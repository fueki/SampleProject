package jp.viewflipper.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import android.os.Bundle;

public class ViewFlipperActivity extends Activity {
    private ViewFlipper viewFlipper;
    private float firstTouch;
    private boolean isFlip = false;
    private ImageView firstImageView;
    private ImageView secondImageView;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        
        Button prevButton = (Button) findViewById(R.id.prevButton);
        Button nextButton = (Button) findViewById(R.id.nextButton);
 
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
 
        viewFlipper.setAutoStart(true);     //自動でスライドショーを開始
        viewFlipper.setFlipInterval(1000);  //更新間隔(ms単位)
    }
 
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int)event.getRawX();
        switch(v.getId()) {
        case R.id.layout_first:
        case R.id.layout_second:
            switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstTouch = event.getRawX();
                return true;
            case MotionEvent.ACTION_MOVE:
                if(!isFlip) {
                    if(x - firstTouch > 50) {
                        isFlip = true;
//                        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_in_left));
//                        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.move_out_right));
                        viewFlipper.showNext();
                    }
                    else if(firstTouch - x > 50) {
                        isFlip = true;
//                        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_in_right));
//                        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.move_out_left));
                        viewFlipper.showPrevious();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isFlip = false;
                break;
            }
        }
         
        return false;
    }
}