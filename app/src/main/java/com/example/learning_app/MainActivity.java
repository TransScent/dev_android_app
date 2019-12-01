package com.example.learning_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.learning_app.Utilities.UtilitiesCheck;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    Button btn_test;
    String url="http://192.168.42.113:8020/api/learning/getCountriesList";
    UtilitiesCheck utilitiesCheck;
    RequestQueue queue;
    ArrayAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utilitiesCheck=new UtilitiesCheck(getApplicationContext());
        btn_test=(Button) findViewById(R.id.button_id);
        queue = Volley.newRequestQueue(getApplicationContext());
        adapter = new ArrayAdapter<String>(this,R.layout.activity_listview);
        listView= (ListView) findViewById(R.id.list_items);
        listView.setAdapter(adapter);
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
                                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                        System.out.println("Results"+results);

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Unable to get response", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }


                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(jsObjRequest);
                    }else {
                        Toast.makeText(getApplicationContext(), "No internet connection.Please check your network connection", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }




}
