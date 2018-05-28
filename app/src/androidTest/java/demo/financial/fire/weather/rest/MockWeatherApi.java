package demo.financial.fire.weather.rest;

import com.google.gson.Gson;

import demo.financial.fire.api.WeatherAPI;
import demo.financial.fire.api.models.WeatherResponse;
import io.reactivex.Observable;

import static demo.financial.fire.weather.data.TestData.DERRY_WEATHER_DATA_JSON;

public class MockWeatherApi implements WeatherAPI {

    @Override
    public Observable<WeatherResponse> getCurrentWeatherForLocation(String lat, String lon, String appId, String units) {
        WeatherResponse weatherWrapper = new Gson().fromJson(DERRY_WEATHER_DATA_JSON, WeatherResponse.class);
        return Observable.just(weatherWrapper);
        }
}
