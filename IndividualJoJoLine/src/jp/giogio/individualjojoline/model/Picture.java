package jp.giogio.individualjojoline.model;

import android.util.Log;

/**
 * 画像を表します。
 */
public class Picture {
	/**
	 * nama識別子。
	 */
	private int mName;

	/**
	 * サムネイル画像の識別子。
	 */
	private int mThumbnailId;

	/**
	 * スタンド名。
	 */
	private int mTitle;

	/**
	 * インスタンスを初期化します。
	 *
	 * @param thumbnailId 画像のリソース識別子。
	 * @param title       画像の題名。
	 * @param string 
	 */
	public Picture(int thumbnailId, int title, int name ) {
		this.mThumbnailId = thumbnailId;
		this.mTitle       = title;
		this.mName        = name;
		
	}

	/**
	 * 画像のリソース識別子を取得します。
	 *
	 * @return 識別子。
	 */
	public int getName() {
		return this.mName;
	}

	/**
	 * サムネイル画像のリソース識別子を取得します。
	 *
	 * @return 識別子。
	 */
	public int getThumbnailId() {
		return this.mThumbnailId;
	}

	/**
	 * 画像の題名を取得します。
	 *
	 * @return 題名。
	 */
	public int getTitle() {
		return this.mTitle;
	}
}
