package jp.gr.java_conf.akabeko.testimagegallery.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * CoverFlow と画像を関連付けるためのアダプターです。
 */
public class CoverFlowImageAdapter extends BaseAdapter {
	/**
	 * 元画像と反射エフェクト間の距離。
	 */
	private static int REFLECTION_GAP = 4;

	/**
	 * ビットマップの読み込み設定。
	 */
	private BitmapFactory.Options mOptions = new BitmapFactory.Options();

	/**
	 * コンテキスト。
	 */
	private Context mContext;

	/**
	 * サムネイル画像を動的に生成することを示す値。
	 */
	private boolean mIsMakeThumbnail;

	/**
	 * 反射エフェクトを使用することを示す値。
	 */
	private boolean mIsUserEffect;

	/**
	 * レイアウト情報。
	 */
	private Gallery.LayoutParams mLayoutParams;

	/**
	 *  画像のリソース ID コレクション。
	 */
	private int[] mResourceIds;

	/**
	 * アイテムの幅と高さを指定して、インスタンスを初期化します。
	 *
	 * @param context         コンテキスト。
	 * @param ids             画像のリソース ID コレクション。
	 * @param width           アイテムの幅。
	 * @param height          アイテムの高さ。
	 * @param isMakeThumbnail サムネイル画像を動的に生成する場合は true。それ以外は false。
	 * @param isUserEffect    反射エフェクトを使用する場合は true。それ以外は false。
	 */
	public CoverFlowImageAdapter( Context context, int[] ids, int width, int height, boolean isMakeThumbnail, boolean isUserEffect ) {
		this.mContext         = context;
		this.mResourceIds     = ids;
		this.mIsMakeThumbnail = isMakeThumbnail;
		this.mIsUserEffect    = isUserEffect;
		this.mLayoutParams    = new Gallery.LayoutParams( width, height );
	}

	/**
	 * リソース ID から画像を取得します。
	 *
	 * @param id リソース ID。
	 *
	 * @return 成功時は Bitmap インスタンス。それ以外は null 参照。
	 */
	private Bitmap decodeBitmap( int id ) {
		if( this.mIsMakeThumbnail ) {
			this.mOptions.inJustDecodeBounds = true;
			BitmapFactory.decodeResource( this.mContext.getResources(), id, this.mOptions );

			int width  = ( this.mOptions.outWidth  / this.mLayoutParams.width  ) + 1;
			int height = ( this.mOptions.outHeight / this.mLayoutParams.height ) + 1;
			int scale  = Math.max( width, height );

			this.mOptions.inJustDecodeBounds = false;
			this.mOptions.inSampleSize = scale;

			return BitmapFactory.decodeResource( this.mContext.getResources(), id, this.mOptions );

		} else {
			return BitmapFactory.decodeResource( this.mContext.getResources(), id );
		}
	}

	/**
	 * アイテムの総数を取得します。
	 *
	 * @return アイテム数。
	 */
	public int getCount() {
		return this.mResourceIds.length;
	}

	/**
	 * 指定されたインデックスの指すアイテムを取得します。
	 *
	 * @param position インデックス。
	 *
	 * @return アイテム。
	 */
	public Object getItem( int position ) {
		return position;
	}

	/**
	 * 指定されたインデックスの指すアイテムの識別子を取得します。
	 *
	 * @param position インデックス。
	 *
	 * @return 識別子。
	 */
	public long getItemId( int position ) {
		return position;
	}

	/**
	 * ビューの内容を取得します。
	 *
	 * @param position    ビュー内における、アイテムの表示位置を示すインデックス。
	 * @param convertView 表示領域となるビュー。
	 * @param parent      親となるビュー。
	 */
	public View getView( int position, View convertView, ViewGroup parent ) {
		ImageView view = new ImageView( this.mContext );

		Bitmap bmp = decodeBitmap( this.mResourceIds[ position ] );
		view.setLayoutParams( this.mLayoutParams );
		view.setScaleType( ScaleType.CENTER_INSIDE );
		view.setImageBitmap( this.mIsUserEffect ? this.makeReflectedImage( bmp, REFLECTION_GAP ) : bmp );

		BitmapDrawable drawable = ( BitmapDrawable )view.getDrawable();
		drawable.setAntiAlias( true );

		return view;
	}

	/**
	 * 画像の下部に反射エフェクトを付けた Bitmap を生成します。
	 *
	 * @param src 元となる画像。
	 * @param gap 元画像と反射エフェクト間の距離。
	 *
	 * @return 成功時は Bitmap インスタンス。それ以外は null 参照。
	 */
	private Bitmap makeReflectedImage( Bitmap src, int gap ) {
		Matrix matrix = new Matrix();
		matrix.preScale( 1, -1 );

		int    width      = src.getWidth();
		int    height     = src.getHeight();
		int    destHeight = height + height / 2;
		Bitmap effect     = Bitmap.createBitmap( src, 0, height / 2, width, height / 2, matrix, false );
		//Bitmap dest       = Bitmap.createBitmap( width, destHeight, Config.ARGB_4444 );
		Bitmap dest       = Bitmap.createBitmap( width, destHeight, Config.ARGB_8888 );
		Canvas canvas     = new Canvas( dest );

		canvas.drawBitmap( src, 0, 0, null );
		canvas.drawRect( 0, height, width, height + gap, new Paint() );
		canvas.drawBitmap( effect, 0, height + gap, null );

		Paint paint  = new Paint();
		paint.setShader( new LinearGradient( 0, height, 0, destHeight + gap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP ) );
		paint.setXfermode( new PorterDuffXfermode( Mode.DST_IN ) );
		canvas.drawRect( 0, height, width, destHeight + gap, paint );

		return dest;
	}
}