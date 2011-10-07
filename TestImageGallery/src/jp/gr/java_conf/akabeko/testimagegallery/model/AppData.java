package jp.gr.java_conf.akabeko.testimagegallery.model;

import android.app.Application;

/**
 * アプリケーション全体の状態を管理します。
 */
public class AppData extends Application {
	/**
	 * 画像情報を管理するためのオブジェクト インスタンス。
	 */
	private PictureManager mPictureManager = new PictureManager();

	/**
	 * 画像情報を取得します。
	 *
	 * @return 画像情報。
	 */
	public PictureManager getPicture() {
		return this.mPictureManager;
	}
}
