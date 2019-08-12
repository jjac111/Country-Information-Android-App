package com.example.country;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.country.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountriesFragment extends Fragment {
    // String used when logging error messages
    private static final String TAG = "Country Information Activity";

    private List<Country> countries; // flag file names
    private String region; // world region in current view

    // configures the ContinentsFragment when its View is created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.fragment_countries, container, false); // guarda vista para retornar al final

        countries = new ArrayList<>();
        region = getArguments().getString("SELECTED_CONTINENT");

        try {
            for(String str : getActivity().getAssets().list(region))
                countries.add( new Country(str.replace(".png", ""), region));
        } catch (IOException e) {
            e.printStackTrace();
        }

        CountriesAdapter adapter = new CountriesAdapter(getContext(), countries);

        return view; // return the fragment's view for display
    }


}
