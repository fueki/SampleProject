package net.dabits.android.nicoplayer.activities;

import net.dabits.android.nicoplayer.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class AccountActivity  extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        addPreferencesFromResource(R.xml.account_preferences);
    }
}
