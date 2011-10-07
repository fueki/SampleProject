package jp.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewEx extends View {
	private String text;
    private int    color;
    private int    size;
    
	public CustomViewEx(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setBackgroundColor(Color.WHITE);

        //ëÆê´ÇÃéÊìæ
        TypedArray attr=context.obtainStyledAttributes(
            attrs,R.styleable.CustomView);
        text =attr.getString(R.styleable.CustomView_text);
        color=attr.getColor(R.styleable.CustomView_color,0xFF000000);
        size =attr.getDimensionPixelSize(R.styleable.CustomView_size,12);
    }

	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(color);
        paint.setTextSize(size);
        canvas.drawText(text,0,size,paint);
    }
}
