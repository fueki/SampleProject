/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.oesf.mtgeduwg.training.rssreader.helper;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;

/**
 * Http通信のヘルパークラス。
 */
public class HttpHelper extends DefaultHttpClient {
    /**
     * RSSフィードを取得可能なURLかどうか判断します。
     * 
     * @param url
     * @return boolean 取得可能:true
     */
    public boolean isUrlAddressExist(String url) {
        InputStream inputStream = null;
        try {
            inputStream = getResponseContent(url);
            return new XmlHelper().isRssFeed(inputStream);
        } catch (Exception exception) {
            Log.e(exception.getClass().getName(), exception.getMessage(), exception);
            return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                    Log.e(ioException.getClass().getName(), ioException.getMessage(), ioException);
                }
            }
        }
    }
    /**
     * HttpResponseコンテンツのInputStreamを取得します。
     * 
     * @param url
     * @return InputStream
     * @throws HttpHelperException
     */
    public InputStream getResponseContent(String url) throws HttpHelperException {
        HttpResponse response;
        try {
            HttpGet httpGet = new HttpGet(url);
            response = execute(httpGet);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new HttpHelperException(illegalArgumentException);
        } catch (IllegalStateException illegalStatException) {
            throw new HttpHelperException(illegalStatException);
        } catch (ClientProtocolException clientProtocolException) {
            throw new HttpHelperException(clientProtocolException);
        } catch (IOException ioException) {
            throw new HttpHelperException(ioException);
        }
        if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
            try {
            InputStream inputStream = response.getEntity().getContent();
                Log.v("HttpHelper", "Succeeded in retrieving the InputStream.");
                return inputStream;
            } catch (IllegalStateException illegalStateException) {
                throw new HttpHelperException(illegalStateException);
            } catch (IOException ioException) {
                throw new HttpHelperException(ioException);
            }
        } else {
            throw new HttpHelperException();
        }
    }
}
