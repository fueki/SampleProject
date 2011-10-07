package jp.oesf.httpsample.list;

import jp.oesf.httpsample.R;
import jp.oesf.httpsample.R.id;
import jp.oesf.httpsample.R.layout;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<ListViewItem> {

	public MyArrayAdapter(Context context) {
		super(context, R.layout.listview_item, R.id.item_text);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);

		ListViewItem item = getItem(position);
		if (item != null) {
			ImageView imageView = (ImageView) view.findViewById(R.id.item_img);
			TextView textView = (TextView) view.findViewById(R.id.item_text);
			
			if (imageView != null && item.image != null) {
				imageView.setImageBitmap(item.image);
			}
			if (textView != null && item.jtext != null) {
				textView.setText((CharSequence) item.jtext);
			}
		}

		return view;
	}
}
