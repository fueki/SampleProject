package jp.gr.java_conf.akabeko.testimagegallery.model;

/**
 * 画像を表します。
 */
public class Picture {
	/**
	 * 画像のリソース識別子。
	 */
	private int mResourceId;

	/**
	 * サムネイル画像の識別子。
	 */
	private int mThumbnailId;

	/**
	 * 画像の題名。
	 */
	private String mTitle;

	/**
	 * インスタンスを初期化します。
	 *
	 * @param resourceId  画像のリソース識別子。
	 * @param thumbnailId 
	 * @param title       画像の題名。
	 * @param author      作者・撮影者。
	 * @param url         画像の URL。
	 */
	public Picture(int thumbnailId, String title ) {
//		this.mResourceId  = resourceId;

		this.mThumbnailId = thumbnailId;

		this.mTitle       = title;
	}

	/**
	 * 画像のリソース識別子を取得します。
	 *
	 * @return 識別子。
	 */
	public int getResourceId() {
		return this.mResourceId;
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
	public String getTitle() {
		return this.mTitle;
	}
}
