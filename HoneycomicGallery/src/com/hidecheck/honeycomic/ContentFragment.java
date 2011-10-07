package com.hidecheck.honeycomic;

import android.app.Fragment;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ContentFragment extends Fragment {
	private View contentView;
	
	//The bitmap currently used by ImageView
	private Bitmap bitmap = null;
	
	// Current action mode (contextual action bar, a.k.a. CAB)
	private ActionMode currentActionMode;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	 ImageView imageView ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		contentView = inflater.inflate(R.layout.content_wellcome, null);
		imageView = (ImageView)contentView.findViewById(R.id.image);
		contentView.setDrawingCacheEnabled(false);
		
		//imageview enable drag.
		imageView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				ClipData data = ClipData.newPlainText("msg",
				"Please drop to robot.");

				v.startDrag(data, new DragShadowBuilder(v), null, 0);
				return false;
			}
		});
		
		imageView.setOnDragListener(new OnDragListener() {
			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				switch (event.getAction()) {
				case DragEvent.ACTION_DRAG_ENTERED:
					Log.v("ContentFragment", "onDrag event: DRAG_ENTERD");
					break;

				case DragEvent.ACTION_DRAG_EXITED:
					Log.v("ContentFragment", "onDrag event: DRAG_EXITED");
					break;
					
				case DragEvent.ACTION_DRAG_STARTED:
					//ドラッグが開始
					Log.v("ContentFragment", "onDrag event: DRAG_STARTED");
					break;

				case DragEvent.ACTION_DROP:
					Log.v("ContentFragment", "onDrag event: DRAG_DROP");
					break;
				}
				return false;
			}
		});
		
		
		// TODO change image resource
		imageView.setImageResource(R.drawable.big_droid);
		return contentView;
	}
	
	
}
