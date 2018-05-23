package demo.financial.fire.api;

import demo.financial.fire.api.models.Sys;
import demo.financial.fire.api.models.WeatherResponse;
import demo.financial.fire.api.models.Wind;

import static java.lang.String.valueOf;

public class WeatherWrapper {

    private WeatherResponse weatherResponse;

    public WeatherWrapper(WeatherResponse weather) {
        this.weatherResponse = weather;
    }

    public String getCurrentTemp(){
        return valueOf(weatherResponse.getMain().getTemp());
    }

    public String getMaxTemp(){
        return valueOf(weatherResponse.getMain().getTempMax());
    }

    public String getMinTemp(){
        return valueOf(weatherResponse.getMain().getTempMin());
    }

    public String getHumidity(){
        return valueOf(weatherResponse.getMain().getHumidity());
    }

    public String getIconCode(){
        return valueOf(weatherResponse.getWeather().get(0).getIcon());
    }

    public String getConditionTitle(){
        return weatherResponse.getWeather().get(0).getMain();
    }

    public String getConditionDescription(){
        return weatherResponse.getWeather().get(0).getDescription();
    }

    public String getSunrise(){
        return valueOf(weatherResponse.getSys().getSunrise());
    }

    public String getSunset(){
        return valueOf(weatherResponse.getSys().getSunset());
    }

    public String getCountry(){
        return valueOf(weatherResponse.getSys().getCountry());
    }

    public Sys getSys(){
        return weatherResponse.getSys();
    }

    public Wind getWind(){
        return weatherResponse.getWind();
    }

    public String getPlace(){
        return weatherResponse.getName();
    }
}
