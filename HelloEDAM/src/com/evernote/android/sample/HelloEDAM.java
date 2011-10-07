package com.evernote.android.sample;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.thrift.protocol.TBinaryProtocol;

import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteStore;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Resource;
import com.evernote.edam.type.User;
import com.evernote.edam.userstore.AuthenticationResult;
import com.evernote.edam.userstore.Constants;
import com.evernote.edam.userstore.UserStore;

import com.evernote.android.edam.EDAMUtil;
import com.evernote.android.edam.FileData;
import com.evernote.android.edam.TAndroidHttpClient;

import com.evernote.android.sample.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * この単純なAndroidアクティビティは，ノートを作成するためのEvernote webサービスAPI (EDAM) との
 * 統合の仕方を表します。
 * 
 * このサンプルでは，ユーザがデバイスのイメージギャラリーから画像を1枚選びます。その画像は直接 
 * WebサービスAPIによってEvernoteに保存されます。
 * 
 * このプロジェクトをビルドするためには，まず初めにlib/java/libthrift.jarとlib/java/evernote-api-*.jar
 * をlibディレクトリにコピーする必要があります。
 */
public class HelloEDAM extends Activity {
  
  // 注意: あなたがEvernoteから受け取ったconsumer keyとconsumer secretに変更
  //      してください。
  private static final String CONSUMER_KEY = "edamtest";
  private static final String CONSUMER_SECRET = "0123456789abcdef";
 
  // 注意: あなたが実際にEvernoteサーバで使用しているユーザ名とパスワードに変更
  //      しなければなりません。
  //      https://sandbox.evernote.com/Registration.action で
  //      sandbox.evernote.com用のアカウントを作成することができます。
  //       
  private String username = "fueroid";
  private String password = "subarub4";

  // ユーザエージェントをあなたのアプリケーションを説明する文字列 (会社名/アプリ名とバージョンという形式)
  // に変更してください。ユニークなユーザエージェントが使われていれば，Evernote側のログを識別できるので，
  // より良いサポートを提供することができます。
  private static final String USER_AGENT = "Evernote/HelloEDAM (Android) " + 
                                           Constants.EDAM_VERSION_MAJOR + "." + 
                                           Constants.EDAM_VERSION_MINOR;

  // アプリケーションデータを格納するディスク上のディレクトリ
  private static final String APP_DATA_PATH = "/Android/data/com.evernote.android.sample/files/";

  // アクティビティ結果要求コード
  private static final int SELECT_IMAGE = 1;

  // アプリケーション名 (ログ用)
  private static final String TAG = "HelloEDAM";

  // 全てのEvernoteノートに適用するENMLの前文
  // ノートコンテンツは<en-note>と</en-note>の間に入れます。
  private static final String NOTE_PREFIX = 
    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
    "<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">" +
    "<en-note>";

  // 全てのEvernoteノートに適用するENMLの後文
  private static final String NOTE_SUFFIX = "</en-note>";
  // サンドボックスサーバの代わりにEvernoteプロダクションサーバを使う場合は
  // この値を "www.evernote.com" に変更してください。
  private static final String EVERNOTE_HOST = "sandbox.evernote.com";
  private static final String USERSTORE_URL = "https://" + EVERNOTE_HOST + "/edam/user";
  private static final String NOTESTORE_URL_BASE = "https://" + EVERNOTE_HOST + "/edam/note/";

  // Evernoteウェブサービスを使うためのクライアントクラス
  private UserStore.Client userStore;
  private NoteStore.Client noteStore;

  // ユーザ認証が成功した後にEvernoteウェブサービスから受け取る認証トークン
  private String authToken;
  
  // 我々が更新するUI要素
  private Button btnSave;
  private TextView msgArea;
  
  // ギャラリーから現在選択されている画像のパスとMIMEタイプ
  private String filePath;
  private String mimeType;
  private String fileName;

  /** アクティビティが最初に生成されたときに呼ばれる。 */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    this.msgArea = (TextView)findViewById(R.id.message);
    this.btnSave = (Button) findViewById(R.id.save_button);
    this.btnSave.setEnabled(false);
    
