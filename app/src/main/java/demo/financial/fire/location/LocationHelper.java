package demo.financial.fire.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.lang.reflect.Array;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

import javax.inject.Inject;

import timber.log.Timber;

import static android.support.constraint.Constraints.TAG;

public class LocationHelper {

    private Context context;
    private Location lastLocation;
    private FusedLocationProviderClient locationClient;

    @Inject
    public LocationHelper(Context context) {
        this.context = context;

        locationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    /**
     * permissions request handled in other dedicated class
     */
    @SuppressLint("MissingPermission")
    public void getLastLocation(Consumer<LocationCoords> response) {

        locationClient.getLastLocation()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        lastLocation = task.getResult();
                        LocationCoords coords = new LocationCoords();
                        coords.setLat(lastLocation.getLatitude());
                        coords.setLon(lastLocation.getLongitude());
                        Timber.i("Coords results: " + String.valueOf(coords.getLat() + String.valueOf(coords.getLon())));
                        response.accept(coords);
                    } else {
                        Timber.d(TAG, "getLastLocation:exception", task.getException());

                    }
                });
    }
}
