package demo.financial.fire.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import demo.financial.fire.R;
import demo.financial.fire.WeatherApplication;
import demo.financial.fire.weather.WeatherContract.Presenter;
import demo.financial.fire.weather.WeatherContract.View;

public class WeatherActivity extends AppCompatActivity implements View{

    @Inject
    Presenter presenter;

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
    }

    @Override
    public void showWeather() {

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
