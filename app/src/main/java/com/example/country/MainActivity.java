package com.example.country;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.ArraySet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String REGIONS = "pref_regionsToInclude";
    private boolean phoneDevice = true; // used to force portrait mode
    private boolean preferencesChanged = true; // did preferences change?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getLayoutInflater().inflate(R.layout.activity_main,null);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set default values in the app's SharedPreferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // register listener for SharedPreferences changes
        PreferenceManager.getDefaultSharedPreferences(this).
                registerOnSharedPreferenceChangeListener(
                        preferencesChangeListener);


        // determine screen size
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        // if device is a tablet, set phoneDevice to false
        if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
                screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)
            phoneDevice = false; // not a phone-sized device

        // if running on phone-sized device, allow only portrait orientation
        if (phoneDevice)
            setRequestedOrientation(
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(v);
        checkAssets();
    }

    // called after onCreate completes execution
    @Override
    protected void onStart() {
        super.onStart();

        if (preferencesChanged) {
            // now that the default preferences have been set,
            // initialize ContinentsFragment and start the quiz
            ContinentsFragment countryInfoFragment = (ContinentsFragment)
                    getSupportFragmentManager().findFragmentById(
                            R.id.continentsFragment);
            countryInfoFragment.updateRegions(
                    PreferenceManager.getDefaultSharedPreferences(this));
            countryInfoFragment.resetInformation();
            preferencesChanged = false;
        }
    }

    // show menu if app is running on a phone or a portrait-oriented tablet
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            // inflate the menu
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;

    }

    // displays the SettingsActivity when running on a phone
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent preferencesIntent = new Intent(this, SettingsActivity.class);
        startActivity(preferencesIntent);
        return super.onOptionsItemSelected(item);
    }

    private void checkAssets() {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        Set<String> assetsList = new ArraySet<>();
        try {
            for(String str : getAssets().list("")) {
                if (str.equals("Africa") || str.equals("Asia") || str.equals("Europe") || str.equals("North_America") || str.equals("South_America") || str.equals("Oceania")) {
                    int countriesInContinent = 0;
                    for(String cntry : getAssets().list(str ))
                        countriesInContinent++;
                    assetsList.add(str);
                    editor.putInt(str + "_countries", countriesInContinent);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.putStringSet(SettingsActivity.AVAILABLE_REGIONS, assetsList).commit();
    }

    // listener for changes to the app's SharedPreferences
    private SharedPreferences.OnSharedPreferenceChangeListener preferencesChangeListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                // called when the user changes the app's preferences
                @Override
                public void onSharedPreferenceChanged(
                        SharedPreferences sharedPreferences, String key) {
                    preferencesChanged = true; // user changed app setting
                    ContinentsFragment countryInfoFragment = (ContinentsFragment)
                            getSupportFragmentManager().findFragmentById(
                                    R.id.continentsFragment);
                        if (key.equals(REGIONS)) { // regions to include changed
                            Set<String> regions =
                                sharedPreferences.getStringSet(REGIONS, null);

                        if (regions != null && regions.size() > 0) {
                            countryInfoFragment.updateRegions(sharedPreferences);
                            countryInfoFragment.resetInformation();
                        }
                        else {
                            // must select one region--set North America as default
                            SharedPreferences.Editor editor =
                                    sharedPreferences.edit();
                            regions.add(getString(R.string.default_region));
                            editor.putStringSet(REGIONS, regions);
                            editor.apply();

                            Toast.makeText(MainActivity.this,
                                    R.string.default_region_message,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    Toast.makeText(MainActivity.this,
                            R.string.restarting_quiz,
                            Toast.LENGTH_SHORT).show();
                }
            };
}
