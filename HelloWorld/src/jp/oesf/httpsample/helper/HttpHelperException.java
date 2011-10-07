package jp.oesf.httpsample.helper;

public class HttpHelperException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public HttpHelperException(Throwable e) {
		super(e);
	}

	public HttpHelperException(String msg) {
		super(msg);
	}

	public HttpHelperException(String detail, Throwable e) {
		super(detail, e);

	}

}
