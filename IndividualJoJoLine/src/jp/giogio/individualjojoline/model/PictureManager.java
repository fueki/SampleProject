package jp.giogio.individualjojoline.model;

import jp.giogio.individualjojoline.R;


/**
 * 画像情報を管理します。
 */
public class PictureManager {
	/**
	 * 画像情報の選択が変更された時の通知を受け取るためのリスナーです。
	 */
	public interface OnSelectionChangeListener {
		/**
		 * 画像情報の選択状態が変更された時に発生します。
		 *
		 * @param old 前に選択されていた情報のインデックス。
		 * @param now 新たに選択されていた情報のインデックス。
		 */
		void onSelectionChanged( int old, int now );
	}

	/**
	 * 選択されている画像情報のインデックス。
	 */
	private int mCurrent;

	/**
	 * 画像情報のコレクション。
	 */
	private Picture[] mPictures = new Picture[] {
		new Picture(R.drawable.ak,R.string.araki_st,R.string.araki),
		new Picture(R.drawable.jj, R.string.jonasan_st,R.string.jonasan),
		new Picture(R.drawable.josefu, R.string.josef_st,R.string.josef),
		new Picture(R.drawable.joutarou, R.string.joutarou_st,R.string.joutarou),
		new Picture(R.drawable.kjj, R.string.jousuke_st,R.string.jousuke),
		new Picture(R.drawable.jjr, R.string.joruno_st,R.string.joruno),
		new Picture(R.drawable.jjl, R.string.jorin_st,R.string.jorin),
		new Picture(R.drawable.speedw, R.string.speed_st,R.string.speed),
		new Picture(R.drawable.faq20g09s, R.string.shutoro_st,R.string.shutoro),
		new Picture(R.drawable.kakyoin, R.string.kakyouin_st,R.string.kakyouin),
		new Picture(R.drawable.porunare, R.string.porunarefu_st,R.string.porunarefu),
		new Picture(R.drawable.dio, R.string.dio_st,R.string.dio),
		new Picture(R.drawable.kira, R.string.kira_st,R.string.kira),
		new Picture(R.drawable.okuyasu, R.string.okuyasu_st,R.string.okuyasu),
		new Picture(R.drawable.rohan, R.string.rohan_st,R.string.rohan),
		new Picture(R.drawable.butyarathi, R.string.butya_st,R.string.butya),
		new Picture(R.drawable.misuta, R.string.misuta_st,R.string.misuta),
		new Picture(R.drawable.puroshu, R.string.puroshu_st,R.string.puroshu),
		new Picture(R.drawable.ff, R.string.ff_st,R.string.ff),
		new Picture(R.drawable.anasui, R.string.anasui_st,R.string.anasui),
	};

	/**
	 * サムネイル画像のリソース識別子コレクション。
	 */
	private int[] mThumbnailIds;

	/**
	 * コンテンツ情報の選択が変更された時の通知を受け取るためのリスナー。
	 */
	private OnSelectionChangeListener mOnSelectionChange;

	/**
	 * 画像情報の総数を取得します。
	 *
	 * @return 総数。
	 */
	public int getCount() {
		return this.mPictures.length;
	}

	/**
	 * 選択されている画像のインデックスを取得します。
	 *
	 * @return インデックス。
	 */
	public int getCurrentIndex() {
		return this.mCurrent;
	}

	/**
	 * 画像情報を取得します。
	 *
	 * @param index 画像情報のインデックス。
	 *
	 * @return 画像情報。
	 */
	public Picture getPicture( int index ) {
		return this.mPictures[ index ];
	}

	/**
	 * 画像情報を取得します。
	 *
	 * @return 現在、選択されている画像情報。
	 */
	public Picture getPicture() {
		return this.mPictures[ this.mCurrent ];
	}

	/**
	 * サムネイル画像のリソース識別子コレクションを取得します。
	 *
	 * @return リソース識別子コレクション。
	 */
	public int[] getThumbnailIds() {
		if( this.mThumbnailIds == null ) {
			int     count  = this.mPictures.length;
			int[]   ids    = new int[ count ];

			for( int i = 0; i < count; ++i ) {
				ids[ i ] = this.mPictures[ i ].getThumbnailId();
			}

			this.mThumbnailIds = ids;
		}

		return this.mThumbnailIds;
	}

	/**
	 * 画像情報の選択状態を次へ切り替えます。
	 */
	public void next() {
		int index = this.mCurrent + 1;
		if( index == this.mPictures.length ) {
			this.select( 0 );

		} else {
			this.select( index );
		}
	}

	/**
	 * 画像情報の選択状態を前へ切り替えます。
	 */
	public void prev() {
		int index = this.mCurrent - 1;
		if( index < 0 ) {
			this.select( this.mPictures.length - 1 );

		} else {
			this.select( index );
		}
	}

	/**
	 * 画像情報の選択状態が更新された時に発生します。
	 *
	 * @param old 前に選択されていた情報のインデックス。
	 * @param now 新たに選択されていた情報のインデックス。
	 */
	private void onSelectionChanged( int old, int now ) {
		if( this.mOnSelectionChange != null ) {
			this.mOnSelectionChange.onSelectionChanged( old, now );
		}
	}

	/**
	 * 画像情報の選択状態を変更します。
	 *
	 * @param index インデックス。
	 */
	public void select( int index ) {
		if( index != this.mCurrent && 0 <= index && index < this.mPictures.length ) {
			int old = this.mCurrent;
			this.mCurrent = index;
			this.onSelectionChanged( old, index );

		} else {
			this.onSelectionChanged( index, index );
		}
	}

	/**
	 * 画像情報の選択が変更された時の通知を受け取るためのリスナーを設定します。
	 *
	 * @param listener リスナー。
	 */
	public void setOnSelectionChangeListener( OnSelectionChangeListener listener ) {
		this.mOnSelectionChange = listener;
	}
}
