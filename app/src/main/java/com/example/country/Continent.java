package com.example.country;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.ArraySet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Continent {
    public String imageURL;
    public String name;
    public int numberOfCountries;

    public Continent(String imageURL, String name, int numberOfCountries){
        this.name = name;
        this.numberOfCountries = numberOfCountries;
        this.imageURL = imageURL;
    }
}
