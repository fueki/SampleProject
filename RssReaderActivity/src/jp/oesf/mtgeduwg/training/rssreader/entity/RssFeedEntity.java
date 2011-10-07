/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.oesf.mtgeduwg.training.rssreader.entity;

public class RssFeedEntity {
    private long rssFeedId;
    private long urlId;
    private String senderName;
    private String url;
    private String title;
    private String description;
    private String publishDate;
    private String link;
    private String guide;
    public long getRssFeedId() {
        return rssFeedId;
    }
    public void setRssFeedId(long rssFeedId) {
        this.rssFeedId = rssFeedId;
    }
    public long getUrlId() {
        return urlId;
    }
    public void setUrlId(long urlId) {
        this.urlId = urlId;
    }
    public String getSenderName() {
        return senderName;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getGuid() {
        return guide;
    }
    public void setGuid(String guide) {
        this.guide = guide;
    }
}
