package com.example.android.walkmehome;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView welcome = (TextView) findViewById(R.id.welcome);
        welcome.setText("Привіт!\n\n" +
                "Дякую за завантаження!\n\n" +
                "Мене звати Протон з позитивним зарядом і я прототип для проекту " +
                "\"Пильнуй своїх\". Я допоможу тобі налаштувати та протестувати віртуальне " +
                "супроводження. \n\nКоли ми завершимо, я надсилатиму повідомлення про твої " +
                "пересування та затримки у дорозі тому, на кого ти можеш покластися.");
    }

    public void buttonStart (View view) {
        Intent i=new intent(MainActivity.this,Main2Activity.class);
        startActivity(i);

    }

}
