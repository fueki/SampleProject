package jp.android.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class SampleAsyncTask extends Activity {
	
	private TextView text;
	private static final String TAG = "SampleAsyncTask";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        
        setContentView(R.layout.main);
        
        this.text = (TextView)findViewById(R.id.async);
        this.text.setText("" + 0);
        
        new MyAsyncTask().execute("arge as doInBackground");
    }
    
    class MyAsyncTask extends AsyncTask<String,Integer,Boolean>{
    	@Override
    	protected void onPreExecute(){
    		setProgressBarIndeterminateVisibility(true);
    	}
    	@Override
    	protected Boolean doInBackground(String...params){
    		Log.v(TAG,"params : " + params[0]);
    		
    		for(int i = 0; i<=10; i++){
    			try{
    				publishProgress(i);
    				Thread.sleep(1000);
    			} catch (InterruptedException e){
    				e.printStackTrace();
    				return false;
    			}
    		}
			return true;
    	}
    	@Override
    	protected void onPostExecute(Boolean result){
    		if(result){
    			text.setText("Š®—¹");
    		} else {
    			text.setText("Ž¸”s");
    		}
    		setProgressBarIndeterminateVisibility(false);
    	}
    	@Override
    	protected void onProgressUpdate(Integer... values){
    		text.setText("" + values[0]);
    	}
    }
}