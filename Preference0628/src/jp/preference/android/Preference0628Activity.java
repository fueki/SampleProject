package jp.preference.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Preference0628Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClickSaveButton(View view){
    	EditText edit = (EditText)findViewById(R.id.edit_string_value);
    	CheckBox cb   = (CheckBox)findViewById(R.id.checkBox_boolean_value);
    	
    	SharedPreferences preferences = getSharedPreferences("PreferenceTest",Context.MODE_PRIVATE);
    	
    	SharedPreferences.Editor editor = preferences.edit();
    	editor.putString("KeyString", edit.getText().toString());
    	editor.putBoolean("KeyBoolean", cb.isChecked());
    	
    	editor.commit();
    }
    
    public void onClickGetDataButton(View view){
    	SharedPreferences preferences = getSharedPreferences("PreferenceTest",Context.MODE_PRIVATE);
    	String valueString = preferences.getString("KyeString", null);
    }
}