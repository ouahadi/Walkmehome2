package rocks.lechick.android.walkmehome;

import android.Manifest;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
 * Created by Gleb
 * TulaCo 
 * 1/24/2018
 */

public abstract class BaseActivity extends AppCompatActivity {


    public void checkPermissions(Consumer<Boolean> consumer) {
        RxPermissions rxPermissions = new RxPermissions(this);
        Observable.just(new Object()).compose(rxPermissions.ensure(Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION))
                .doAfterNext(granted -> {
                    if(!granted) {
                        showInfoMessage(getString(rocks.lechick.android.walkmehome.R.string.permission_error), getWindow().getDecorView().getRootView());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(consumer);


    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    public void showInfoMessage(String message, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .show();
    }
}
