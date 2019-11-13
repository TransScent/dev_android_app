package com.example.learning_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button btn_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_test=findViewById(R.id.button_id);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Welcome Back Jayprakash In Android world !!!", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "Try Again !!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
