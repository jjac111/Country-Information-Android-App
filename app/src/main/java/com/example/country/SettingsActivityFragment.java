// SettingsActivityFragment.java
// Subclass of PreferenceFragment for managing app settings
package com.example.country;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.ArraySet;

import java.util.Set;

public class SettingsActivityFragment extends PreferenceFragment {
    // creates preferences GUI from preferences.xml file in res/xml
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preferences); // load from XML
        MultiSelectListPreference mslp = (MultiSelectListPreference) getPreferenceScreen().getPreference(0);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        ArraySet<String> defaultSet = new ArraySet<>();
        defaultSet.add("North America");
        ArraySet<String> actualEntries = new ArraySet<>();
        ArraySet<String> actualValues = new ArraySet<>();
        Set<String> stringSet = sp.getStringSet(SettingsActivity.AVAILABLE_REGIONS, defaultSet);
        for(String str : stringSet){
            if(str.equals("Africa") || str.equals("Asia") || str.equals("Europe") || str.equals("North_America") || str.equals("South_America") || str.equals("Oceania")) {
                actualEntries.add(str.replace("_", " "));
                actualValues.add(str);
            }
        }
        String[] entries = actualEntries.toArray(new String[0]);
        String[] values = actualValues.toArray(new String[0]);
        mslp.setEntries(entries);
        mslp.setEntryValues(values);
    }
}


/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
