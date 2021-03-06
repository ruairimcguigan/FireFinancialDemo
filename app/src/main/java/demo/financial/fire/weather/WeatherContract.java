package demo.financial.fire.weather;

import android.app.Activity;
import android.support.annotation.NonNull;

import demo.financial.fire.api.models.WeatherResponse;
import demo.financial.fire.api.WeatherWrapper;

public interface WeatherContract {

    interface View {

        void showWeather(WeatherWrapper weather);

        void showProgress();

        void hideProgress();

        void showPermissionRequestRationale();

        void showPermissionsSnackbar(final int message,
                                     final int requestId,
                                     android.view.View.OnClickListener listener);
    }

    interface Presenter {

        void attachView(View view);

        void loadWeatherData(String lat, String lon);

        void detach();

        void onSuccess(WeatherResponse weather);

        void onError(Throwable throwable);

        boolean hasLocationPermissions();

        void checkPermissionsRequestRationale(Activity activity);

        void requestPermission(Activity activity);

        void onPermissionDenied(Activity activity);

        void onRequestPermissionResult(Activity activity,
                                       int requestCode,
                                       @NonNull String[] permissions,
                                       @NonNull int[] grantResults);

        void getLastLocation();

        String iconUrlBuilder(String iconCode);
    }

    interface Model {

        void cacheWeatherData();
    }
}
