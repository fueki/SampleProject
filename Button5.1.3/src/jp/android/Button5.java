package jp.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Button5 extends Activity implements OnClickListener{
	Button button;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
    }
    
    public void onClick(View v){
    	button.setText("Button Clicked!!");
    }
    
}