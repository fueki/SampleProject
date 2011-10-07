package jp.oesf.listsample;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListSample extends ListActivity {
	public static final String mItems[] = { "柴犬", "北海道犬", "甲斐犬", "紀州犬", "土佐犬",
			"四国犬", "秋田犬", "縄文犬", "琉球犬", "川上犬", "薩摩犬", "美濃柴", "山陰柴", "まめしば" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		ArrayAdapter<String> adpter = new ArrayAdapter<String>(this,
				R.layout.list_row, mItems);
		setListAdapter(adpter);

	}
}