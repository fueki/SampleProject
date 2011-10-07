package jp.oesf.tandd;

import java.util.ArrayList;
import java.util.List;

import jp.oesf.tandd.model.FeedEntity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TandDActivity extends ListActivity {
	//private static final Context context = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);		
	}
	
	public void onResume() {
		super.onResume();
		
		if(isConnected(this)){
			TandDDB ddb = new TandDDB(this);
			ddb.deleteALLDBdata();
			new AsyncTaskBack(this).execute("makeData");
//			makeData();
		}else {
			Toast.makeText(this, "オフラインです", Toast.LENGTH_LONG).show();
		}
//		initList();
	}
	
	/* 現在のネットワーク接続状況を調べる */
	public static boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if( ni != null ){
            boolean flg = cm.getActiveNetworkInfo().isConnected();
            return flg;
        }
        return false;
	}
	
	public void makeData(){
		//通信データを取得
		AccessOesf ao = new AccessOesf();
		String xml = ao.conectOesf(this);
	
		ArrayList<FeedEntity> rssList = null;
		//xml解析データをリストに入れる
		if(xml != null) {
			Analyze_XML analyze_XML = new Analyze_XML();
			rssList = analyze_XML.parseSax(this, xml);
		}
		
		//データベースにリストを保存
		if(rssList != null && rssList.size() > 0) {
			TandDDB db = new TandDDB(this);
			db.addAll(rssList);
		}
	}
			
	class TableAdapter extends ArrayAdapter<FeedEntity> {
		public TableAdapter(Context context) {
			super(context, R.layout.list_row);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			FeedEntity item = getItem(position);
			if (row == null) {
				// Viewを作成する
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.list_row, null);
			}

			if (item != null) {

				// Title
				TextView textTitle = (TextView) row
						.findViewById(R.id.textTitle);
				if (textTitle != null) {
					textTitle.setText(item.getTitle());
				}

				// summary
				TextView textSummary = (TextView) row
						.findViewById(R.id.textSummary);
				if (textSummary != null) {
					textSummary.setText(item.getDescription());
				}

				// date
				TextView textdate = (TextView) row.findViewById(R.id.textDate);
				if (textdate != null) {
					textdate.setText(item.getPubDate());
				}
			}
			return row;
		}

		@Override
		public long getItemId(int position) {
			return Long.parseLong(getItem(position).getId());
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		Log.v("", "pos:" + position+" id:" + id);
		
		Intent intent = new Intent(this, Detail.class);
		intent.putExtra("id", id);
		startActivity(intent);
	}
	
	private void initList()
	{
		TableAdapter adapter = new TableAdapter(this);
		setListAdapter(adapter);
		
		List<FeedEntity> list = new ArrayList<FeedEntity>();
		TandDDB db = new TandDDB(this);
		list = db.getData();
		
		for(FeedEntity entity:list)
		{
			adapter.add(entity);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main_menu, menu);
	
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		TableAdapter adapter = new TableAdapter(this);
		setListAdapter(adapter);
		
		TandDDB ddb = new TandDDB(this);
		
		
		/* ネットに接続していたら、更新する */
		if(isConnected(this)){
			ddb.deleteALLDBdata();
			adapter.clear();
			new AsyncTaskBack(this).execute("makeData");
//			makeData();		//xmlデータ作成
		}else {
			Toast.makeText(this, "オフラインです", Toast.LENGTH_LONG).show();
		}
//		initList();		
		return true;
	}
	
	class AsyncTaskBack extends AbsLoadingTask{
		    
		public AsyncTaskBack(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		protected Boolean doInBackground(String...strings){
			makeData();
			
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
	       	return false;
	    }
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			initList();
		}
	}
}
