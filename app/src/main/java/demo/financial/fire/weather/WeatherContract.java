package demo.financial.fire.weather;

public interface WeatherContract {

    interface View {

        void showWeather();

        void showProgress();

        void hideProgress();

    }

    interface Presenter {

        void attachView(View view);

        void onWeatherDataLoaded();

        void detach();

        void onSuccess();

        void onError();
    }

    interface Model {

        void cacheWeatherData();
    }
}
