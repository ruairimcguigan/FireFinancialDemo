package demo.financial.fire.weather;

import java.util.List;

public interface WeatherContract {

    interface View {

        void showWeather();

        void showProgress();

        void hideProgress();

    }

    interface Presenter {

        void attachView(View view);

        void onWeatherDataLoaded();

    }

    interface Model {

        void cacheWeatherData();
    }
}
