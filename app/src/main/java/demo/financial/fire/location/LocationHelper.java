package demo.financial.fire.location;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class LocationHelper {

    private String latitude;
    private String longitude;
    private Context context;
    private Location lastLocation;
    private FusedLocationProviderClient locationClient;

    public LocationHelper(Context context) {
        this.context = context;

        locationClient = LocationServices.getFusedLocationProviderClient(context);
    }


}
