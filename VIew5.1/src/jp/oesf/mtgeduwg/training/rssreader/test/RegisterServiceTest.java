package jp.oesf.mtgeduwg.training.rssreader.test;

import jp.oesf.mtgeduwg.training.rssreader.RegisterService;
import jp.oesf.mtgeduwg.training.rssreader.helper.DatabaseOpenHelper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.ServiceTestCase;

public class RegisterServiceTest extends ServiceTestCase<RegisterService> {

	public RegisterServiceTest() {
		super(RegisterService.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRegisterService() {
		int before;
		int after;

		Intent intent = new Intent(RegisterService.class.getName());
		before = countDb();
		startService(intent);
		after = countDb();

		assertTrue(after - before > 0);
	}

	private int countDb() {
		int result = 0;
		Cursor cursor = null;
		DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(getContext());
		SQLiteDatabase database = null;
		
		try {
			database = databaseOpenHelper.getReadableDatabase();
			cursor = database.query("RSS_FEED", null, null, null, null, null,
					null);
			result = cursor.getCount();
		} finally {
			if (database != null) {
				database.close();
			}
			
			if(cursor != null){
				cursor.close();
			}
		}
		return result;
	}

}
