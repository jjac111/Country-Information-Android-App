package com.example.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContinentsAdapter extends BaseAdapter {


    private List<Continent> continents;
    private Context context;
    private ImageView imageView;
    private TextView nameTextView;
    private TextView numberTextView;

    public ContinentsAdapter(Context context, List<Continent> continents){
        this.continents = continents;
        this.context = context;
    }

    @Override
    public int getCount() {
        return continents.size();
    }

    @Override
    public Object getItem(int position) {
        return continents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data model based on position
        Continent continent = (Continent) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.row_continent, parent, false);
        }

        // Set item views based on your views and data model, programatical access
        imageView = convertView.findViewById(R.id.continentImageView);
        nameTextView = convertView.findViewById(R.id.continentName);
        numberTextView = convertView.findViewById(R.id.numberOfCountries);
        nameTextView.setText(continent.name);
        numberTextView.setText("Countries: " + continent.numberOfCountries);
        new DownloadImageTask(imageView)
                .execute(continent.imageURL);

        return convertView;
    }

}
