package com.example.learning_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.example.learning_app.Utilities.UtilitiesCheck;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private Context context = this;
    Button btn_test;
    String url="http://192.168.42.189:8020/api/learning/getCountriesList";
    UtilitiesCheck utilitiesCheck;
    RequestQueue queue;
    ArrayList<CountryModel> arrayofUsers;
    ListView listView;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayofUsers = new ArrayList<CountryModel>();
        utilitiesCheck=new UtilitiesCheck(context);
        btn_test=findViewById(R.id.button_id);
        queue = Volley.newRequestQueue(context);
        adapter = new CustomAdapter(MainActivity.this, arrayofUsers);
        //Attach the adapter to Listview
        listView = (ListView)findViewById(R.id.list_items);
            btn_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (utilitiesCheck.isNetworkConnected()) {
                        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String status = response.getString("status");
                                    System.out.println(status+"status");
                                    String is_process = response.getString("is_process");
                                    System.out.println(is_process);
                                    if (status.equals("200") && is_process.equals("Y")) {
                                        JSONArray results = response.getJSONArray("results");
                                        String id;
                                        String name;
                                        int phonecode;
                                        for (int i = 0; i < results.length(); i++) {
                                            JSONObject row = results.getJSONObject(i);
                                            id = row.getString("id");
                                            name = row.getString("name");
                                            phonecode=row.getInt("phonecode");
                                            arrayofUsers.add(new CountryModel(name,id,phonecode));
                                        }
                                        listView.setAdapter(adapter);
                                        Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
                                        System.out.println("Results"+results);

                                    } else {
                                        Toast.makeText(context, "Unable to get response", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                }


                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(jsObjRequest);
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.customview,null);
                        builder.setView(dialogView);

                        final AlertDialog dialog = builder.create();
                        Button  closeBtn = (Button)dialogView.findViewById(R.id.buttonOk);
                        dialog.show();
                        closeBtn .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();

                    }


                }
            });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Toast.makeText(context,"Here you are",Toast.LENGTH_LONG).show();
            }
        });

        }




}
