package jp.oesf.app.youtubedownloader.service;
/**
 * サービスのインターフェイス.
 */
interface IDownloadService {
    /**
     * ダウンロードサービス
     */
    String downloadFile(String url, String title);
 }
