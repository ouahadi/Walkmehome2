package rocks.lechick.android.walkmehome.utils;
/*
 * Created by Gleb
 * TulaCo 
 * 1/29/2018
 */

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rocks.lechick.android.walkmehome.LocationServerDTO;

public class ApiHelper {
    public static void sendRequest(LocationServerDTO locationServerDTO) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://walkmehome.ml/srv/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        LocationServerDTO[] dtoS = new LocationServerDTO[1];
        dtoS[0] = locationServerDTO;
        apiService.sendLocation(dtoS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {}, error -> {});
    }
}
