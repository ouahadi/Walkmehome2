package com.example.android.walkmehome;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    private TextView welcomeTextView;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fields init
        welcomeTextView = findViewById(R.id.welcomeTextView);
        startButton = findViewById(R.id.startButton);
        //

        welcomeTextView.setText(getString(R.string.weolcome_text));
        RxPermissions rxPermissions = new RxPermissions(this);
        if (!rxPermissions.isGranted(Manifest.permission.SEND_SMS))
            rxPermissions.request(Manifest.permission.SEND_SMS)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Exception {
                            if (!granted)
                                showInfoMessage(getString(R.string.permission_error), welcomeTextView);
                        }
                    });
        if (!rxPermissions.isGranted(Manifest.permission.ACCESS_COARSE_LOCATION))
            rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Exception {
                            if (!granted)
                                showInfoMessage(getString(R.string.permission_error), welcomeTextView);
                        }
                    });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main2Activity.startActivity(MainActivity.this);
            }
        });
    }

}
