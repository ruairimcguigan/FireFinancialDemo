package demo.financial.fire.weather.api;

import demo.financial.fire.weather.api.models.WeatherResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("weather")
    Observable<WeatherResponse> getCurrentWeatherForLocation(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("APPID") String appId,
            @Query("units") String units);
}
