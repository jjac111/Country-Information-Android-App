package com.example.country;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CountriesAdapter extends BaseAdapter {


    private List<Country> countries;
    private Context context;
    private ImageView imageView;
    private TextView nameTextView;
    private TextView continentTextView;

    public CountriesAdapter(Context context, List<Country> countries){
        this.countries = countries;
        this.context = context;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data model based on position
        Country country = (Country) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.row_continent, parent, false);     //usa el mismo layout de fila que los continents
        }

        // Set item views based on your views and data model, programatical access
        imageView = convertView.findViewById(R.id.continentImageView);
        nameTextView = convertView.findViewById(R.id.continentName);
        continentTextView = convertView.findViewById(R.id.numberOfCountries);
        nameTextView.setText(country.name);
        continentTextView.setText("Countries: " + country.continent);
        try (InputStream stream =
                     context.getAssets().open(country.continent + "/" + country.name + ".png")) {
            // load the asset as a Drawable and display on the flagImageView
            Drawable flag = Drawable.createFromStream(stream, country.name);
            imageView.setImageDrawable(flag);
        }
        catch (IOException exception) {

        }
        return convertView;
    }

}
