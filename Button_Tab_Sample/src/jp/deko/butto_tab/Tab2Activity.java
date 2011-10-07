package jp.deko.butto_tab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Tab2Activity extends Activity {  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
  
        TextView textview = new TextView(this);  
        textview.setText("This is Tab2");  
        setContentView(textview);  
    }  
}  