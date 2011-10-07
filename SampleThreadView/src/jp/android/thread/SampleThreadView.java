package jp.android.thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class SampleThreadView extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClickTreadStrat(View v){
    	new GoodThread().start();
    }
    
    class GoodThread extends Thread{
    	
    	Handler mHandler = new Handler();
    	
    	public void run(){
    		final TextView tv = (TextView)findViewById(R.id.view1);
    		
    		mHandler.post(new Runnable(){
    			@Override
    			public void run(){
    				tv.setText(R.string.hello_thread);
    			}
    		});
    	} 
    }
}