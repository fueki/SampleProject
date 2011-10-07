package jp.giogio.individualjojoline;

import java.io.IOException;

import jp.giogio.individualjojoline.db.DataBaseHelper;
import jp.giogio.individualjojoline.model.AppData;
import jp.giogio.individualjojoline.model.PictureManager;
import jp.giogio.individualjojoline.widget.CoverFlowGallery;
import jp.giogio.individualjojoline.widget.CoverFlowImageAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;


public class IndividualJoJoLineActivity extends Activity implements OnItemClickListener, OnItemSelectedListener {
	public interface mReplaceList {

	}

	protected SQLiteDatabase db;
	private DataBaseHelper mDbHelper;

	
	/**
	 * 画像を表示する為のActivityの識別子
	 */
	private static final int PICTURE_ACTIVITY = 100;
	
	/**
	 * ギャラリー。
	 */
	private CoverFlowGallery mCoverFlowGallery;

	/**
	 * 画像情報を管理するインスタンス。
	 */
	private PictureManager mPictureManager;

	/**
	 * 選択されている画像のタイトル。
	 */
	private TextView mTitleTextView;
	
	/**
	 * 選択されている画像のタイトル。
	 */
	private TextView mNameTextView;
	
	private String mReplaceString;
	public static String mReplaceList = null;
	private static final String REPLACE_KEY = "replace_key";
	private static final String ACTION_INTERCEPT = "com.adamrocker.android.simeji.ACTION_INTERCEPT";
	
	
	/**
	 * コントロールを初期化
	 */
	private void initControls(){
		this.mCoverFlowGallery = (CoverFlowGallery)this.findViewById(R.id.content_list);
		this.mCoverFlowGallery.setAdapter( new CoverFlowImageAdapter( this.getApplicationContext(), this.mPictureManager.getThumbnailIds(), 350, 350, false, true ) );
//		this.mCoverFlowGallery.setSpacing( -30 );
//		this.mCoverFlowGallery.setSelection( this.mPictureManager.getCurrentIndex(), true );
//		this.mCoverFlowGallery.setAnimationDuration( 1000 );
		this.mCoverFlowGallery.setOnItemClickListener( this );
		this.mCoverFlowGallery.setOnItemSelectedListener( this );
		
		this.mNameTextView  = (TextView)this.findViewById(R.id.content_name);
		this.mTitleTextView = (TextView)this.findViewById(R.id.content_title);
		this.updateSelectionInfo();
	}

	/**
	 * 遷移先のActivityが終了した時に発生
	 *
	 * @param requestCode 遷移先のActivityの識別情報
	 * @param resultCode　遷移先のActivity実行結果を示す値
	 * @param data　　　　　遷移先のActivityが設定したIntentインスタンス
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == PICTURE_ACTIVITY){
			this.mCoverFlowGallery.setSelection(this.mPictureManager.getCurrentIndex(), true);
			this.updateSelectionInfo();
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent it = getIntent();
	    String action = it.getAction();

	    if (action != null && ACTION_INTERCEPT.equals(action)) {
	        /* Simejiから呼出された時 */
	        mReplaceString = it.getStringExtra(REPLACE_KEY);// 置換元の文字を取得
	        this.setContentView(R.layout.main);
	    } else {
	        // Simeji以外から呼出された時
	    	this.setContentView(R.layout.main);
	    }

		//システムデータベース領域にコピー
		setDatabase();

		this.mPictureManager = ((AppData)this.getApplication()).getPicture();
		this.initControls();
		
//		DataBaseInsert db = new DataBaseInsert(this);
//		db.insert();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(mReplaceList != null){
			replace(mReplaceList);
		}
	}

	private void replace(String result) {
	    Intent data = new Intent();
	    data.putExtra(REPLACE_KEY, result);
	    setResult(RESULT_OK, data);
	    mReplaceList = null;
	    finish();
	}
	
	/**
	 * リストにおけるアイテムの選択状態が変更された時に発生
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		this.mPictureManager.select(position);
		this.updateSelectionInfo();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 選択されたコンテンツの情報を更新
	 */
	private void updateSelectionInfo(){
		//this.mIndexTextView.setText(String.valueOf(this.mPictureManager.getCurrentIndex() + 1));
		this.mTitleTextView.setText(this.mPictureManager.getPicture().getTitle());
		this.mNameTextView.setText(this.mPictureManager.getPicture().getName());
	}

	/**
	 * リスト上のアイテムがクリックされた時に発生
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		intent.setClass(this, ResultJoJoList.class);
		intent.putExtra("id", position);
		this.startActivityForResult(intent, PICTURE_ACTIVITY);
	}
	
	private void setDatabase() {  
	    mDbHelper = new DataBaseHelper(this);   
	    try {  
	        mDbHelper.createEmptyDataBase();  
	        db = mDbHelper.openDataBase();  
	    } catch (IOException ioe) {  
	        throw new Error("Unable to create database");  
	    } catch(SQLException sqle){  
	        throw sqle;  
	    } 
	}
}