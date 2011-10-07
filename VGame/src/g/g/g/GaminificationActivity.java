package g.g.g;

import g.g.g.gamesystem.action.ActionManage;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GaminificationActivity extends Activity {
	TextView remainAction;
	TextView display;
	private static int throwsCount = 3;
	private final int reset = 3;
	private Button resetButton;
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        resetButton = (Button)findViewById(R.id.button_reset);
        remainAction = (TextView)findViewById(R.id.remainaction);
        display = (TextView)findViewById(R.id.display);
        remainAction.setText("");

        resetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				throwsCount = reset;
				display.setText("");
				Toast.makeText(GaminificationActivity.this, "Resetしました", Toast.LENGTH_SHORT).show();
			}
		});
    }

    public void onClickThrowDice(View v){

    	if(throwsCount <= 0){
    		Toast.makeText(this, "これ以上ダイスは振れません", Toast.LENGTH_SHORT).show();
    	}else{
    		display.setText(throwDice());
    		throwsCount --;
    	}


    }
    public int getRemainTimes(){
    	return 123;
    }
    public String throwDice(){
    	int i = ActionManage.throwDice();
    	String s = String.valueOf(i);
    	return s;
    }
}