package com.example.android.walkmehome;

import android.Manifest;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/*
 * Created by Gleb
 * TulaCo 
 * 1/24/2018
 */

public abstract class BaseActivity extends AppCompatActivity {

    private View contentView;

    public void checkPermissions(Consumer<Boolean> consumer) {
        RxPermissions rxPermissions = new RxPermissions(this);
        Observable.empty().compose(rxPermissions.ensure(Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_COARSE_LOCATION))
                .doAfterNext(granted -> {
                    if(!granted) {
                        showInfoMessage(getString(R.string.permission_error), contentView);
                    }
                }).subscribe(consumer);


    }

    @Override
    public void setContentView(View view) {
        contentView = view;
        super.setContentView(view);
    }

    public void showInfoMessage(String message, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .show();
    }
}
