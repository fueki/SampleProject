package jp.dekoflipper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class CustomDekoSearchFlipperActivity extends View{
	private String text;
    private int    color;
    private int    size;
    
	public CustomDekoSearchFlipperActivity(Context context,AttributeSet attrs) {
		super(context,attrs);
		
		
		//‘®«‚Ìæ“¾
        TypedArray attr=context.obtainStyledAttributes(
            attrs,R.styleable.CustomView);
        text =attr.getString(R.styleable.CustomView_text);
        color=attr.getColor(R.styleable.CustomView_color,0xFF000000);
        size =attr.getDimensionPixelSize(R.styleable.CustomView_size,12);
    
	}

}
