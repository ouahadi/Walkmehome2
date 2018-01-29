package rocks.lechick.android.walkmehome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import rocks.lechick.android.walkmehome.utils.LocationHelper;
import rocks.lechick.android.walkmehome.utils.SaveHelper;
import rocks.lechick.android.walkmehome.utils.SmsHelper;


public class Main3Activity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, Main3Activity.class));
    }

    private Button helpButton;
    private Button goButton;
    private TextView savedNumberTextView;
    private String phone;
    private boolean isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rocks.lechick.android.walkmehome.R.layout.activity_main3);
        phone = SaveHelper.getPhone(this);
        goButton = findViewById(rocks.lechick.android.walkmehome.R.id.goButton);
        helpButton = findViewById(rocks.lechick.android.walkmehome.R.id.helpButton);
        savedNumberTextView = findViewById(rocks.lechick.android.walkmehome.R.id.savedNumberTextView);
        savedNumberTextView.setText(getString(rocks.lechick.android.walkmehome.R.string.saved_number_message, phone));

        goButton.setOnClickListener(v -> {
            if(!isStarted) {
                checkPermissions(granted -> {
                    if(granted) {
                        LocationHelper.setLocationProviderSettings(Main3Activity.this);
                        SmsHelper.scheduleSms(Main3Activity.this, phone);
                        goButton.post(() -> goButton.setText(getString(rocks.lechick.android.walkmehome.R.string.in_save)));
                        isStarted = true;
                    }
                });
            } else {
                SmsHelper.stopSms(Main3Activity.this);
                SmsHelper.sendSms(Main3Activity.this, SaveHelper.getPhone(Main3Activity.this), getString(rocks.lechick.android.walkmehome.R.string.i_m_in_save));
                goButton.post(() -> goButton.setText(getString(rocks.lechick.android.walkmehome.R.string.go)));
                isStarted = false;
            }


        });

        helpButton.setOnClickListener(v -> {
            checkPermissions(granted -> {
                if(granted)
                    SmsHelper.sendEmergencySms(Main3Activity.this, phone);
            });
        });
    }
}
