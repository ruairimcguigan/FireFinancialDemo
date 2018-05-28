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

    public double getCurrentTemp(){
        return weatherResponse.getMain().getTemp();
    }

    public double getMaxTemp(){
        return weatherResponse.getMain().getTempMax();
    }

    public double getMinTemp(){
        return weatherResponse.getMain().getTempMin();
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

    public long getSunrise(){
        return weatherResponse.getSys().getSunrise();
    }

    public long getSunset(){
        return weatherResponse.getSys().getSunset();
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
