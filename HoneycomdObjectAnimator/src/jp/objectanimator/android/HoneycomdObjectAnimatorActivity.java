package jp.objectanimator.android;

import java.util.Random;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HoneycomdObjectAnimatorActivity extends Activity {
    
	ObjectAnimator xAnim = null;
    ObjectAnimator yAnim = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
//        ImageView img = (ImageView) findViewById(R.id.imageView1);
//        
//        xAnim = ObjectAnimator.ofFloat(img, "translationX", 0.f, 300);
//        xAnim.setDuration(3000);
//        
//        yAnim = ObjectAnimator.ofFloat(img, "translationY", 0.f, 200);
//        yAnim.setDuration(3000);
//        
//        xAnim.start();
//        yAnim.start();
        
        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.main, null);
        setContentView(v);

        v.post(new Runnable() {

            public void run() {

                ImageView img = (ImageView) findViewById(R.id.imageView1);

                Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.vip470676);

                img.setImageBitmap(bMap);

                Point p = generateNextPoint();

                xAnim = ObjectAnimator.ofFloat(img, "translationX", 0.f, p.getX());
                xAnim.setDuration(1000);
                xAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                xAnim.setRepeatCount(ValueAnimator.INFINITE);

                yAnim = ObjectAnimator.ofFloat(img, "translationY", 0.f, p.getY());
                yAnim.setDuration(1000);
                yAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                yAnim.setRepeatCount(ValueAnimator.INFINITE);

                xAnim.addListener(new Animator.AnimatorListener() {

                    public void onAnimationStart(Animator animation) {
                    }

                    public void onAnimationRepeat(Animator animation) {

                        Point p = generateNextPoint();

                        Float x = (Float) xAnim.getAnimatedValue();
                        xAnim.setFloatValues(x, p.getX());

                        Float y = (Float) yAnim.getAnimatedValue();
                        yAnim.setFloatValues(y, p.getY());
                    }

                    public void onAnimationCancel(Animator animation) {
                    }

                    public void onAnimationEnd(Animator animation) {
                    }
                });

                xAnim.start();

                yAnim.start();
            }
        });
    }

    public Point generateNextPoint() {

        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.vip470676);

        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
        int lWidth = layout.getWidth();
        int lHeight = layout.getHeight();

        int width = lWidth - bMap.getWidth();
        int height = lHeight - bMap.getHeight();

        Random generator = new Random();

        Float xTarget = (generator.nextFloat() * width + 1);
        Float yTarget = (generator.nextFloat() * height + 1);

        return new Point(xTarget, yTarget);
    }
}

class Point {

    private Float x;
    private Float y;

    Point(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("x = ");
        sb.append(x);
        sb.append(" ; y = ");
        sb.append(y);

        return sb.toString();
    }
}