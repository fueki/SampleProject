package com.example.chackable.view;


import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.chackable.ItemMap;
import com.example.chackable.R;
import com.example.chackable.bean.ItemBean;
import com.example.chackable.view.CheckableImageView.OnChangeListener;

public class ItemsView extends FrameLayout {

	private LinearLayout rootView;
	private ItemMap itemMap;
	private ArrayList<CheckableImageView> checkableImageViews;
	private HashMap<Integer, CheckableImageView> chackableMap;

	public ItemsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	

	
	public ItemsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}



	public ItemsView(Context context) {
		super(context);
		init();
	}



	private void init(){
		checkableImageViews = new ArrayList<CheckableImageView>();
		itemMap = new ItemMap();
		LayoutInflater inflater = LayoutInflater.from(this.getContext());
		rootView = (LinearLayout) inflater.inflate(R.layout.items_view, null);
		addView(rootView);
		
	}
	
	public void putItem(int position, ItemBean bean){
		itemMap.put(position, bean);
		CheckableImageView view = new CheckableImageView(getContext(),bean);
		checkableImageViews.add(view);
//		chackableMap.put(position, view);
		rootView.addView(view);
	}

	public void getItem(int position){
		checkableImageViews.get(position);
	}
	
	public void setListener(OnChangeListener changeListener){
		for(CheckableImageView view : checkableImageViews){
			view.setListener(changeListener);
		}
	}

}
