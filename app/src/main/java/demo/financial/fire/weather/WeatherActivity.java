package demo.financial.fire.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import demo.financial.fire.R;
import demo.financial.fire.WeatherApplication;
import demo.financial.fire.weather.WeatherContract.View;
import demo.financial.fire.weather.api.models.WeatherResponse;

public class WeatherActivity extends AppCompatActivity implements View{

    @Inject
    WeatherPresenter presenter;

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
        presenter.loadWeatherData();
    }

    @Override
    public void showWeather(WeatherResponse weather) {
        // TODO: 23/05/2018  
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
