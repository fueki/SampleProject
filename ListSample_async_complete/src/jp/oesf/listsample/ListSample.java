package jp.oesf.listsample;

import jp.oesf.listsample.model.RowModel;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListSample extends ListActivity {
	private static final String TAG = "ListSample";

	private TableAdapter adpter;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//TODO No.01 タイトルバーにインジケータを表示させる
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.list);

        adpter = new TableAdapter(this);
        setListAdapter(adpter);

        //RowModelの作成
        //リソースからテキスト配列を取得する
        String titles[] = getResources().getStringArray(R.array.titles);
        String summaries[] = getResources().getStringArray(R.array.summaries);
        Drawable loadingImage = getResources().getDrawable(R.drawable.ic_contact_picture);
        int num = titles.length;

        RowModel[] models = new RowModel[num];

        //RowModelを1行ごとに作成
        //配列に格納する
        for(int i = 0; i < num; i++){
        	RowModel row = new RowModel();
        	row.setTitle(titles[i]);
        	row.setSummary(summaries[i]);
        	row.setThumbnailImage(loadingImage);
        	adpter.add(row);
        	models[i] = row;
        }

        //TODO No.08 非同期処理呼び出し
        new LoadImageTask().execute(models);
    }

    class TableAdapter extends ArrayAdapter<RowModel>{
		public TableAdapter(Context context) {
			super(context, R.layout.list_row);
		}

		//表示するリスト内の1行毎の各データ要素をセット
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			RowModel item = getItem(position);
			if(row == null){
				//Viewを作成する
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.list_row_rich, null);
			}

			if( item != null){

				// Image
				ImageView imageView = (ImageView)row.findViewById(R.id.image_thumbnail);
				if( imageView != null){
					imageView.setImageDrawable(item.getThumbnailImage());
				}

				//Title
				TextView textTitle = (TextView)row.findViewById(R.id.text_title);
				if(textTitle != null){
					textTitle.setText(item.getTitle());
				}

				// summary
				TextView textSummary = (TextView)row.findViewById(R.id.text_summary);
				if(textSummary != null){
					textSummary.setText(item.getSummary());
				}
			}
			return row;
		}
    }

    /**
     * 画像取得処理
     * 	スリープをかけて時間のかかる重い処理を疑似的に実装
     * @return
     */
    private Drawable getImage(){

    	try {
    		Thread.sleep(1000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	Log.v(TAG, "getImage()");
    	return getResources().getDrawable(R.drawable.icon);
    }

    /**
     * イメージ取得タスク
     * @author kobayashi.akihiro
     *
     */
    class LoadImageTask extends AsyncTask<RowModel, Void, Void>{

    	/**
    	 * バックグランド処理の直前の処理
    	 */
    	@Override
    	protected void onPreExecute() {
    		//TODO No.02インジケータを表示する
    		setProgressBarVisibility(true);
    	}

    	/**
    	 * バックグラウンドで実行する処理
    	 */
		@Override
		protected Void doInBackground(RowModel... arg0) {
			//TODO No.03 引数からデータを1件ずつ読み込み、RowModelのDrawableを設定する
			//キャンセルされている場合は処理を中断する
			//isCancelled()メソッドでキャンセル状況を取得できる
			for(RowModel rowModel : arg0){
				if(isCancelled()){
					Log.v("ListSample", "LoadImageTask#doInBackground() canceled.");
					break;
				}
				// TODO No.04 サムネイルイメージ取得
				rowModel.setThumbnailImage(getImage());

				// TODO No.05 更新通知
				publishProgress((Void)null);

			}
			return null;
		}

		/**
		 * バックグラウンド実行完了時の処理
		 */
		@Override
		protected void onPostExecute(Void result) {
			//TODO No.06 インジケータを非表示する
			setProgressBarIndeterminateVisibility(false);
		}

		/**
		 * バックグラウンドの進捗状況の更新
		 */
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO No.07 データの変更通知
			adpter.notifyDataSetChanged();
		}
    }
}