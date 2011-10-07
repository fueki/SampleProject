package jp.samplecamera.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class SampleCamera01Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        /**
         * フルスクリーンにして、SurfaceViewをセット
         */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout l = new LinearLayout(this);
        l.addView(new SampleView(this));
        setContentView(l);
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
    	boolean ret = super.onCreateOptionsMenu(menu);
    	menu.add(0 , Menu.FIRST , Menu.NONE , "画像一覧");
    	return ret;
    	}
}