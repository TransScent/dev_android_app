package com.example.learning_app.Intromanager;

import android.content.Context;
import android.content.SharedPreferences;

public class Intromanager {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        Context context;
public Intromanager(Context context)
        {
        this.context=context;
        preferences=context.getSharedPreferences("first", 0);
        editor=preferences.edit();

        }
public void setFirst(Boolean isFirst)
        {
        editor.putBoolean("check",isFirst);
        editor.commit();
        }
public boolean Check()
        {
        return preferences.getBoolean("check",true);
        }
        }
