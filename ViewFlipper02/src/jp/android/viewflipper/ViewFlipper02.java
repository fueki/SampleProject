package jp.android.viewflipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

public class ViewFlipper02 extends Activity {
    private ViewFlipper viewflipper;  
    private float lastTouchX;
    private static final Animation inFromLeft = AnimationHelper.inFromLeftAnimation();
    private static final Animation outToRight = AnimationHelper.outToRightAnimation();
    private static final Animation inFromRight = AnimationHelper.inFromRightAnimation();
    private static final Animation outToLeft = AnimationHelper.outToLeftAnimation();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        viewflipper = (ViewFlipper)this.findViewById(R.id.layoutswitcher);
        
        this.viewflipper.setOnTouchListener(new OnTouchListener() {
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastTouchX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        float currentX = event.getX();
                        if (lastTouchX < currentX) {
                        	
                            
                        	viewflipper.setInAnimation(inFromLeft);
                            viewflipper.setOutAnimation(outToRight);
                            viewflipper.showNext();
                        }
                        if (lastTouchX > currentX) {
                        	
                        	viewflipper.setInAnimation(inFromRight);
                        	viewflipper.setOutAnimation(outToLeft);
                            viewflipper.showPrevious();
                        }
                        break;
                }
                return true;
            }
        });
    }
}