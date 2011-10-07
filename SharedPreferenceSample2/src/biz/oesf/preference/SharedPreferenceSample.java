package biz.oesf.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferenceSample extends Activity {
	TextView mTextView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTextView.setText("");
        
    }
    
    public void onClickSaveButton(View v){
    	//プログラム上で使える様にfindViewById
    	EditText edit = (EditText)findViewById(R.id.saveeditText);
    	CheckBox cd   = (CheckBox)findViewById(R.id.savecheckBox);
    	
    	SharedPreferences preferences = getSharedPreferences
										("PreferenceTest",Context.MODE_PRIVATE);
    	//データ書き込み
    	SharedPreferences.Editor editor = preferences.edit();
    	editor.putString("KeyString", edit.getText().toString());
    	editor.putBoolean("KeyBoolean", cd.isChecked());
    	editor.commit();
  
    }
    public void onClickGetDataButton(View v){
    	SharedPreferences preferences = getSharedPreferences
    									("PreferenceTest",Context.MODE_PRIVATE);
    	
    	String valueString = preferences.getString("KeyString", null);
    	boolean valueBoolean = preferences.getBoolean("KeyBoolean", false);
    	
    	StringBuilder result = new StringBuilder();
    	result.append("KeyString:" + valueString + "\n");
    	result.append("KeyBoolean:" + valueBoolean);
    	
    	mTextView = (TextView)findViewById(R.id.datetextView);
    	mTextView.setText(result.toString());
    }
}