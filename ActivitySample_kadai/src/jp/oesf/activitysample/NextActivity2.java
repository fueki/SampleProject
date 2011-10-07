package jp.oesf.activitysample;

import jp.oesf.activitysample.util.AlertDialogUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NextActivity2 extends Activity {
	int result;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next2);
        
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        TextView textView = (TextView)findViewById(R.id.text_title);
        textView.setText(message);
    }
    
    public void onClickFinishButton(View v){
    	setResult(result);
    	finish();
    }
    
    public void onClickAlertButton(View v){
    	
    	AlertDialogUtil.showAlertHasCancel(this, "Title", "message", 
    	new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				result = RESULT_OK;
			}
		}, 
		new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				result = RESULT_CANCELED;
			}
		});
//    	showAlert();
    }
    
    private void showAlert(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// 表示するタイトルを設定します。
		builder.setTitle("Title");
		// 表示するメッセージを設定します。
		builder.setMessage("Message");
		// ボタンに表示するテキストとボタンをクリックされた時に呼び出されるリスナーを設定します。
		builder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(NextActivity2.this,
								"Hello, Android!!", Toast.LENGTH_SHORT).show();
						result = RESULT_OK;
					}
				});
		
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						result = RESULT_CANCELED;
					}
				});


		// AlertDialogを表示します。
		builder.show();

    }
    
}