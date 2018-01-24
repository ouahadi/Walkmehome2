package com.example.android.walkmehome;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/*
 * Created by Gleb
 * TulaCo 
 * 1/24/2018
 */

public abstract class BaseActivity extends AppCompatActivity {
    public void showInfoMessage(String message, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .show();
    }
}
