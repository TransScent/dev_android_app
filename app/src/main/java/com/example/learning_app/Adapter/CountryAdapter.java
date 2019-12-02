package com.example.learning_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learning_app.Models.CountryModel;
import com.example.learning_app.R;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<CountryModel> {
    public CountryAdapter(Context context, ArrayList<CountryModel> country) {
        super(context, 0, country);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CountryModel countryModel=getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview, parent, false);

        }
        TextView countryName=(TextView)convertView.findViewById(R.id.countryName);
        countryName.setText(countryModel.getName());

        return super.getView(position, convertView, parent);

    }
}
