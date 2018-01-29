package rocks.lechick.android.walkmehome;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import rocks.lechick.android.walkmehome.utils.SaveHelper;


public class MainActivity extends BaseActivity {

    private TextView welcomeTextView;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rocks.lechick.android.walkmehome.R.layout.activity_main);

        //fields init
        welcomeTextView = findViewById(rocks.lechick.android.walkmehome.R.id.welcomeTextView);
        startButton = findViewById(rocks.lechick.android.walkmehome.R.id.startButton);
        //

        welcomeTextView.setText(getString(rocks.lechick.android.walkmehome.R.string.welcome_text));
        checkPermissions(granted -> {
        });
        if (!SaveHelper.getPhone(this).equals("")) {
            Main3Activity.startActivity(this);
            finish();
        }
        startButton.setOnClickListener(v -> {
            Main2Activity.startActivity(MainActivity.this);
            finish();
        });
    }

}
