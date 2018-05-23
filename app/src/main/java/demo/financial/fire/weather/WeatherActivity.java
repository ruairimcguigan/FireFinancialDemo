package demo.financial.fire.weather;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import demo.financial.fire.R;
import demo.financial.fire.WeatherApplication;
import demo.financial.fire.api.WeatherWrapper;
import timber.log.Timber;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;
import static android.support.design.widget.Snackbar.make;
import static android.view.View.VISIBLE;
import static butterknife.ButterKnife.bind;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

    @Inject
    WeatherPresenter presenter;

    @BindView(R.id.root_view)
    ViewGroup container;

    @BindView(R.id.progress)
    ViewGroup progressBar;

    @BindView(R.id.activity_weather_current_weather_image)
    ImageView weatherImage;

    @BindView(R.id.activity_weather_city_name)
    TextView cityName;

    @BindView(R.id.activity_weather_description)
    TextView description;

    @BindView(R.id.activity_weather_current_temp)
    TextView currentTemp;

    @BindView(R.id.activity_weather_min_temp)
    TextView minTemp;

    @BindView(R.id.activity_weather_max_temp)
    TextView maxtemp;

    @BindView(R.id.activity_weather_sunrise)
    TextView sunrise;

    @BindView(R.id.activity_weather_sunset)
    TextView sunset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        bind(this);

        ((WeatherApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);

        if (!presenter.hasLocationPermissions()) {
            requestPermissionsRequestRationale();
        } else {
            presenter.getLastLocation();
        }
    }

    private void requestPermissionsRequestRationale() {
        presenter.checkPermissionsRequestRationale(this);
    }

    @Override
    public void showPermissionsSnackbar(final int message, final int requestId,
                                        View.OnClickListener listener) {
        make(findViewById(android.R.id.content), getString(message), LENGTH_INDEFINITE)
                .setAction(getString(requestId), listener).show();
    }

    @Override
    public void showPermissionRequestRationale() {
        showPermissionsSnackbar(R.string.permission_request_rationale, android.R.string.ok,
                view -> presenter.requestPermission(this));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void showWeather(WeatherWrapper weather) {
        weatherImage.setImageURI(Uri.parse(presenter.iconUrlBuilder(weather.getIconCode())));
        Timber.i("IMAGE: ", presenter.iconUrlBuilder(weather.getIconCode()));
        cityName.setText(weather.getPlace());
        description.setText(weather.getConditionDescription());
        currentTemp.setText(weather.getCurrentTemp());
        minTemp.setText(weather.getMinTemp());
        maxtemp.setText(weather.getMaxTemp());
        sunrise.setText(weather.getSunrise());
        sunset.setText(weather.getSunset());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detach();
    }
}
