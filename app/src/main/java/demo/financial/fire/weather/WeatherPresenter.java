package demo.financial.fire.weather;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import java.net.URLConnection;

import javax.inject.Inject;

import demo.financial.fire.BuildConfig;
import demo.financial.fire.R;
import demo.financial.fire.api.WeatherAPI;
import demo.financial.fire.location.LocationHelper;
import demo.financial.fire.util.PermissionsChecker;
import demo.financial.fire.weather.WeatherContract.Model;
import demo.financial.fire.weather.WeatherContract.Presenter;
import demo.financial.fire.weather.WeatherContract.View;
import demo.financial.fire.api.models.WeatherResponse;
import demo.financial.fire.api.WeatherWrapper;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS;
import static android.support.constraint.Constraints.TAG;
import static demo.financial.fire.BuildConfig.APPLICATION_ID;
import static demo.financial.fire.BuildConfig.BASE_ICON_URL;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static java.lang.String.valueOf;

public class WeatherPresenter implements Presenter {

    @Inject
    PermissionsChecker permissionsChecker;

    @Inject
    LocationHelper locationHelper;

    private final CompositeDisposable disposable = new CompositeDisposable();

    private void addDisposable(Disposable disposable) {
        this.disposable.add(disposable);
    }

    private Model model;
    private View view;

    @Inject
    WeatherPresenter(Model model) {
        this.model = model;
    }

    @Inject
    WeatherAPI weatherService;

    @Override
    public void attachView(View view) {
        if (view != null) {
            this.view = view;
        }
    }

    public void checkPermissionsRequestRationale(Activity activity) {
        Timber.i(TAG, "Displaying permission rationale to provide additional context.");
        view.showPermissionRequestRationale();
    }

    public void requestPermission(Activity activity) {
        Log.i(TAG, "Requesting permission");
        permissionsChecker.startLocationPermissionRequest(activity);
    }

    @Override
    public void loadWeatherData(String lat, String lon) {
        addDisposable(weatherService
                .getCurrentWeatherForLocation(lat, lon, BuildConfig.API_KEY, "metric")
                .observeOn(mainThread())
                .doOnSubscribe(__ -> view.showProgress())
                .doOnError(this::onError)
                .doOnTerminate(() -> view.hideProgress())
                .subscribe(this::onSuccess));
    }

    @Override
    public void onSuccess(WeatherResponse weather) {
        view.hideProgress();
        view.showWeather(wrapWeather(weather));
    }

    @Override
    public void onError(Throwable throwable) {
        view.showPermissionsSnackbar(R.string.fetch_error, 0, null);
    }

    @Override
    public boolean hasLocationPermissions() {
        view.showProgress();
        return permissionsChecker.hasPermissions(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION);
    }

    @Override
    public void onPermissionDenied(Activity activity) {
        view.showPermissionsSnackbar(R.string.permission_denied,
                R.string.settings, v -> allowSettingsAdjustment(activity));
    }

    private void allowSettingsAdjustment(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    public void onRequestPermissionResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Timber.i(TAG, "onRequestPermissionResult");
        if (requestCode == permissionsChecker.getPermissionsRequestCode()) {
            if (grantResults.length <= 0) {
                Timber.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                onPermissionDenied(activity);
            }
        }
    }

    @Override
    public void getLastLocation() {
        locationHelper.getLastLocation(locationCoords ->
                loadWeatherData(valueOf(locationCoords.getLat()), valueOf(locationCoords.getLon())));
    }

    @Override
    public String iconUrlBuilder(String iconCode) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(BASE_ICON_URL)
                .appendPath("img")
                .appendPath("w")
                .appendPath(iconCode);
        return builder.build().toString().concat(".png");
    }


    private WeatherWrapper wrapWeather(WeatherResponse response){
        return new WeatherWrapper(response);
    }

    @Override
    public void detach() {
        if (!disposable.isDisposed()) {
            disposable.clear();
        }
    }
}
