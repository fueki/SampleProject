package jp.oesf.app.youtubedownloader.model;

import android.graphics.Bitmap;

/**
 * 行entityクラス
 */
public class RowModel {
	/** タイトル */
	private String title;
	/** サムネイルイメージ */
	private Bitmap thumbnailImage;
	/** サマリー */
	private String summary;
	/** URL情報 */
	private String url;
	/** サムネイルイメージのURL */
	private String thumbnailImageURL;
	/** ファイル名(フルパス) */
	private String fileName;
	/** ダウンロード終了フラグ */
	private boolean downloadFlag = false;

	/**
	 * サムネイルイメージのURL取得。
	 * 
	 * @return サムネイルイメージのURL
	 */
	public final String getThumbnailImageURL() {
		return thumbnailImageURL;
	}

	/**
	 * サムネイルイメージのURL設定。
	 * 
	 * @param thumbnailImageURL
	 *            サムネイルイメージの設定
	 */
	public final void setThumbnailImageURL(String thumbnailImageURL) {
		this.thumbnailImageURL = thumbnailImageURL;
	}

	/**
	 * タイトルの取得。
	 * 
	 * @return タイトル
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * タイトルの設定。
	 * 
	 * @param title
	 *            タイトル
	 */
	public final void setTitle(String title) {
		this.title = title;
	}

	/**
	 * サムネイルイメージの取得
	 * 
	 * @return サムネイルイメージ
	 */
	public final Bitmap getThumbnailImage() {
		return thumbnailImage;
	}

	/**
	 * サムネイルイメージの設定
	 * 
	 * @param thumbnailImage
	 *            サムネイルイメージ
	 */
	public final void setThumbnailImage(Bitmap thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	/**
	 * サマリーの取得
	 * 
	 * @return サマリー
	 */
	public final String getSummary() {
		return summary;
	}

	/**
	 * サマリーの設定
	 * 
	 * @param summary
	 *            サマリー
	 */
	public final void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * URLの取得
	 * 
	 * @return URL
	 */
	public final String getUrl() {
		return url;
	}

	/**
	 * URLの設定
	 * 
	 * @param url
	 *            URL
	 */
	public final void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ファイル名の取得
	 * 
	 * @return ファイル名
	 */
	public final String getFileName() {
		return fileName;
	}

	/**
	 * ファイル名の設定
	 * 
	 * @param fileName
	 *            ファイル名
	 */
	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * ダウンロード有無の取得
	 * 
	 * @return ダウンロード有無
	 */
	public final boolean isDownloadFlag() {
		return downloadFlag;
	}

	/**
	 * ダウンロード有無の設定
	 * 
	 * @param downloadFlag
	 *            ダウンロード有無
	 */
	public final void setDownloadFlag(boolean downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

}
