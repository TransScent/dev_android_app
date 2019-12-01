package com.example.learning_app.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.learning_app.Models.CountryModel;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<CountryModel> {
    public CountryAdapter(Context context, ArrayList<CountryModel> country) {
        super(context, 0, country);
    }
}
