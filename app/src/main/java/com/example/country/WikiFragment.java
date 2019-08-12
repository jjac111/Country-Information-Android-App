package com.example.country;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

public class WikiFragment extends Fragment {
    // String used when logging error messages
    private static final String TAG = "Wiki WebView";

    private WebView webView;
    private String wikiURL = " https://en.wikipedia.org/wiki/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.fragment_wiki, container, false); // guarda vista para retornar al final


        // get references to GUI components
        webView = view.findViewById(R.id.wikiWebView);
        String url = wikiURL + getArguments().getString("country", "Ecuador");
        webView.loadUrl(url);

        return view; // return the fragment's view for display
    }
}
