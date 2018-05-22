package demo.financial.fire.weather;

import javax.inject.Inject;

import demo.financial.fire.weather.WeatherContract.Model;
import demo.financial.fire.weather.WeatherContract.Presenter;
import demo.financial.fire.weather.WeatherContract.View;

public class WeatherPresenter implements Presenter {

    private Model model;
    private View view;

    @Inject
    WeatherPresenter(Model model) {
        this.model = model;
    }

    @Override
    public void attachView(View view) {
        if (view != null){
            this.view = view;
        }
    }

    @Override
    public void onWeatherDataLoaded() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void detach() {

    }

}
