package com.example.android.walkmehome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.android.walkmehome.utils.SaveHelper;
import com.example.android.walkmehome.utils.SmsHelper;

public class Main3Activity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, Main3Activity.class));
    }

    private Button helpButton;
    private Button goButton;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        phone = SaveHelper.getPhone(this);
        goButton = findViewById(R.id.goButton);
        helpButton = findViewById(R.id.helpButton);

        goButton.setOnClickListener(v -> {
            checkPermissions(granted -> {
                if(granted) {
                    SmsHelper.scheduleSms(Main3Activity.this, phone);
                }
            });

        });

        helpButton.setOnClickListener(v -> {
            checkPermissions(granted -> {
                if(granted)
                    SmsHelper.sendEmergencySms(Main3Activity.this, phone);
            });
        });
    }
}
