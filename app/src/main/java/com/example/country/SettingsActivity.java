// SettingsActivity.java
// Activity to display SettingsActivityFragment on a phone
package com.example.country;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.ArraySet;
import android.view.View;

import java.io.IOException;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {
   public static final String AVAILABLE_REGIONS = "pref_availableRegions"; //Key de SharedPreferences para guardar y extraer qué regiones se encuentran en la carpeta de assets

   // inflates the GUI, displays Toolbar and adds "up" button
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      View v = getLayoutInflater().inflate(R.layout.activity_settings,null);
      Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      setContentView(v);
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
