package jp.sample;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

public class SampleDialogActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClickButton(View view){
    	new AsyncTaskBack(this).execute("xxx");
    }
    
    class AsyncTaskBack extends AbsLoadingTask{
    
        public AsyncTaskBack(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		protected Boolean doInBackground(String...strings){
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return false;
        }
    }
}