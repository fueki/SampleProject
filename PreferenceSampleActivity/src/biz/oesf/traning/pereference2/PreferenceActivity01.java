package biz.oesf.traning.pereference2;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class PreferenceActivity01 extends  PreferenceActivity{
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }
}