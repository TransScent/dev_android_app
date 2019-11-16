package com.example.learning_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learning_app.Utilities.UtilitiesCheck;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button btn_test;
    UtilitiesCheck utilitiesCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utilitiesCheck=new UtilitiesCheck(getApplicationContext());
        btn_test=findViewById(R.id.button_id);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(utilitiesCheck.isNetworkConnected())
                {
                    Snackbar.make(view, "Internet Connection available", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    Snackbar.make(view, "No internet connection.\n Please check your network connection", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }
        });

    }
}
