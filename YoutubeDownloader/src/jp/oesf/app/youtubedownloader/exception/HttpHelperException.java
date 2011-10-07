package jp.oesf.app.youtubedownloader.exception;

/**
 * HTTP用例外処理クラス
 */
public class HttpHelperException extends Exception {
	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * HTTP用例外処理
	 * 
	 * @param e
	 *            例外情報
	 */
	public HttpHelperException(Throwable e) {
		super(e);
	}

	/**
	 * HTTP用例外処理
	 * 
	 * @param msg
	 *            例外メッセージ
	 */
	public HttpHelperException(String msg) {
		super(msg);
	}

	/**
	 * HTTP用例外処理
	 * 
	 * @param detail
	 *            詳細情報
	 * @param e
	 *            例外情報
	 */
	public HttpHelperException(String detail, Throwable e) {
		super(detail, e);
	}

}
