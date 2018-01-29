package rocks.lechick.android.walkmehome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import rocks.lechick.android.walkmehome.utils.SaveHelper;


public class Main2Activity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, Main2Activity.class));
    }

    private EditText numberFieldEditText;
    private Button acceptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rocks.lechick.android.walkmehome.R.layout.activity_main2);
        numberFieldEditText = findViewById(rocks.lechick.android.walkmehome.R.id.numberFieldEditText);
        acceptButton = findViewById(rocks.lechick.android.walkmehome.R.id.acceptButton);
        acceptButton.setOnClickListener(v -> {
            SaveHelper.savePhone(Main2Activity.this, numberFieldEditText.getText().toString());
            Main3Activity.startActivity(Main2Activity.this);
        });

    }

}
