package jp.android;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class IndividualJoJoDBActivity extends Activity {
    /** Called when the activity is first created. */
	
	protected SQLiteDatabase db;
	protected Cursor cursor;
	private TextView resultTextView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        db = (new DatabaseOpenHelper(this)).getWritableDatabase();
    }
    
    public void onClickCreateDB(View v){
    	insert();
    }
    
    public void onClicksss(View v){
    	DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(this);
        db = null;
        TextView textCount = (TextView) findViewById(R.id.textView1);
        
     // 読込専用のSQLiteDatabaseオブジェクトを取得する
        db = databaseOpenHelper.getReadableDatabase();
        // 全件検索する
        Cursor cursor = db.query(DatabaseOpenHelper.ARAKI_TABLE, null, null, null, null, null, null);
        Cursor cursor1 = db.query(DatabaseOpenHelper.JONATHAN_TABLE, null, null, null, null, null, null);
        if (cursor != null) {
                startManagingCursor(cursor);
                textCount.setText("[データ件数：" + cursor.getCount() + "件]");
        }

        while (cursor.moveToNext()) {
                // nameを取得
                String name = cursor.getString(cursor.getColumnIndex("WORDS"));

                Log.v("ARAKI_TABLE", "name:" + name);
        }
        while (cursor1.moveToNext()) {
            // nameを取得
            String name = cursor1.getString(cursor1.getColumnIndex("WORDS"));

            Log.v("JONATHAN_TABLE", "name:" + name);
    }
        // データベースから切断する
        databaseOpenHelper.close();
        Log.v("DatabaseSample", "Succeeded in close the database.");
    }

    private void insert(){
    	long rowId;
    	DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(this);
        db = null;

        // 読込専用のSQLiteDatabaseオブジェクトを取得する
        db = databaseOpenHelper.getWritableDatabase();

        // isnertデータの設定
        //ContentValues values = new ContentValues();
//        ArrayList<ContentValues> valueList = new ArrayList<ContentValues>();
        ContentValues values = new ContentValues();
        values.put("WORDS", "はっきりいうと、この作品のテーマはありふれたテーマ「生きること」です");
        rowId = db.insert(DatabaseOpenHelper.ARAKI_TABLE, null, values);
//        valueList.add(values);
//        values.put("WORDS", "き…切れた。\nぼくの体の中で　なにかが切れた…決定的ななにかが……！");
//        valueList.add(values);
        //ContentValues values1 = new ContentValues();
//        for ( int i = 0; i < valueList.size(); i++ ) {
//        	//values1 = valueList.get(i);
//        	rowId = db.insert(DatabaseOpenHelper.ARAKI_TABLE, null, valueList.get(i));
//        }

//        for(ContentValues ff : valueList){
//        	ContentValues values11 = new ContentValues();
//        	values1 = ff;
//        	rowId = db.insert(DatabaseOpenHelper.ARAKI_TABLE, null, values1);
//        }
        
       // データを追加する 
//        rowId = db.insert(DatabaseOpenHelper.ARAKI_TABLE, null, values);
//        values.put("WORDS", "き…切れた。\nぼくの体の中で　なにかが切れた…決定的ななにかが……！");
//        rowId = db.insert(DatabaseOpenHelper.ARAKI_TABLE, null, values);
        
        values.put("WORDS", "き…切れた。\nぼくの体の中でなにかが切れた…決定的ななにかが……！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "君がッ、泣くまで、殴るのをやめないッ！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "なっ！何をするだァーーーーーッゆるさんッ！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "ふるえるぞハート！燃えつきるほどヒート！！\nおおおおおっ　刻むぞ血液のビート！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "山吹き色の波紋疾走！！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "ぼくは父を守るッ！ジョースター家守るッ！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "ちがう信念さえあれば人間に不可能はない！人間は成長するのだ！してみせるッ！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "ぼくの青春はディオとの青春！これからその青春に決着をつけてやるッ！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "おまえの骨ひとつとてこの世に残さん！邪悪な魂を絶ってやる！！歴史の闇に永遠に沈め！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "試してみろ！ひっぱった瞬間ぼくの丸太のような足蹴りが君の股間をつぶす。それでもいいのなら！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "ディオッ！君の野望 僕が打ち砕く！！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        values.put("WORDS", "とうさァアアアアアん最後の力をォオオオオッ！！");
        rowId = db.insert(DatabaseOpenHelper.JONATHAN_TABLE, null, values);
        
        
        values.put("WORDS", "ま…まずい！ジョジョは殴られたことよりも、エリナさんに買ってもらった服が血で汚れたことを怒るタイプ！");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        values.put("WORDS", "スピードワゴンはクールに去るぜ");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        values.put("WORDS", "こいつはくせえッー！ゲロ以下のにおいがプンプンするぜッーーーーッ！！");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        values.put("WORDS", "やってみたいんだ！ おせーて！ おせーてくれよォ！");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        values.put("WORDS", "う・・・う・・・おれはいつも傍観者よ・・・\nなにもできねえ なにもしてやれねえ");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        values.put("WORDS", "おれは物を盗むが、あいつは命を盗むッ！！");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        values.put("WORDS", "「誰だ？」って聞きたそうな表情してんで自己紹介させてもらうがよ\nおれぁ、おせっかい焼きのスピードワゴン！");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        values.put("WORDS", "こんな悪には出会ったことがねえほどになァーーーーッ");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        values.put("WORDS", "環境で悪人になっただと？ちがうね！！こいつは生まれついての悪だッ！");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        values.put("WORDS", "おれぁ生まれてからずっと暗黒街で生きいろんな悪党を見てきた。だから悪い人間といい人間の区別は「におい」で分かる！");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        values.put("WORDS", "運命か…人の出会いってのは運命できめられてるのかもしれねえな…");
        rowId = db.insert(DatabaseOpenHelper.SPEEDWAGON_TABLE, null, values);
        
        
        values.put("WORDS", "我がナチスの科学力はァァァァァァァァアアア世界一ィィィイイイイ");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "ナチスの科学は世界一チイイイイ！！");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "我がドイツの医学薬学は世界一ィィィ！");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "おまえらイギリス人とは根性がちがうのだ、この腰ぬけめがッ！");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "紫外線照射装置作動！");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "ヨーロッパの格言にこんなのがある…「老人が自殺する所…その町はもうすぐ滅びる」");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "ドジこいたーーッ。手柄をたてて勲章をもらうつもりが、こいつはいかーん！");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "小僧！人種はちがえど、わたしはおまえのような勇気ある者に敬意を表す！\nすぐれた人間のみ生き残ればよい！");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "くらえッ新しい対吸血鬼兵器！紫外線照射装置ィィィィィィィィ！！");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "動物園の檻の中の灰色熊を怖がる子供がおるか？");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "う…うろたえるんじゃあないッ！ドイツ軍人はうろたえないッ！");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "祖国のためなら、足の二本や三本かんたんにくれてやるわーーーッ！！");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "人間の偉大さは―恐怖に耐える誇り高き姿にある―\nギリシアの史家、プルタルコスの言葉だ");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "地獄から舞い戻ったぜ");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "ブァカ者がァアアアア");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        values.put("WORDS", "おれの体はァァアアアアアーッ！！我がゲルマン民族の最高知能の結晶であり誇りであるゥゥゥ！！つまりすべての人間を越えたのだァアアアアアアアア！！");
        rowId = db.insert(DatabaseOpenHelper.STROHEIM_TABLE, null, values);
        
        
        values.put("WORDS", "刻むぜ波紋のビート！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "ケツの穴にツララを突っ込まれた気分だ・・・");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "確実！そうコーラを飲んだらゲップが出るっていうくらい確実じゃッ！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "「表面張力」というのを知っているかね？バービーくん");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "図にのるんじゃあないッ！このアメ公がッ！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "ハッピー うれピー よろピくね～");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "ヤレヤレだ");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "おれがどくのは道にウンコがおちている時だけだぜ");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "おまえの次のセリフは『なんでメリケンのことわかったんだこの野郎！』という！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "ゆるせねえ！美人なだけになおさら怒りがこみあげるぜ！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "このくそったれ野郎の首から下は、わしの祖父ジョナサン・ジョースターの肉体をのっとったものなのじゃあああーーーーあああ！！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "凄まじい殺気ってやつだッ！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "早く逃げねーと、シタ入れてキスするぞッ！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "気づくのが遅いんだよ、アホレイツォ！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "このオレに二度同じ手を使うことは、すでに凡策なんだよ！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "おれの嫌いな言葉は、一番が「努力」で、２番目が「ガンバル」なんだぜーッ");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "養豚場のブタでもみるかのように冷たい目だ…残酷な目だ…");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "は……発想のスケールで……ま…………まけた");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "このブス女が～っジョセフ・ジョースターが闘いにおいて、きさまなんかとは年季がちがうということをこれから思いしらせてやる");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "「相手が勝ち誇ったとき そいつはすでに敗北している」これがジョセフ・ジョースターのやり方、老いてますます健在というところかな");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        values.put("WORDS", "また会おうッ！　わしのことが嫌いじゃあなけりゃあな！…マヌケ面ァ！");
        rowId = db.insert(DatabaseOpenHelper.JOSEF_TABLE, null, values);
        
        
        values.put("WORDS", "やれやれだぜ");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "ドゥーユゥーアンダスタンンンンドゥ？");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "そうだ、味方だぜ。　ただし正義の……味方だ…");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "見るんじゃあなくて観ることだ 聞くんじゃあなく聴くことだ");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "生涯に３度も飛行機で落ちた男と、いっしょにセスナなんかあまり乗りたかねーな");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "つけの領収書だぜ");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "子供の頃『刑事コロンボ』が好きだったせいかこまか、いことが気になると夜もねむれねえ");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "ジョースター家には、伝統的な戦いの発想法があってな…\nひとつだけ残された戦法があったぜ。それは！『逃げる』");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "なあーに、野球のルールは知っている…ゲーム操作はやりながらおぼえるぜ！");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "グッド　なかなかおもしろいゲームだ…");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "バレなきゃあイカサマじゃあねえんだぜ……");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "やれやれ　こういう時はハゲますもんだぜ…\n「一回のオモテだ…まだ始まったばかりだガンバレ承太郎」ってな");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "これがてめーの『スタンド』か！緑色でスジがあって、まるで光ったメロンだな！");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "裁くのは　おれの『スタンド』だッー！！");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "流星指刺（スターフィンガー）");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "てめーは、この空条承太郎がじきじきにブチのめす");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "てめーの敗因は…たったひとつだぜ…ＤＩＯ…\nたったひとつの単純な答えだ…『てめーは　おれを怒らせた』");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "忘れたくても、そんなキャラクターしてねえぜ…てめーはよ　元気でな……");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "やかましいッ！おれは女が騒ぐとムカつくんだッ！");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "『スタープラチナ ザ・ワールド』");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "ヤツに「したたかさプラス冷静な態度」を感じるぜ");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "オラァッ！ おぉおぉぉぉおおおオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラァッ！！");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "『時は動き出す』");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "いい時計だな！だがもう時間が見れないようにたたっこわしてやるぜ…きさまの顔面の方をな…");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "やかましい！うっとおしいぞこのアマ！");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "『スタンド』使いに共通する見分け方を発見した。それは…スタンド使いはタバコの煙を少しでも吸うとだな…鼻の頭に血管が浮き出る");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "もうてめーにはなにもいうことはねえ… …とてもアワれすぎて 何も言えねえ");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "「無理」だと？この旅は無理なことばかりして来た旅だった…無理だとか無駄だとかいった言葉は聞きあきたしおれたちには関係ねえ");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "ああうそだぜ！だが…マヌケは見つかったようだな");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        values.put("WORDS", "「悪」とはてめー自身のためだけに弱者を利用しふみつけるやつのことだ！！ましてや女をーっ！きさまがやったのはそれだ！あ～んおめーの「スタンド」は被害者自身にも法律にも見えねえしわからねえ…だから、おれが裁く！");
        rowId = db.insert(DatabaseOpenHelper.JOUTAROU_TABLE, null, values);
        
        
        values.put("WORDS", "レロレロレロレロレロレロレロレロレロ");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        values.put("WORDS", "『我が名は花京院典明』\n『我が友人アヴドゥルの無念のために、左にいる友人ポルナレフの妹の魂のやすらぎのために』\n『死をもってつぐなわせてやる』");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        values.put("WORDS", "このゲームで、この花京院典明に精神的動揺による操作ミスは決してない！と思っていただこうッ！");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        values.put("WORDS", "さあ、お仕置きの時間だよベイビー");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        values.put("WORDS", "鏡に「中の世界」なんてありませんよ…ファンタジーやメルヘンじゃあないんですから");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        values.put("WORDS", "おぞましいスタンドには、おぞましい本体がついているものよ");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        values.put("WORDS", "エメラルドスプラッシュ！！");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        values.put("WORDS", "バ…バカな…か…簡単すぎる…あっけなさすぎる…");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        values.put("WORDS", "承太郎ッ！君の意見を聞こうッ！");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        values.put("WORDS", "「悪」？「悪」とは敗者のこと…「正義」とは勝者のこと…生き残った者のことだ。過程は問題じゃあない敗けたやつが『悪』なのだ");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        values.put("WORDS", "メ…ッセージ…で…す…これが…せい…いっぱい…です。ジョースター…さん受け取って…ください…伝わって…ください…");
        rowId = db.insert(DatabaseOpenHelper.KAKYUUIN_TABLE, null, values);
        
        
        values.put("WORDS", "おれの『スタンド』がしかるべき報いを与えてやるッ！");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        values.put("WORDS", "やったッ！命中だッ！しゃぶれッ！おれの剣をしゃぶれッ！このドグサレがッ！");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        values.put("WORDS", "なにか頭の悪そうなカードだな");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        values.put("WORDS", "ブッた切ってやるッ！今のオレのチャリオッツは素早いぜッ！");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        values.put("WORDS", "ブラボー！おお…ブラボー！！");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        values.put("WORDS", "おれの『スタンド』がしかるべき報いを与えてやるッ！");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        values.put("WORDS", "ポルナレフ…名のらしていただこう…J・P… ポルナレフ");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        values.put("WORDS", "まあまあ写真ならわたしがとってあげよう。君キレイな足しているから全身入れよーね (ほんとはフトモモだけアップでとりたいな)");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        values.put("WORDS", "ひえええええええッ！！ちいさい。これはなんだかわからないけどスゴク悲しいシクシク");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        values.put("WORDS", "あ…ありのまま今起こったことを話すぜ！『おれは奴の前で階段を登っていたと思ったら、いつの間にか降りていた』な…なにを言っているのかわからねーと思うがおれも何をされたのかわからなかった…頭がどうにかなりそうだった…催眠術だとか超スピードだとかそんなチャチなもんじゃあ断じてねえ。もっと恐ろしいものの片鱗を味わったぜ…");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        values.put("WORDS", "マンガ家にしてみろッ！子どものころからなりたかったんだッ！ディズニーより売れっ子のやつがいいッ！みじめなヤツはヤだぞッ『ポルナレフランド』をおっ立てるんだ");
        rowId = db.insert(DatabaseOpenHelper.POLNAREFF_TABLE, null, values);
        
      
        values.put("WORDS", "最高に「ハイ！」ってやつだァァァァ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "今！ためらいもなくきさまを惨殺処刑してくれよう！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "君…もうジョジョとキスはしたのかい？まだだよなァ初めての相手はジョジョではないッ！このディオだッ！ーッ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "ＫＷＡＨＨＨ！コリコリ弾力ある頸動脈にさわっているぞォ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "猿が人間に追いつけるかーッおまえはこのディオにとってのモンキーなんだよ ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "友情だと！？きれいごとを並べてニコニコするなよなクズどもがッ！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "ぼくは犬が嫌いだ！怖いんじゃあない人間にへーこらする態度に虫酸が走るのだ！あのダニーとかいう阿呆犬をぼくに近づけるなよな");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "このままッ！！親指を！こいつの！目の中に…つっこんで！殴りぬけるッ！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "ほおぉ～衛生観念もない虫けら同然のたかがじじいの浮浪者が、よくもこのディオにそんな無礼な口がきけたものだ…");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "無駄無駄無駄無駄無駄無駄無駄無駄ァーーーーーーッ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "無駄無駄無駄無駄無駄無駄無駄無駄無駄");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "無駄無駄ッ！！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "ＷＲＹＹＹＹＹＹＹＹＹＹーーーーーーッ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "ＵＲＥＥＹＹＹ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "「波紋」？「呼吸法」だと？フーフー吹くなら………このおれのために、ファンファーレでも吹いてるのが似あっているぞッ！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "なじむ実に！なじむぞ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "あんなクズに名誉などあるものかァーーーーッ！！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "ロードローラーだッ！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "自ら首をはねるとは…うれしいぞ……");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "ドアぐらい開けて出ていけ…この世界の空間から、姿をまったく消すスタンドよ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "早く持って来いッ！\nスチュワーデスがファースト・クラスの客に酒とキャビアをサービスするようにな…");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "老いぼれが…！　きさまのスタンドが一番…なまっちょろいぞッ！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "ジョースターの血統はあなどれんということだ！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "きさま！見ているなッ！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "いくぞ！ジョジョ！そしてようこそ！我が永遠の肉体よ！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "カエルの小便よりも……下衆な！下衆な波紋なぞをよくも！よくもこのおれに！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "そんなねむっちまいそうな、のろい動きでこのディオが倒せるかァーーーーーー！？");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "おまえは今まで食ったパンの枚数をおぼえているのか？");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "おれは人間をやめるぞ！ジョジョーーーーッ！！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "おれは人間を超越するッ！ジョジョ、おまえの血でだァーーッ！！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "貧弱！貧弱ゥ！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "自動車か…なかなかのパワーとスピードだ。このDIOが生まれた時代は馬車しか走っていなかった");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "「世界」時よ止まれッ！ WRYYYYYYYYYYーッ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "ロードローラーだッ！もうおそい！脱出不可能よッ！無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄ァーッ8秒経過！ウリイイイイヤアアアッーぶっつぶれよォォッ");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        values.put("WORDS", "マヌケが…知るがいい…「世界」の真の能力は…まさに！「世界を支配する」能力だということを！");
        rowId = db.insert(DatabaseOpenHelper.DIO_TABLE, null, values);
        
        
        values.put("WORDS", "なんていうか･･････その･･･下品なんですが･･････フフ･･････勃起･･････しちゃいましてね･････････");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "今ノ爆発ハ人間ジャネェ～～～～コッチヲ見ロォ～");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "コッチヲ見ロ。オイ…コッチヲ…見ロッテ、イッテルンダゼ");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "質問を質問で返すなあーっ！！疑問文には、疑問文で答えろと、学校で教えているのか？");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "ちょっと臭ってきたか……この女ともそろそろ別れ時かな。");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "『キラークイーン』の特殊能力…それは…『キラークイーン』は『触れたもの』は『どんな物』でも『爆弾』に変えることができる");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "顔半分が内部でふっ飛んで、脳ミソが1/3ぐらい顔の肉とシェイクされただけのようだったがね…");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "キラークイーンは、すでにドアノブに触っている");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "『キラークイーン』ッ！こいつらを爆破しろォーッ！！");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "いいや！「限界」だッ！押すねッ！『今だッ』！");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "「ジョー･モンタナ」の投げるタッチダウンパスのように、確実にやつの鼻先に突っこみ、その位置で「点火」してやる！");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "「思い込む」という事は何よりも「恐ろしい」ことだ…しかも自分の能力や才能を優れたものと過信している時は、さらに始末が悪い");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "「問題」は！！この吉良吉影にとって、最も重要な…「問題」は…！！こいつが『敵かどうか』という事だ…");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "『キラークイーン』第一の爆弾");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "キラークイーン『第３の爆弾』BITE THE DUST");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "あの目は…「肥えだめ」で溺れかけてるネズミみたいに絶望しているぞ…");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "「命」を「運」んで来ると書いて『運命』！…フフよくぞ言ったものだ");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        values.put("WORDS", "あなたの「手」…とてもなめらかな関節と皮膚をしていますね…白くってカワイイ指だ、ほおずり…してもいいですか？…「ほおずり」…するととても落ちつくんです。アフウウウ～");
        rowId = db.insert(DatabaseOpenHelper.KIRA_TABLE, null, values);
        
        
        values.put("WORDS", "「まさか」って感じだがグッときたぜ！！");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "なるほど、完璧な作戦っスねーーっ。不可能だという点に目をつぶればよぉ～～。");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "グレートだぜ…億泰！");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "スゲーッ、爽やかな気分だぜ。新しいパンツをはいたばかりの、正月元旦の朝のよーによォ～～～ッ");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "ナイスバディのお姉ちゃん捕まえるハンティングっスか？オレ、けっこう純愛タイプだからなぁそーゆーことやったことねーっスよ");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "承太郎さんが気合い入れりゃあ、ポパイにホーレン草、楽勝っスよ～");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "いや違うッぶつかると思うからぶつかるんだ！この仗助くんならやれる！曲がり切れる！曲がってやるう～ッ");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "ドララララララララララララララララララララララララララ");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "おい…先輩あんた…今おれのこの頭のことなんつった！");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "おれがこの町とおふくろを守りますよ。この人の代わりに…どんなことが起ころうと…");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "今回はマヌケじゃあなかったぜ…プレッシャーをはねかえす男「東方仗助」と呼んでくれっス！");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "スタンドも月までブッ飛ぶこの衝撃…");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        values.put("WORDS", "お…おまえなんかぜんぜん怖くなくなったぜバ～カッ");
        rowId = db.insert(DatabaseOpenHelper.JOUSUKE_TABLE, null, values);
        
        
        values.put("WORDS", "まっ！オレ、頭悪いから、深く考えると頭痛おきるけどよォ～～っ。");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "ンまぁーーーーいッ！！　味に目醒めたァーっ");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "ンまあーいっ！");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "ゥンまああ～いっ");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "オレはくれるっつーもんは、病気以外なら何でももらうかんなー、コラァ！");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "兄貴はよ…ああなって当然の男だ…まっとうに生きれるはずがねえ宿命だった。\nでもよ…でも兄貴は最後にッ！おれの兄貴は、最後の最後におれをかばってくれたよなあ～～っ");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "あっ！こりゃたまらん！ヨダレずびっ！");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "あっ！こりゃたまらん！ヨダレずびっ！なんつーか、気品に満ちた水っつーか、たとえると、アルプスのハープを弾くお姫様が飲むような味っつーか。");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "スゲーさわやかなんだよ…３日間砂漠をうろついて、初めて飲む水っつーかよぉーっ");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "「ハーモニー」っつーんですかあ～～「味の調和」っつーんですかあ～～っ");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "たとえるなら、サイモンとガーファンクルのデュエット！ウッチャンに対するナンチャン！高森朝雄の原作に対する、ちばてつやの「あしたのジョー」！");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "たとえると、『豆まきの節分』の時に、年齢の数だけ豆を食おうとして、大して好きでもねぇ豆を、フトきづいてみたら、一袋食ってたっつーカンジかよぉーっ！");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "やったァーーッメルヘンだッ！ファンタジーだッ！こんな体験できるやつは他にいねーっ");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "おれは、もうとっくにスゲームカついてるぜっ！やつは完ペキ！この億泰・仗助コンビを敵にまわした…！！");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        values.put("WORDS", "ウオオオオオウダラァーッもうどっちか考えるのは面倒くせえぇぇッ！チクショォォォーッ");
        rowId = db.insert(DatabaseOpenHelper.OKUYASU_TABLE, null, values);
        
        
        values.put("WORDS", "うむを言わさず、先手必勝さ！");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "きさま程度のスカタンに、この露伴がなめられてたまるかァ―――ッ！！");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "だが断る。");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "この岸部露伴が最も好きな事のひとつは、自分で強いと思ってるやつに「ＮＯ」と断ってやる事だ…");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "いいかい！もっとも『むずかしい事』は！『自分を乗り越える事』さ！ぼくは自分の『運』をこれから乗り越える！！");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "ところで君達、『おもしろいマンガ』というのは、どうすれば描けるか知ってるかね？\n『リアリティ』だよ！『リアリティ』こそが作品に生命を吹き込むエネルギーであり『リアリティ』こそがエンターテイメントなのさ");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "『マンガ』とは、想像や空想で描かれていると思われがちだが、実は違う！\n自分の見た事や、体験した事、感動した事を描いてこそおもしろくなるんだ！");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "おまえのその髪型な…自分ではカッコいいと思ってるようだけど…ぜェーーんぜん似合ってないよ……ダサイねェ！！");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "今どきいるのか！こんなやつって感じだよ…こぎたない野鳥になら、住み家として気に入ってもらえるかもなあ");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "ぼくに取り憑くなんぞ、ゴキブリがゴキブリポイポイに入って、喜んでいるようなものだッ");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "味も見ておこう");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "わたしの能力…『天国への扉』によって心の扉は開かれる");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "こんな屈辱は初めてだ…憶えてろ…きさま…憶えてろよ…");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        values.put("WORDS", "ぼくの「スタンド」『ヘブンズ・ドアー』…自分の『遠い記憶』と…『「運命」は読めない』…か");
        rowId = db.insert(DatabaseOpenHelper.ROHAN_TABLE, null, values);
        
        
        values.put("WORDS", "このジョルノ･ジョバーナには、夢がある。");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "暗闇に道を開くのは、「覚悟」のある者だけだ…");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "１度でいい事を、２度言わなけりゃあいけないってのは、そいつが頭が悪いって事だからです。");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "あなた…『覚悟して来てる人』…ですよね。");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "ゴールド・(黄金) エクスペリエンス！(体験)");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "WRYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄無駄ァアアアアア");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "人というのは、成功や勝利よりも『失敗』から学ぶ事が多い…。");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "「覚悟」とは！！暗闇の荒野に！！進むべき道を切り開く事だッ！");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "あなたの「覚悟」は…この登りゆく朝日よりも、明るい輝きで『道』を照らしている。そして我々がこれから『向かうべき…正しい道』をもッ！");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "君は…ここに…おいて行く…もう誰も君を…これ以上、傷つけたりはしないように……決して…");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "終わりのないのが『終わり』それが『ゴールド・E・レクイエム』");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        values.put("WORDS", "去ってしまった者たちから受け継いだものは、さらに『先』に進めなくてはならない！！");
        rowId = db.insert(DatabaseOpenHelper.GIORNO_TABLE, null, values);
        
        
        values.put("WORDS", "この味は！………ウソをついてる『味』だぜ");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "『任務は遂行する』『部下も守る』「両方」やらなくっちゃあならないってのが、「幹部」のつらいところだな。");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "『任務は遂行する』…『部下も守る』おまえごときに両方やるというのは、そうムズかしい事じゃあないな。");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "覚悟はいいか？オレはできてる。");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "どうだい、ブルっちまう特技だろう…");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "「ブッ殺してやる」ってセリフは…終わってから言うもんだぜ。オレたち「ギャングの世界」ではな。");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "何をやったって、しくじるもんなのさ。ゲス野郎はな。");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "アリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリアリーヴェデルチ！");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "きさまにオレの心は永遠にわかるまいッ！");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "閉じろジッパ―――ッ！！");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "オレは「正しい」と思ったからやったんだ。後悔はない…");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "こんな世界とはいえ、オレは自分の「信じられる道」を歩いていたい！");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "おまえが決めるんだ…自分の「歩く道」は…自分が決めるんだ……");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "ディ・モールト　グラッツェ");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "『スティッキィ・フィンガーズ』ッ！");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "自分の腕をひきちぎったほどのおまえの気高き『覚悟』と…黄金のような『夢』に賭けよう");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "オレの「命」は…あの時、すでに終わっていたんだ。");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        values.put("WORDS", "気にするな、ジョルノ…そうなるべきだったところに…戻るだけなんだ。元に戻るだけ…");
        rowId = db.insert(DatabaseOpenHelper.BUCHARATHI_TABLE, null, values);
        
        
        values.put("WORDS", "イチゴケーキが食いてーんだよッ、オレはッ！");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        values.put("WORDS", "キャモオオオーーーーン！");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        values.put("WORDS", "あなたのオッパイのぞこーとか、スカートの中の太モモさんに指をはわせよーなんて事は、ついでき心でして、はい！どーか、ボスにだけは内密に～");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        values.put("WORDS", "キャプテン翼やってるぜ。");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        values.put("WORDS", "ああ！ジョルノ！もっとやさしく。そこはダメ！ダメッ！ダメッ！ダメッ！ああ！やさしくして、やさしく！服をぬがさないでッ！感じる。");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        values.put("WORDS", "ボスを倒したのならよォ――実力からいって…次の幹部はオレかな。");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        values.put("WORDS", "それでいい……そこの位置がいいッ！");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        values.put("WORDS", "「お弁当だぞォー！！トスカーナのサラミはうまいぞォーッ！」");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        values.put("WORDS", "「ワギャアアアアーックレーッ」「アギャギャギャギャーックレェー」");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        values.put("WORDS", "行け…『セックス・ピストルズ』ッ！");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        values.put("WORDS", "キスでもしてんだな…スピードがついてる分だけ「道路さん」に熱烈なヤツをよォーッ");
        rowId = db.insert(DatabaseOpenHelper.MISTA_TABLE, null, values);
        
        
        values.put("WORDS", "『ブッ殺した』なら、使ってもいいッ！");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "「直」は素早いんだぜ。パワー全開だぁ～～～。『グレイトフル・デッド』の「直」ざわりはよおおお。");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "そこら辺のナンパ道路や、仲よしクラブで、「ブッ殺す」「ブッ殺す」って、大口叩いて仲間と心をなぐさめあってるような負け犬どもとはわけが違うんだからな。");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "ペッシ　ペッシ　ペッシ　ペッシよォ～～～～～");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "おいオメー、さっきからうるせえぞ。「ブッ殺す」「ブッ殺す」ってよォ～～。");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "どういうつもりだてめー、そういう言葉は、オレたちの世界にはねーんだぜ…そんな、弱虫の使う言葉はな…。");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "「ブッ殺す」…そんな言葉は使う必要がねーんだ。なぜなら、オレや、オレたちの仲間は、その言葉を頭の中に思い浮かべた時には！実際に相手を殺っちまって、もうすでに終わってるからだッ！だから使った事がねェーッ。");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "それにたいしたこたァねーだろォーッ。毎年、世界中のどっかで、旅客機が墜落している…それよりは軽く済むッ！");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "まだわかんねーのか、ママッ子野郎のペッシ！");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "「スタンド」を決して解除したりはしねえッ！たとえ腕を飛ばされようが、脚をもがれようともなッ！");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "「ブッ殺す」と心の中で思ったならッ！その時スデに行動は終わっているんだッ！");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "仲間を切り捨てても、娘を守るため、オレを倒す！それが任務じゃあねえのか？「幹部失格」だな。");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "さっきおまえの事「幹部失格」だなんて言ったが、撤回するよ…無礼な事を言ったな…");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        values.put("WORDS", "グレイト……フル・デッド…………");
        rowId = db.insert(DatabaseOpenHelper.PROSCIUTTO_TABLE, null, values);
        
        
        values.put("WORDS", "ロッチュ～、お腹チュいたニャン！");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "つまり…だからその…マ マで始まる言葉で…マ…マス… そのつまりマスタ～…ベ～ション…をよ…");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "飛びてェーッ");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "糸の『力』よりも、自然の力の方が効果的ってこと…よ");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "決着ゥゥーーーーーーーッ！！");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "手錠はなんのためにある？逃がさないためにあるんじゃあない！屈服させるためにあるッ！");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "あたし死ぬまで決してもうひとりエッチはやらないッ！誓うわ…賭けてもいい！！");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "うるせェェェェェー弁護士を呼べェェェーッ");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "コントロール室はこの先どっチュへ行けばい…いいんでチュか？");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "オラオラオラオラオラオラオラオラオラオラオラオラオラオラオラオラ");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "『ストーン・フリィィィィィィィィーッ！！』");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "ウェザー もう一度…もう一度話がしたい。あなたとそよ風の中で話がしたい");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "あなたの考えには希望がある暗闇なんかじゃあない…道がひとつしかなくてもそれにかすかでも考えがあるならそれはきっとうまくいく道");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        values.put("WORDS", "あたしも名前を付けるわ「ストーン・フリー」あたしは…この「石の海」から自由になる…聞こえた？『ストーン・フリー』よ…これが名前");
        rowId = db.insert(DatabaseOpenHelper.JORIN_TABLE, null, values);
        
        
        values.put("WORDS", "『フー・ファイターズ』！！わたしの事を呼ぶなら、そう呼べ！");
        rowId = db.insert(DatabaseOpenHelper.FF_TABLE, null, values);
        values.put("WORDS", "『フー･ファイターズ』と呼べと言ったはずだッ！知性なら、おまえたちより上だッ！");
        rowId = db.insert(DatabaseOpenHelper.FF_TABLE, null, values);
        values.put("WORDS", "ああ～「水」～「水」だァァァァ～「水」が必要だァァァ～のどが乾いて来たァァァーッ");
        rowId = db.insert(DatabaseOpenHelper.FF_TABLE, null, values);
        values.put("WORDS", "あたしの一番怖い事は…友達に「さよなら」を言う事すら考えられなくなる事だった。でも…最後の最後に…それを考える事ができた。");
        rowId = db.insert(DatabaseOpenHelper.FF_TABLE, null, values);
    
        
        values.put("WORDS", "祝福しろ。");
        rowId = db.insert(DatabaseOpenHelper.ANNASUI_TABLE, null, values);
        values.put("WORDS", "用意をするんだ。てめーがこの世に生まれて来たことを後悔する用意をだ！");
        rowId = db.insert(DatabaseOpenHelper.ANNASUI_TABLE, null, values);
        values.put("WORDS", "どっちなんだよ？したいの？したくないのか？それともなんか…君独特の趣味とかあるわけ？キスする顔の角度とかに好みとか。");
        rowId = db.insert(DatabaseOpenHelper.ANNASUI_TABLE, null, values);
        values.put("WORDS", "カエルの気持ちになって、一生追跡して来きな…！！");
        rowId = db.insert(DatabaseOpenHelper.ANNASUI_TABLE, null, values);
        values.put("WORDS", "おい！見たか！今の看板ッ！ミッキーがいなきゃあ、ディズニーじゃあねえッ！");
        rowId = db.insert(DatabaseOpenHelper.ANNASUI_TABLE, null, values);
        values.put("WORDS", "それだ…それがいいんだ。もっと見つめてほしいその瞳…もっと君に…");
        rowId = db.insert(DatabaseOpenHelper.ANNASUI_TABLE, null, values);
        values.put("WORDS", "『ダイバー・ダウン！』");
        rowId = db.insert(DatabaseOpenHelper.ANNASUI_TABLE, null, values);
        values.put("WORDS", "ところで…おれは全力であなたのお嬢さんを守ります。すでにのっぴきならない事態に陥ったようだが、この闘いは生き抜く…だからお嬢さんとの結婚をお許しください");
        rowId = db.insert(DatabaseOpenHelper.ANNASUI_TABLE, null, values);
        
        

        Log.v("AddActivity", "RowID:" + rowId);
        // データベースから切断する
        databaseOpenHelper.close();
        if (rowId != -1) {
                finish();
        }
    }
}