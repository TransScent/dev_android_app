package com.example.learning_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.learning_app.Adapter.State_Adapter;
import com.example.learning_app.Models.CountryModel;
import com.example.learning_app.Models.StateModel;
import com.example.learning_app.Utilities.ApiCall;
import com.example.learning_app.Utilities.UtilitiesCheck;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class RecyclerViewActivity extends AppCompatActivity {
    private List<StateModel> stateList = new ArrayList<>();
    private RecyclerView recyclerView;
    private State_Adapter mAdapter;
    private RequestQueue queue;
    int id;
    ApiCall apiCall;
    private Context context=this; UtilitiesCheck utilitiesCheck;


    SpotsDialog dialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        dialog1 = new SpotsDialog(context);
        apiCall=new ApiCall();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        queue = Volley.newRequestQueue(context);
        mAdapter = new State_Adapter(stateList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        utilitiesCheck=new UtilitiesCheck(context);




    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras= getIntent().getExtras();
        id=extras.getInt("id");
            prePareStateData(id);


    }

    private void prePareStateData(final int id) {
        if (utilitiesCheck.isNetworkConnected()) {
            dialog1.show();
            StringRequest strreq = new StringRequest(Request.Method.POST, apiCall.getStateList(),
                    new Response.Listener<String>() {
                        @Override

                        public void onResponse(String response) {
                            try {
                                dialog1.dismiss();
                                JSONObject obj = new JSONObject(response);
                                String status = obj.getString("status");
                                String is_process = obj.getString("is_process");
                                if (status.equals("200") && is_process.equals("Y")) {
                                    JSONArray results = obj.getJSONArray("results");
                                    int id;
                                    String name;
                                    int phonecode;
                                    for (int i = 0; i < results.length(); i++) {
                                        JSONObject row = results.getJSONObject(i);

                                        name = row.getString("name");

                                        stateList.add(new StateModel(name));
                                    }
                                    recyclerView.setAdapter(mAdapter);
                                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                                } else {
                                    dialog1.dismiss();
                                    Toast.makeText(context, "Unable to get response", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                dialog1.dismiss();
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError e) {
                    dialog1.dismiss();
                    e.printStackTrace();
                }
            }) {
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("country_id", String.valueOf(id));
                    return params;
                }
            };
            queue.add(strreq);
        }else{
           // Need to implement over here.
            dialog1.dismiss();
        }
    }
    }
