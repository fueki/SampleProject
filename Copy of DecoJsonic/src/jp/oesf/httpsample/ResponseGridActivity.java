package jp.oesf.httpsample;

import java.util.ArrayList;

import org.json.JSONException;

import jp.oesf.httpsample.bean.DecoBean;
import jp.oesf.httpsample.constant.Constants;
import jp.oesf.httpsample.grid.BitmapAdapter;
import jp.oesf.httpsample.helper.CloudAccessManager;
import jp.oesf.httpsample.helper.JsonHelper;
import jp.oesf.httpsample.list.ListViewItem;
import jp.oesf.httpsample.util.HttpUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

public class ResponseGridActivity extends Activity {
	
	private static ListViewItem item = null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_main);
 
        ArrayList<Bitmap> list = load();
        BitmapAdapter adapter = new BitmapAdapter(
                getApplicationContext(), R.layout.grid_item,list);
 
        GridView gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(adapter);
 
    }
 
    private ArrayList<Bitmap> load() {
        ArrayList<Bitmap> list = new ArrayList<Bitmap>();
        
        String url = null;
		String resultJson = CloudAccessManager.getDecos("search");
		try {
			ArrayList<DecoBean> decos = JsonHelper.decodeDecos(resultJson);
			
			for(DecoBean bean:decos){
				item = new ListViewItem();
				
				url = bean.url;
				
				item.jtext = bean.file_name;
				Bitmap bmp = HttpUtil.getImage(Constants.HOST + url);
				list.add(bmp);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("HttpSample", "onClickGetDecoButton", e);
		}
        return list;
    }
}
