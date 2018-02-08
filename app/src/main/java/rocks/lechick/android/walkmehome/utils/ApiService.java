package rocks.lechick.android.walkmehome.utils;
/*
 * Created by Gleb
 * TulaCo 
 * 1/29/2018
 */

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rocks.lechick.android.walkmehome.LocationServerDTO;

public interface ApiService {

    @POST("send_track")
    public Observable<Void> sendLocation(@Body LocationServerDTO[] locationServerDTOArray);
}
