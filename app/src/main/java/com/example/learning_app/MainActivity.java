package com.example.learning_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.learning_app.Utilities.UtilitiesCheck;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btn_test;
    String url="http://localhost:8020/api/learning/getCountriesList";
    UtilitiesCheck utilitiesCheck;
     RequestQueue queue;
     ListView listView ;
      String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
          "WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utilitiesCheck=new UtilitiesCheck(getApplicationContext());
         ArrayAdapter adapter = new ArrayAdapter<String>(this, 
                 R.layout.activity_listview, mobileArray);
          listView = (ListView) findViewById(R.id.list_item);
               listView.setAdapter(adapter);
              queue = Volley.newRequestQueue(this);
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                          Object o = adapterView.getItemAtPosition(position);
                                String str=(String)o;//As you are using Default String Adapter
                       if(str=="Android") {

                           JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                               @Override
                               public void onResponse(JSONObject response) {
                                   System.out.println(response);


                               }
                           }, new Response.ErrorListener() {

                               @Override
                               public void onErrorResponse(VolleyError error) {
                                   // TODO Auto-generated method stub

                               }
                           });

                           queue.add(jsObjRequest);
                       } else{
                          Toast.makeText(getApplicationContext(),"I am from somewhere else !!!"+str,Toast.LENGTH_LONG).show();
                       }

                   }
               });


        /*
        btn_test=findViewById(R.id.button_id);
     edit_number=findViewById(R.id.edit_number);
    btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(utilitiesCheck.isNetworkConnected())
                {
                   this.doSomeThinking();
                    Snackbar.make(view, "Internet Connection available", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    Snackbar.make(view, "No internet connection.\n Please check your network connection", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }

            private void doSomeThinking() {
           int num=Integer.parseInt(edit_number.getText().toString());
                num=23;
           if(num>10){
               Toast.makeText(getApplicationContext(),num,Toast.LENGTH_LONG).show();
           }else {
               Toast.makeText(getApplicationContext(),"I'm here for other reason...",Toast.LENGTH_LONG).show();

           }



            }

        });
                */
    }
}