    setupApi();
  }
  
  /**
   * "Select Image"ボタンをユーザがタップしたときに呼ばれる。
   * 
   * 共有する画像を選択するために画像ギャラリーをユーザに見せる。
   */
  public void startSelectImage(View view) {
    Intent intent = new Intent(Intent.ACTION_PICK, 
                               MediaStore.Images.Media.INTERNAL_CONTENT_URI);
    startActivityForResult(intent, SELECT_IMAGE);
  }

  /**
   * 我々が起動したアクティビティから制御が戻ってきたときに呼ばれる。
   */
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == SELECT_IMAGE)
      // 我々の'startSelectImage'アクションからのコールバック
      if (resultCode == Activity.RESULT_OK) {
        endSelectImage(data);
      } 
  }

  /**
   * 画像ギャラリーピッカーから制御が戻ってきたときに呼ばれる。
   * ユーザが選択した画像を読み込む。
   * 
   * @param アクティビティから返されたデータ
   */
  private void endSelectImage(Intent data) {
    // ギャラリーからのコールバックはテーブルへのポインタを含んでいる。
    // 適切なレコードをルックアップして必要な情報を引き出す。
    // 以下の場合はディスク上のファイルのパスとファイル名，MIMEタイプになる。
    Uri selectedImage = data.getData();
    String[] queryColumns = { MediaStore.Images.Media.DATA, 
                              MediaStore.Images.Media.MIME_TYPE, 
                              MediaStore.Images.Media.DISPLAY_NAME };
    Cursor cursor = getContentResolver().query(selectedImage, queryColumns, null, null, null);
    cursor.moveToFirst();
    this.filePath = cursor.getString(cursor.getColumnIndex(queryColumns[0]));
    this.mimeType = cursor.getString(cursor.getColumnIndex(queryColumns[1]));
    this.fileName = cursor.getString(cursor.getColumnIndex(queryColumns[2]));
    cursor.close();

    if (getNoteStore() != null) {
      this.msgArea.setText(this.fileName);
      this.btnSave.setEnabled(true);
    }
  }

  /**
   * "Save Image"ボタンをユーザがタップしたときに呼ばれる。
   * 
   * 現在選択されている画像を，EvernoteウェブサービスAPIを使用する
   * ユーザのEvernoteアカウントに保存する。
   * 
   * アクティビティが開始されたときにEvernote APIの初期化に成功していない場合
   * は何もしない。
   */
  public void saveImage(View view) {
    if (getNoteStore() != null) {
      String f = this.filePath;
      try {
        // 画像ファイルのデータをハッシュ化
        // そのハッシュはENMLノートコンテンツの中で画像ファイルを参照するために使われる。
        InputStream in = new BufferedInputStream(new FileInputStream(f)); 
        FileData data = new FileData(EDAMUtil.hash(in), new File(f));
        in.close();

        // 新しいリソースを作成
        Resource resource = new Resource();
        resource.setData(data);
        resource.setMime(this.mimeType);
        
        // 新しいノートを作成
        Note note = new Note();
        note.setTitle("Android test note");
        note.addToResources(resource);
        
        // ノートのENMLコンテンツを設定
        // ENMLの仕様は以下の"appendix A of the Evernote API Overview"を参照
        // http://www.evernote.com/about/developer/api/evernote-api.htm
        String content = 
          NOTE_PREFIX +
          "<p>This note was uploaded from Android. It contains an image.</p>" +
          "<en-media type=\"" + this.mimeType + "\" hash=\"" +
          EDAMUtil.bytesToHex(resource.getData().getBodyHash()) + "\"/>" +
          NOTE_SUFFIX;
        note.setContent(content);
        
        // サーバにノートを作成
        // 返ってくるNoteオブジェクトには，そのノートのユニークID(GUID)やリソースのGUID，
        // 作成時間や更新時間など，サーバが生成した属性情報を含まれている。
        Note createdNote = getNoteStore().createNote(getAuthToken(), note);

        Toast.makeText(this, R.string.msg_image_saved, Toast.LENGTH_LONG).show();
      } catch (Throwable t) {
        // Throwable例外を捕捉するのは一般的には悪いことだが，これはシンプルなデモなので
        // 全てのエラーを捕まえてログを書きたい。
        Toast.makeText(this, R.string.err_creating_note, Toast.LENGTH_LONG).show();
        Log.e(TAG, getString(R.string.err_creating_note), t);
      }  
    }
  }

  /**
   * ユーザ認証も含めて，EvernoteウェブサービスAPIとの通信を設定する。
   * 
   */
  private void setupApi() {
    try {
      // UserStore.clientを作るためにEDAMUtil.getUserStoreClient()を使うこともできる。
      TAndroidHttpClient userStoreTrans = 
        new TAndroidHttpClient(USERSTORE_URL, USER_AGENT, getTempDir());
      TBinaryProtocol userStoreProt = new TBinaryProtocol(userStoreTrans);
      setUserStore(new UserStore.Client(userStoreProt, userStoreProt));
      
      // 現在使用しているEvernote APIのバージョンが，サーバと互換性があることを検証する
      boolean versionOk = userStore.checkVersion("HelloEDAM (Android)",
          com.evernote.edam.userstore.Constants.EDAM_VERSION_MAJOR,
          com.evernote.edam.userstore.Constants.EDAM_VERSION_MINOR);
      if (!versionOk) {
        this.msgArea.setText(R.string.err_protocol_version);
        Log.e(TAG, getString(R.string.err_protocol_version));
        Toast.makeText(this, R.string.err_protocol_version, Toast.LENGTH_LONG).show();
        return;
      }

      // 認証する。注: もし以下の場合は失敗する
      // - ダミーのAPI consumer keyとconsumer secretを各自の値で置き換えていない場合
      // - WebサービスAPIキーを要求する場合: その場合はOAuthで認証しなければならない
      // - 接続先として sandbox.evernote.com を指定しているのに，www.evernote.comの
      //   ユーザ名とパスワードを使用している場合
      AuthenticationResult authResult = null;
      try {
        authResult = getUserStore().authenticate(username, password, CONSUMER_KEY, CONSUMER_SECRET);
      } catch (EDAMUserException ex) {
        this.msgArea.setText(R.string.err_authentication);
        Log.e(TAG, getString(R.string.err_authentication), ex);
        Toast.makeText(this, R.string.err_authentication, Toast.LENGTH_LONG).show();
        return;
      }
      User user = authResult.getUser();
      setAuthToken(authResult.getAuthenticationToken());

      // 認証が成功した後にNoteStoreへの接続を設定する
      String noteStoreUrl = NOTESTORE_URL_BASE + user.getShardId();
      TAndroidHttpClient noteStoreTrans = 
        new TAndroidHttpClient(noteStoreUrl, USER_AGENT, getTempDir());
      TBinaryProtocol noteStoreProt = new TBinaryProtocol(noteStoreTrans);
      setNoteStore(new NoteStore.Client(noteStoreProt, noteStoreProt));
    } catch (Throwable t) {
      // Throwable例外を捕捉するのは一般的には悪いことだが，これはシンプルなデモなので
      // 全てのエラーを捕まえてログを書きたい。
      this.msgArea.setText(R.string.err_api_setup);
      Log.e(TAG, getString(R.string.err_api_setup), t);
      Toast.makeText(this, R.string.err_api_setup, Toast.LENGTH_LONG).show();
    }
  }

  private UserStore.Client getUserStore() {
    return this.userStore;
  }
  
  private void setUserStore(UserStore.Client userStore) {
    this.userStore = userStore;
  }
  
  private NoteStore.Client getNoteStore() {
    return this.noteStore;
  }
  
  private void setNoteStore(NoteStore.Client noteStore) {
    this.noteStore = noteStore;
  }
  
  private String getAuthToken() {
    return this.authToken;
  }
  
  private void setAuthToken(String authToken) {
    this.authToken = authToken;
  }
  
  private File getTempDir() {
    return new File(Environment.getExternalStorageDirectory(), APP_DATA_PATH);
  }
}