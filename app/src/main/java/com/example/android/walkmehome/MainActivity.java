package com.example.android.walkmehome;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.walkmehome.utils.SaveHelper;

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
        checkPermissions(granted -> {});
        if(!SaveHelper.getPhone(this).equals("")) Main3Activity.startActivity(this);
        startButton.setOnClickListener(v -> Main2Activity.startActivity(MainActivity.this));
    }

}
