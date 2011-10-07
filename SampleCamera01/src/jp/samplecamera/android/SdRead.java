package jp.samplecamera.android;

import java.io.File;  
import java.util.ArrayList;  
import java.util.List;  
  
import android.app.Activity;  
import android.content.Context;  
import android.graphics.BitmapFactory;  
import android.os.Bundle;  
import android.os.Environment;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.GridView;  
import android.widget.ImageView;

public class SdRead extends Activity {
	private static final String APPLICATION_NAME = "map_google_image";
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
  
        setContentView(R.layout.main);  
          
        // SDカードのFileを取得  
        File imageFile = new File(Environment.getExternalStorageDirectory() + "/" + APPLICATION_NAME); 
        File file = new File(imageFile + "/" + APPLICATION_NAME);
        dirList.add(file.getPath());  
  
        // SDカード内のファイルを検索。  
        int m = 0;  
        int n = 0;  
        while(dirList.size() > m){  
            File subDir = new File((String) dirList.get(m));  
            String subFileName[] = subDir.list();  
            n = 0;  
            while(subFileName.length > n){  
                File subFile = new File(subDir.getPath() + "/" + subFileName[n]);  
                if(subFile.isDirectory()){  
                    dirList.add(subDir.getPath() + "/" + subFileName[n]);  
                }else if(subFile.getName().endsWith("jpg") || subFile.getName().endsWith("JPG")){  
                    imgList.add(subDir.getPath() + "/" + subFileName[n]);  
                }  
                n++;  
            }  
            m++;  
        }  
        GridView g = (GridView) findViewById(R.id.GridView01);  
        g.setAdapter(new ImageAdapter(this));  
    }  
  
    private List dirList = new ArrayList();  
    private List imgList = new ArrayList();  
  
    public class ImageAdapter extends BaseAdapter {  
        public ImageAdapter(Context c) {  
            mContext = c;  
        }  
  
        public int getCount() {  
            return imgList.size();  
        }  
  
        public Object getItem(int position) {  
            return position;  
        }  
  
        public long getItemId(int position) {  
            return position;  
        }  
  
        public View getView(int position, View convertView, ViewGroup parent) {  
            ImageView imageView;  
            if (convertView == null) {  
                imageView = new ImageView(mContext);  
                imageView.setLayoutParams(new GridView.LayoutParams(45, 45));  
                imageView.setAdjustViewBounds(true);  
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);  
                imageView.setPadding(8, 8, 8, 8);  
            } else {  
                imageView = (ImageView) convertView;  
            }  
            File f = new File((String) imgList.get(position));  
            BitmapFactory.Options bmOp = new BitmapFactory.Options();  
            bmOp.inSampleSize = 20;  
            imageView.setImageBitmap(BitmapFactory.decodeFile(f.getPath(),bmOp));  
            return imageView;  
        }  
  
        private Context mContext;  
    }  
}
