package net.dabits.android.nicoplayer.procs;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import net.dabits.android.nicoplayer.R;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class VideoListProc {
	private ArrayList<HashMap<String, String>> videoItems;
	private SimpleAdapter videoItemAdapter;
	private String[] from = new String[]{
		"totalView",
		"totalRes",
		"totalMylist",
		"date",
		"title",
		"description",
		"length",
		"thumbnailUrl"
	};
	private int[] to = new int[]{
		R.id.TextView02,
		R.id.TextView04,
		R.id.TextView06,
		R.id.TextView07,
		R.id.TextView08,
		R.id.TextView09,
		R.id.TextView10,
		R.id.ImageView01,
	};
	
	private ListActivity activity;
	
	public VideoListProc(ListActivity activity){
		this.activity = activity;
	}
	
	public void setVideoItems(ArrayList<HashMap<String, String>> videoItems){
		this.videoItems = videoItems;
	}
	
	public SimpleAdapter getVideoItemAdapter(){
		return this.videoItemAdapter;
	}
	
	public void draw(){
    	videoItemAdapter = new SimpleAdapter(activity, videoItems, R.layout.video_list_row, from, to);
        activity.setListAdapter(videoItemAdapter);
        videoItemAdapter.setViewBinder(new VideoItemViewBinder());
	}
    
    public class VideoItemViewBinder implements SimpleAdapter.ViewBinder{

    	public boolean setViewValue(View view, Object data, String textRepresentation) {
    		switch(view.getId()){
    			case R.id.ImageView01:
					try {
						URL url = new URL(textRepresentation);
						InputStream is = url.openStream();
	    	            Bitmap bm = BitmapFactory.decodeStream(is);
	    	            ((ImageView)view).setImageBitmap(bm);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
    		}
			return false;
		}
    	
    }
}
