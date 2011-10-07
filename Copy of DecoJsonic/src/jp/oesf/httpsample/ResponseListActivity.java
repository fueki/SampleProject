package jp.oesf.httpsample;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;

import jp.oesf.httpsample.bean.DecoBean;
import jp.oesf.httpsample.constant.Constants;
import jp.oesf.httpsample.helper.CloudAccessManager;
import jp.oesf.httpsample.helper.JsonHelper;
import jp.oesf.httpsample.list.ListViewItem;
import jp.oesf.httpsample.list.MyArrayAdapter;
import jp.oesf.httpsample.util.HttpUtil;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class ResponseListActivity extends Activity {

	private static MyArrayAdapter adapter = null;
	private ListView listView = null;
	
	private static ListViewItem item = null;
	private static Date d = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		adapter = new MyArrayAdapter(this);
		getImage();
		
		listView =  (ListView) findViewById(R.id.list);
		listView.setAdapter(adapter);
		listView.setEmptyView(findViewById(R.id.empty));

	}
	
	public static void getImage(){
		String url = null;
		String resultJson = CloudAccessManager.getDecos("search");
		try {
			ArrayList<DecoBean> decos = JsonHelper.decodeDecos(resultJson);
			
//			StringBuilder builder = new StringBuilder();
			for(DecoBean bean:decos){
				item = new ListViewItem();
				d = new Date();
				
//				builder.append(bean.toString());
//				builder.append(Constants.BR);
				url = bean.url;
				
				item.jtext = bean.file_name;
				item.image = HttpUtil.getImage(Constants.HOST + url);
				adapter.add(item);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("HttpSample", "onClickGetDecoButton", e);
		}
	}
}
