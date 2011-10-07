package jp.android.pinch;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
//import android.view.ViewGroup;

public class PinchZoomActivity extends Activity {
    
	jp.android.pinch.ScalableView imgView;
//	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sw34);
        
        imgView = (jp.android.pinch.ScalableView)findViewById(R.id.ImageVire);
        imgView.setImageBitmap(bmp);
    }
    
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
/**
 * 調査中
 * 画像をスライドさせて画面から切れたとき、元に戻す処理を実装
 * 以下のコードを実行するとズームなどの処理が出来なくなる
 */
//    	imgView = new jp.android.pinch.ScalableView(this, null);
//    	imgView.setImageResource(R.drawable.sw34);
//    	imgView.setScaleType(jp.android.pinch.ScalableView.ScaleType.FIT_CENTER);
//        setContentView(imgView, new LayoutParams(WC, WC));
        
    	return true;
    }
}