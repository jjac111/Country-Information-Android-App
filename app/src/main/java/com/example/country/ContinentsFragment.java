package com.example.country;


import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContinentsFragment extends Fragment {
    // String used when logging error messages
    private static final String TAG = "Continent Information";

    private List<String> fileNameList; // flag file names
    private Set<String> regionsSet; // world regions in current quiz
    private ArrayList<Continent> continents = new ArrayList<>();


    // configures the ContinentsFragment when its View is created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.fragment_continents, container, false); // guarda vista para retornar al final

        fileNameList = new ArrayList<>();

        // get references to GUI components
        ListView rvContinents = view.findViewById(R.id.continentsListView);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        ArraySet<String> defaultSet = new ArraySet<>();
        defaultSet.add("North America");
        Set<String> stringSet = sp.getStringSet(MainActivity.REGIONS, defaultSet);
        Set<String> existingStringSet = sp.getStringSet(SettingsActivity.AVAILABLE_REGIONS, defaultSet);
        ArrayList<String> toBeRemoved = new ArrayList<>();
        for(String str : stringSet){
            if(!existingStringSet.contains(str)){
                toBeRemoved.add(str);
            }
        }
        stringSet.removeAll(toBeRemoved);
        for(String str : stringSet){
            switch(str){
                case "Africa":{
                    continents.add(new Continent(getString(R.string.url_africa), "Africa", sp.getInt(str + "_countries",0)));
                    break;
                }
                case "Asia":{
                    continents.add(new Continent(getString(R.string.url_asia), "Asia", sp.getInt(str + "_countries",0)));
                    break;
                }
                case "Europe":{
                    continents.add(new Continent(getString(R.string.url_europe), "Europe", sp.getInt(str + "_countries",0)));
                    break;
                }
                case "North_America":{
                    continents.add(new Continent(getString(R.string.url_namerica), "North America", sp.getInt(str + "_countries",0)));
                    break;
                }
                case "South_America":{
                    continents.add(new Continent(getString(R.string.url_samerica), "South America", sp.getInt(str + "_countries",0)));
                    break;
                }
                case "Oceania":{
                    continents.add(new Continent(getString(R.string.url_oceania), "Oceania", sp.getInt(str + "_countries",0)));
                    break;
                }
                default:
                    break;
            }
        }

        ContinentsAdapter adapter = new ContinentsAdapter(getContext(), continents);
        rvContinents.setAdapter(adapter);

        return view; // return the fragment's view for display
    }


    // update world regions for quiz based on values in SharedPreferences
    public void updateRegions(SharedPreferences sharedPreferences) {
        regionsSet =
                sharedPreferences.getStringSet(MainActivity.REGIONS, null);
    }


    // set up and start the next quiz
    public void resetInformation() {
        // use AssetManager to get image file names for enabled regions
        AssetManager assets = getActivity().getAssets();
        fileNameList.clear(); // empty list of image file names

        try {
            // loop through each region
            for (String region : regionsSet) {
                // get a list of all flag image files in this region
                String[] paths = assets.list(region);

                for (String path : paths)
                    fileNameList.add(path.replace(".png", ""));
            }
        }
        catch (IOException exception) {
            Log.e(TAG, "Error loading image file names", exception);
        }
    }

    // parses the country flag file name and returns the country name
    private String getCountryName(String name) {
        return name.substring(name.indexOf('-') + 1).replace('_', ' ');
    }



}
