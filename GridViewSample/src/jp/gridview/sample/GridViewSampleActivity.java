package jp.gridview.sample;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GridViewSampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayList<Bitmap> list = load();
        BitmapAdapter adapter = new BitmapAdapter(getApplicationContext(),R.layout.list_item,list);
        
        GridView gridView = (GridView)findViewById(R.id.gridview1);
        gridView.setAdapter(adapter);
        
    }
    
    private ArrayList<Bitmap> load() {
    	ArrayList<Bitmap> list = new ArrayList<Bitmap>();
    	ContentResolver cr = getContentResolver();
    	Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    	
    	Log.e(" @@@@@@@@@@ ", "" + uri);
    	
    	Cursor c = managedQuery(uri, null, null, null, null);
    	c.moveToFirst();
    	
    	for(int i = 0; i<c.getCount(); i++){
    		long id = c.getLong(c.getColumnIndexOrThrow("_id"));
    		Bitmap bmp = MediaStore.Images.Thumbnails.getThumbnail(cr, id, MediaStore.Images.Thumbnails.MICRO_KIND, null);
    		list.add(bmp);
    		c.moveToNext();
    	}
    	
		return list;
	}
}