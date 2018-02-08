package rocks.lechick.android.walkmehome;
/*
 * Created by Gleb
 * TulaCo 
 * 1/29/2018
 */

public class LocationServerDTO {

    public LocationServerDTO(String email, String loc_date, String LAT, String LON) {
        this.email = email;
        this.loc_date = loc_date;
        this.LAT = LAT;
        this.LON = LON;
    }

    public String email;
    public String loc_date;
    public String LAT;
    public String LON;
}
