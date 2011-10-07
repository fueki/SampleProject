package com.example.chackable.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chackable.R;
import com.example.chackable.R.id;
import com.example.chackable.R.layout;
import com.example.chackable.bean.ItemBean;

public class CheckableImageView extends FrameLayout implements OnClickListener{

	private LinearLayout rootView;
	private CheckBox checkBox;
	private ImageView imageView;
	private ItemBean itemBean;
	private OnChangeListener listener;
	
	public interface OnChangeListener{
		void onCheckedChanged(CheckableImageView checkableImageView, boolean isChecked );
	}
	
	public void setListener(OnChangeListener listener) {
		this.listener = listener;
	}

	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}
	
	public ItemBean getItemBean() {
		return itemBean;
	}

	
	
	public CheckableImageView(Context context, ItemBean itemBean){
		super(context);
		init();
		this.itemBean = itemBean;
	}
	public CheckableImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CheckableImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init(){
		LayoutInflater inflater = LayoutInflater.from(this.getContext());
		rootView = (LinearLayout) inflater.inflate(R.layout.chackable_imageview, null);
		addView(rootView);
		checkBox = (CheckBox)rootView.findViewById(R.id.checkBox1);
		checkBox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(listener != null){
				listener.onCheckedChanged(CheckableImageView.this, isChecked);
				}else{
					Toast.makeText(getContext(), "Warnning!! Lister is null", Toast.LENGTH_SHORT);
				}
			}
		});
		imageView = (ImageView)findViewById(R.id.imageView1);
		imageView.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		
	}

}
