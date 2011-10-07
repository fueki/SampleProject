package com.tappli.android.listviewtest;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<ListViewItem> {
	/** ListViewの中で生成されたViewの数 */
	private static int viewCount = 0;

	public MyArrayAdapter(Context context) {
		super(context, R.layout.listview_item, R.id.item_text);
		// 第1引数 ... コンテキスト
		// 第2引数 ... 表示するレイアウト
		// 第3引数 ...
		// 第2引数の中のTextView(何もしなければ、このTextViewにListViewItemのtoString()の結果が表示される)
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		boolean created = false;
		if (convertView == null) {
			created = true;
			viewCount++;
		}
		
		Log.e(" @@@@@@ @@@@@@", "確認：");
		
		// 基本的なことは親に任せる
		View view = super.getView(position, convertView, parent);

		// この時点でviewにはlistview_itemを生成したものが出来ている。
		// TextViewはListViewItemのtoString()の結果が入っている。
		// ※ここで取得されるViewは、リストのアイテムごとには生成されず、
		// 　ListViewへ一度に表示されるアイテム数分のViewを使いまわすことになる点に注意

		// アプリ独自の表示内容に書き換えるために、情報を取得。
		ListViewItem item = getItem(position);
		if (item != null) {
			// 各コントロールを取得
			TextView numberView = (TextView) view.findViewById(R.id.item_number);
			ImageView imageView = (ImageView) view.findViewById(R.id.item_img);
			TextView textView = (TextView) view.findViewById(R.id.item_text);

			// 表示
			if (created && numberView != null) {
				numberView.setText(String.valueOf(viewCount));
			}
			if (imageView != null && item.image != null) {
				imageView.setImageDrawable(item.image);
			}
			if (textView != null && item.text != null) {
				textView.setText(item.text);
			}
		}

		return view;
	}
}
