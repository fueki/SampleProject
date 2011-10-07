package jp.oesf.listsample;

import jp.oesf.listsample.model.RowModel;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListSample extends ListActivity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        TableAdapter adpter = new TableAdapter(this);
        setListAdapter(adpter);

        //RowModelの作成
        //リソースからテキスト配列を取得する
        String titles[] = getResources().getStringArray(R.array.titles);
        String summaries[] = getResources().getStringArray(R.array.summaries);
        Drawable image = getResources().getDrawable(R.drawable.icon);
        int num = titles.length;

        for(int i = 0; i < num; i++){
        	RowModel row = new RowModel();
        	row.setTitle(titles[i]);
        	row.setSummary(summaries[i]);
        	row.setThumbnailImage(image);
        	adpter.add(row);
        }
    }

    class TableAdapter extends ArrayAdapter<RowModel>{
        public TableAdapter(Context context) {
//            super(context, R.layout.list_row);
            super(context, R.layout.list);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            RowModel item = getItem(position);
            if(row == null){
                //Viewを作成する
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.list_row, null);
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
}