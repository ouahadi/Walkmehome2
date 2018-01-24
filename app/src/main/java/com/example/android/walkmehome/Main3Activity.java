package com.example.android.walkmehome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Main3Activity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, Main3Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
}
