package com.example.learning_app;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.learning_app.Adapter.CustomAdapter;
import com.example.learning_app.Models.CountryModel;
import com.example.learning_app.Utilities.ApiCall;
import com.example.learning_app.Utilities.UtilitiesCheck;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    Context mcontext = this;
    Button btn_test;
    UtilitiesCheck utilitiesCheck;
    RequestQueue queue;
    ArrayList<CountryModel> arrayofUsers;
    ListView listView;
    CustomAdapter adapter;
    ApiCall apiCall;
    SpotsDialog dialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiCall=new ApiCall();
        dialog1 = new SpotsDialog(mcontext);
        //Typeface tf = Typeface.createFromAsset(mcontext.getAssets(),"fonts/sofiaProRegular.ttf");

        arrayofUsers = new ArrayList<CountryModel>();
        utilitiesCheck=new UtilitiesCheck(mcontext);
        btn_test=findViewById(R.id.button_id);
        queue = Volley.newRequestQueue(mcontext);
        adapter = new CustomAdapter(MainActivity.this, arrayofUsers);

        //Attach the adapter to Listview
        listView = (ListView)findViewById(R.id.list_items);

            btn_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (utilitiesCheck.isNetworkConnected()) {
                        dialog1.show();
                        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, apiCall.getCountryList(), null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    dialog1.dismiss();
                                    String status = response.getString("status");
                                    String is_process = response.getString("is_process");
                                     if (status.equals("200") && is_process.equals("Y")) {
                                        JSONArray results = response.getJSONArray("results");
                                        int id;String name;int phonecode;
                                        for (int i = 0; i < results.length(); i++) {
                                            JSONObject row = results.getJSONObject(i);
                                            id = row.getInt("id");
                                            name = row.getString("name");
                                            phonecode=row.getInt("phonecode");
                                            arrayofUsers.add(new CountryModel(name,id));
                                        }
                                        listView.setAdapter(adapter);

                                    } else {
                                         dialog1.dismiss();
                                        Toast.makeText(mcontext, "Unable to get response", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    dialog1.dismiss();
                                    Toast.makeText(mcontext, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                dialog1.dismiss();
                                Toast.makeText(mcontext, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(jsObjRequest);
                    }else {
                        dialog1.dismiss();
                        Snackbar.make(view,"No internet connection.Please check your network connection",Snackbar.LENGTH_LONG).show();

                    }
                }
            });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                CountryModel selItem = (CountryModel ) listView.getItemAtPosition(position);
                int id= selItem.getId();
                Intent myIntent = new Intent(mcontext, RecyclerViewActivity.class);
                myIntent.putExtra("id",id);
                startActivity(myIntent);

            }
        });
        }
}
