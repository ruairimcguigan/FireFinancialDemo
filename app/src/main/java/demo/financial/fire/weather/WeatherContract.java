package demo.financial.fire.weather;

import demo.financial.fire.weather.api.models.WeatherResponse;

public interface WeatherContract {

    interface View {

        void showWeather(WeatherResponse weather);

        void showProgress();

        void hideProgress();

    }

    interface Presenter {

        void attachView(View view);

        void loadWeatherData();

        void detach();

        void onSuccess(WeatherResponse weather);

        void onError(Throwable throwable);
    }

    interface Model {

        void cacheWeatherData();
    }
}
