package jp.inout.android;



import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewInOutActivity extends Activity {
	
    private static final int MENU_ID_MENU1 = (Menu.FIRST + 1);
    private static final int MENU_ID_MENU2 = (Menu.FIRST + 2);
    private static final int MENU_ID_MENU3 = (Menu.FIRST + 3);
	WebView webView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        createWebView("http://www.google.co.jp/");
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            menu.add(Menu.NONE, MENU_ID_MENU1, Menu.NONE, R.string.add);
            menu.add(Menu.NONE, MENU_ID_MENU2, Menu.NONE, R.string.add2);
            menu.add(Menu.NONE, MENU_ID_MENU3, Menu.NONE, R.string.add3);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    	switch (item.getItemId()) {
        default:
        	createWebView("http://www.google.co.jp/");
            break;
        case MENU_ID_MENU1:
        	createWebView("http://jp.msn.com/");
            break;
        case MENU_ID_MENU2:
        	createWebView("http://www.apple.com/jp/mac/");
            break;
        case MENU_ID_MENU3:
        	createWebView("http://www.srware.net/en/software_srware_iron.php");
        	break;
        }
            return true;
    }
    
    public void createWebView(String url){
        // Webビューの作成
        webView = (WebView) findViewById(R.id.webview);
        webView.setVerticalScrollbarOverlay(true);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        
        settings.setSupportMultipleWindows(true);//マルチウインドウをサポートするか否か
        settings.setLoadsImagesAutomatically(true);
        settings.setBuiltInZoomControls(true);//ズームボタンのON/OFF
        settings.setJavaScriptEnabled(true);//JavaScriptのON/OFF
        settings.setSupportZoom(true);//ズームをサポートするか否か
//        settings.setLightTouchEnabled(true);
        
        try{
            //マルチタッチを有効にしたまま、zoom controlを消す
            Field nameField = settings.getClass().getDeclaredField("mBuiltInZoomControls");
            nameField.setAccessible(true);
            nameField.set(settings, false);
        }catch(Exception e){
            e.printStackTrace();
            settings.setBuiltInZoomControls(false);
        }
        
        webView.loadUrl(url);
    }
}