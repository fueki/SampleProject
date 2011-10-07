package jp.gr.java_conf.akabeko.testimagegallery;

import jp.gr.java_conf.akabeko.testimagegallery.model.AppData;
import jp.gr.java_conf.akabeko.testimagegallery.model.Picture;
import jp.gr.java_conf.akabeko.testimagegallery.model.PictureManager;
import jp.gr.java_conf.akabeko.testimagegallery.model.PictureManager.OnSelectionChangeListener;
import jp.gr.java_conf.akabeko.testimagegallery.widget.ToggleChangeVisibleBehavior;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

/**
 * 写真を表示するための画面を表します。
 */
public class PictureActivity extends Activity implements OnClickListener, OnTouchListener, OnSelectionChangeListener {
	/**
	 * フリック操作とみなす移動距離。
	 */
	private static final int FLICK_DISTANCE = 64;

	/**
	 * フリック操作の起点となった X 軸の座標。
	 */
	private float mFlickBeginX;

	/**
	 * 画像情報を管理するインスタンス。
	 */
	private PictureManager mPictureManager;

	/**
	 * 選択されている画像のインデックス。
	 */
	private TextView mIndexTextView;

	/**
	 * コンテンツを表示するための View。
	 */
	private ImageView mImageView;

	/**
	 * 表示モードが原寸であることを示す値。
	 */
	private boolean mIsZoomOriginal;

	/**
	 * 選択されている画像のタイトル。
	 */
	private TextView mTitleTextView;

	/**
	 * 写真の表示方法を切り替えるためのボタン。
	 */
	private ImageButton mZoomButton;

	/**
	 * プレーヤー領域の表示を切り替えるコントローラ。
	 */
	private ToggleChangeVisibleBehavior mPlayerAreaVisibleChanger;

	/**
	 * タイトル バーの表示を切り替えるコントローラ。
	 */
	private ToggleChangeVisibleBehavior mTitleBarVisibleChanger;

	/**
	 * コントロールを初期化します。
	 */
	private void initControls() {
		this.mIndexTextView = ( TextView  )this.findViewById( R.id.content_index );
		this.mTitleTextView = ( TextView  )this.findViewById( R.id.content_title );

		this.mImageView     = ( ImageView )this.findViewById( R.id.content_view  );
		this.mImageView.setOnTouchListener( this );

		( ( TextView )this.findViewById( R.id.content_count ) ).setText( String.format( "/%d", this.mPictureManager.getCount() ) );

		Animation fadeIn  = AnimationUtils.loadAnimation( this, R.anim.fade_in  );
		Animation fadeOut = AnimationUtils.loadAnimation( this, R.anim.fade_out );

		this.mTitleBarVisibleChanger = new ToggleChangeVisibleBehavior( this.findViewById( R.id.titlebar ) );
		this.mTitleBarVisibleChanger.setFadeAnimation( fadeIn, fadeOut );

		this.mPlayerAreaVisibleChanger = new ToggleChangeVisibleBehavior( this.findViewById( R.id.content_player ) );
		this.mPlayerAreaVisibleChanger.setFadeAnimation( fadeIn, fadeOut );

		this.mZoomButton = ( ImageButton )this.findViewById( R.id.content_zoom );
		this.mZoomButton.setOnClickListener( this );

		this.mIndexTextView.setOnClickListener( this );
		this.mTitleTextView.setOnClickListener( this );
		this.findViewById( R.id.content_prev  ).setOnClickListener( this );
		this.findViewById( R.id.content_next  ).setOnClickListener( this );
		this.findViewById( R.id.content_count ).setOnClickListener( this );

		this.updateSelectionInfo();
	}

	/**
	 * View がクリックされた時に発生します。
	 *
	 * @param v クリック対象の View。
	 */
	public void onClick( View v ) {
		switch( v.getId() ) {
		// 前へ
		case R.id.content_prev:
			this.mPictureManager.prev();
			break;

		// 次へ
		case R.id.content_next:
			this.mPictureManager.next();
			break;

		// 表示モード
		case R.id.content_zoom:
			this.mIsZoomOriginal = !this.mIsZoomOriginal;
			if( this.mIsZoomOriginal ) {
				this.mZoomButton.setImageResource( R.drawable.icon_zoom_out );
				this.mImageView.setScaleType( ScaleType.CENTER );

			} else {
				this.mZoomButton.setImageResource( R.drawable.icon_zoom_in );
				this.mImageView.setScaleType( ScaleType.FIT_CENTER );
			}
			break;

		// タイトル バー上のクリックでリストに戻る
		case R.id.content_title:
		case R.id.content_index:
		case R.id.content_count:
			this.setResult( RESULT_OK );
			this.finish();
			break;
		}
	}

	/**
	 * 画面が生成される時に発生します。
	 *
	 * @param savedInstanceState 保存されたインスタンスの状態。
	 */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		this.setContentView( R.layout.picture );

		this.mPictureManager = ( ( AppData )this.getApplication() ).getPicture();
		this.mPictureManager.setOnSelectionChangeListener( this );
		this.initControls();
	}

	/**
	 * 画面が破棄される時に発生します。
	 */
	@Override
	protected void onDestroy() {
		this.mImageView.setImageBitmap( null );

		super.onDestroy();

		if( this.mPictureManager != null ) {
			this.mPictureManager.setOnSelectionChangeListener( null );
			this.mPictureManager = null;
		}
	}

	/**
	 * 画像の選択状態が変更された時に発生します。
	 *
	 * @param old 前に選択されていた画像のインデックス。
	 * @param now 新たに選択された画像のインデックス。
	 */
	public void onSelectionChanged( int old, int now ) {
		this.updateSelectionInfo();
	}

	/**
	 * タッチ操作が行われた時に発生します。
	 *
	 * @param v     操作の行われた View。
	 * @param event イベント データ。
	 *
	 * @return タッチ操作を他のコントロールに伝搬しない場合は true。それ以外は false。
	 */
	public boolean onTouch( View v, MotionEvent event ) {
		switch( event.getAction() ) {

		// 起点
		case MotionEvent.ACTION_DOWN:
			this.mFlickBeginX = event.getX();
			break;

		// 終点
		case MotionEvent.ACTION_UP:
			if( event.getX() < this.mFlickBeginX - FLICK_DISTANCE ) {
				this.mPictureManager.next();

			} else if( this.mFlickBeginX + FLICK_DISTANCE < event.getX() ) {
				this.mPictureManager.prev();

			} else {
				this.toggleVisibleToolBar();
			}

			break;

		// キャンセル ( UP/DOWN が同時 ) の場合は、フリックとして扱わない
		case MotionEvent.ACTION_CANCEL:
			this.toggleVisibleToolBar();
			break;
		}

		return true;
	}

	/**
	 * ツールバーの表示状態をトグル形式で切り替えます。
	 */
	private void toggleVisibleToolBar() {
		this.mPlayerAreaVisibleChanger.toggle();
		this.mTitleBarVisibleChanger.toggle();
	}

	/**
	 * 選択されたコンテンツの情報を更新します。
	 */
	private void updateSelectionInfo() {
		Picture picture = this.mPictureManager.getPicture();

		this.mIndexTextView.setText( String.valueOf( this.mPictureManager.getCurrentIndex() + 1 ) );
		this.mTitleTextView.setText( picture.getTitle()                 );

		this.mImageView.setImageBitmap( null );
		this.mImageView.setScaleType( this.mIsZoomOriginal ? ScaleType.CENTER : ScaleType.FIT_CENTER );
		this.mImageView.setImageResource( picture.getResourceId() );
	}
}
