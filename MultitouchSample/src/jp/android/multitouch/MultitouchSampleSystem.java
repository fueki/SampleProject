package jp.android.multitouch;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class MultitouchSampleSystem extends Activity{
//    ImageView imgView;
    jp.android.multitouch.ScalableView1 imgView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.sw34);
        
        imgView = (jp.android.multitouch.ScalableView1)findViewById(R.id.ImageView01);
        
        //画像Set
        imgView.setImageBitmap(bmp);
//        imgView.setOnTouchListener(ScalebleView.class);
    }
}
