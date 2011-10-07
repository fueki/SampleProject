package jp.rss.rssreaderactivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jp.oesf.mtgeduwg.training.rssreader.entity.RssFeedEntity;
import jp.oesf.mtgeduwg.training.rssreader.helper.DatabaseOpenHelper;
import jp.oesf.mtgeduwg.training.rssreader.helper.HttpHelper;
import jp.oesf.mtgeduwg.training.rssreader.helper.XmlHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RssFeedRegister {
    private Context context;
    public RssFeedRegister(Context context) {
        this.context = context;
    }
    public boolean registration(String url) {
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(context);
        SQLiteDatabase database = null;
        List<RssFeedEntity> rssFeedEntities;
        InputStream inputStream = null;
        ContentValues contentValues;
        try {
            inputStream = new HttpHelper().getResponseContent(url);
            rssFeedEntities = new XmlHelper().parseRssFeeds(inputStream);
            database = databaseOpenHelper.getWritableDatabase();
            database.beginTransaction();
            for (RssFeedEntity rssFeed : rssFeedEntities) {
                contentValues = new ContentValues();
                contentValues.put("title", rssFeed.getTitle());
                contentValues.put("publish_date", rssFeed.getPublishDate());
                contentValues.put("sender_name", rssFeed.getSenderName());
                contentValues.put("description", rssFeed.getDescription());
                database.insert("RSS_FEED", null, contentValues);
            }
            database.setTransactionSuccessful();
            return true;
        } catch (Exception exception) {
            Log.e(exception.getClass().getName(), exception.getMessage(), exception);
            return false;
        } finally {
            if (database != null) {
                database.endTransaction();
                database.close();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                }
            }
        }
    }
}
