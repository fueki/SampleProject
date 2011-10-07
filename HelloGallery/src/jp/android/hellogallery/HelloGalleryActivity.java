package jp.android.hellogallery;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HelloGalleryActivity extends Activity {
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	
	   /** Called when the activity is first created. */
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.main);
	     Gallery g = (Gallery) findViewById(R.id.gallery);
	     g.setAdapter(new ImageAdapter(this));
	     g.setOnItemClickListener(new OnItemClickListener() {
	         public void onItemClick(AdapterView parent, View v, int position, long id) {
	             Toast.makeText(HelloGalleryActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	         }
	     });
	 }

	 public class ImageAdapter extends BaseAdapter {
	     int mGalleryItemBackground;
	     private Context mContext;
	     private Integer[] mImageIds = {
	             R.drawable.ak,
	             R.drawable.ff,
	             R.drawable.jj,
	             R.drawable.jjl,
	             R.drawable.jjr,
	             R.drawable.kjj,
	             R.drawable.kr,
	             R.drawable.tp
	     };
	     public ImageAdapter(Context c) {
	         mContext = c;
	         TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
	         mGalleryItemBackground = a.getResourceId(
	                 R.styleable.Gallery1_android_galleryItemBackground, 0);
	         a.recycle();
	     }
	     public int getCount() {
	         return mImageIds.length;
	     }
	     public Object getItem(int position) {
	         return position;
	     }
	     public long getItemId(int position) {
	    	 
	    	 ImageView image = new ImageView(HelloGalleryActivity.this);
	    	 image.setImageResource(mImageIds[position]);
	    	 setContentView(image,new LayoutParams(WC,WC));
	         return position;
	     }
	     public View getView(int position, View convertView, ViewGroup parent) {
	         ImageView i = new ImageView(mContext);
	         i.setImageResource(mImageIds[position]);
	         i.setLayoutParams(new Gallery.LayoutParams(150, 100));
	         i.setScaleType(ImageView.ScaleType.FIT_XY);
	         i.setBackgroundResource(mGalleryItemBackground);
	         return i;
	     }
	 }
}