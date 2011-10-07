package com.example.chackable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.chackable.bean.ItemBean;
import com.example.chackable.constants.Constants;
import com.example.chackable.view.CheckableImageView;
import com.example.chackable.view.ItemsView;
import com.example.chackable.view.CheckableImageView.OnChangeListener;

public class CustomViewChackableImageActivity extends Activity {
	
	ItemsView itemsView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	private void init() {
		itemsView = (ItemsView)findViewById(R.id.items_view);
		setItems();
	}

	private void setItems() {
		for (int i = 0; i < 4; i++) {
			ItemBean bean = new ItemBean("" + i, "deco_" + i + ".jpg", "img/deco_" + i + ".jpg");
			itemsView.putItem(i, bean);
		}
		itemsView.setListener(new OnChangeListener() {

			@Override
			public void onCheckedChanged(CheckableImageView checkableImageView, boolean isChecked) {
				ItemBean bean = checkableImageView.getItemBean();
				Toast.makeText(CustomViewChackableImageActivity.this, "chaedk:" + isChecked + Constants.BR + bean.toString(), Toast.LENGTH_SHORT).show();
				
			}
			
		});
	}
}