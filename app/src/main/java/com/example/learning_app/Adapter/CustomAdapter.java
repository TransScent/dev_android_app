package com.example.learning_app.Adapter;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.learning_app.MainActivity;
import com.example.learning_app.Models.CountryModel;
import com.example.learning_app.R;
public class CustomAdapter extends BaseAdapter {
    View view;
    ArrayList<CountryModel> arraylist;
    public CustomAdapter(MainActivity customAdapterExample,
                         ArrayList<CountryModel> arrayofUsers) {
        // TODO Auto-generated constructor stub
        arraylist=arrayofUsers;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arraylist.size();
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arraylist.get(position);
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public View getView(int positon, View view, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.activity_listview, parent,false);
        final CountryModel users = arraylist.get(positon);
        TextView name = (TextView)view.findViewById(R.id.countryName);
        name.setText(users.getName());

        return view;
    }


}