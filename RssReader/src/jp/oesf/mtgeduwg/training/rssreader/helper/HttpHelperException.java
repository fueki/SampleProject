/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.oesf.mtgeduwg.training.rssreader.helper;

/**
 * HttpHelperで扱う例外クラス。
 */
public class HttpHelperException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public HttpHelperException() {}
    /**
     * @param detailMessage
     */
    public HttpHelperException(String detailMessage) {
        super(detailMessage);
    }
    /**
     * @param throwable
     */
    public HttpHelperException(Throwable throwable) {
        super(throwable);
    }
    /**
     * @param detailMessage
     * @param throwable
     */
    public HttpHelperException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
