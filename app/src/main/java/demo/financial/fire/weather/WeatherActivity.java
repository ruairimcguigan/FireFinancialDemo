package demo.financial.fire.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import demo.financial.fire.R;
import demo.financial.fire.WeatherApplication;
import demo.financial.fire.weather.api.models.WeatherResponse;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;
import static android.support.design.widget.Snackbar.make;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

    @Inject
    WeatherPresenter presenter;

    @BindView(R.id.root_view)
    ViewGroup rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void showWeather(WeatherResponse weather) {
        Toast.makeText(this, weather.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detach();
    }
}
