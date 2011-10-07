package jp.oesf.mtgeduwg.training.rssreader.test;

import jp.oesf.mtgeduwg.training.rssreader.R;
import jp.oesf.mtgeduwg.training.rssreader.RssReaderActivity;
import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;


public class RssReaderActivityTest extends ActivityInstrumentationTestCase2<RssReaderActivity>{

	public RssReaderActivityTest(){
		super("jp.oesf.mtgeduwg.training.rssreader", RssReaderActivity.class);
	}
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testOnClickListButton(){
		ActivityMonitor monitor = new ActivityMonitor("jp.oesf.mtgeduwg.training.rssreader.RssListActivity", null, false);
		getInstrumentation().addMonitor(monitor);
		final Button button = (Button) getActivity().findViewById(R.id.list_button);
		
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				button.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();
		
		Activity rssListActivity = getInstrumentation().waitForMonitor(monitor);
		ListView listView = (ListView) rssListActivity.findViewById(android.R.id.list);
		assertTrue(listView.getCount() > 0);
		
		if(rssListActivity != null){
			rssListActivity.finish();
		}
	}

}
