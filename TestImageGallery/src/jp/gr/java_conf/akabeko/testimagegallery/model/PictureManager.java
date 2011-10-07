package jp.gr.java_conf.akabeko.testimagegallery.model;

import jp.gr.java_conf.akabeko.testimagegallery.R;

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
//		new Picture( R.drawable.photo_01, R.drawable.photo_thumb_01, "屋久島の二代大杉"   ),
//		new Picture( R.drawable.photo_02, R.drawable.photo_thumb_02, "屋久島のいなか浜"   ),
//		new Picture( R.drawable.photo_03, R.drawable.photo_thumb_03, "種子島"             ),
//		new Picture( R.drawable.photo_04, R.drawable.photo_thumb_04, "大洗神社"           ),
//		new Picture( R.drawable.photo_05, R.drawable.photo_thumb_05, "熱川バナナワニ園"   ),
//		new Picture( R.drawable.photo_06, R.drawable.photo_thumb_06, "鬼押し出し園"       ),
//		new Picture( R.drawable.photo_07, R.drawable.photo_thumb_07, "日本民家園"         ),
//		new Picture( R.drawable.photo_08, R.drawable.photo_thumb_08, "皇居、一般参賀の日" ),
//		new Picture( R.drawable.photo_09, R.drawable.photo_thumb_09, "善光寺"             ),
//		new Picture( R.drawable.photo_10, R.drawable.photo_thumb_10, "大谷資料館の石切場" ),
		new Picture(R.drawable.photo_thumb_01, "屋久島の二代大杉"   ),
		new Picture(R.drawable.photo_thumb_02, "屋久島のいなか浜"   ),
		new Picture(R.drawable.photo_thumb_03, "種子島"             ),
		new Picture(R.drawable.photo_thumb_04, "大洗神社"           ),
		new Picture(R.drawable.photo_thumb_05, "熱川バナナワニ園"   ),
		new Picture(R.drawable.photo_thumb_06, "鬼押し出し園"       ),
		new Picture(R.drawable.photo_thumb_07, "日本民家園"         ),
		new Picture(R.drawable.photo_thumb_08, "皇居、一般参賀の日" ),
		new Picture(R.drawable.photo_thumb_09, "善光寺"             ),
		new Picture(R.drawable.photo_thumb_10, "大谷資料館の石切場" ),

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
