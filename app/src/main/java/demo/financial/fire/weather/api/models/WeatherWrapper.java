package demo.financial.fire.weather.api.models;

public class WeatherWrapper {

    private WeatherResponse weatherResponse;

    public WeatherWrapper(WeatherResponse weather) {
        this.weatherResponse = weather;
    }

    public String getCurrentTemp(){
        return String.valueOf(weatherResponse.getMain().getTemp());
    }

    public String getMaxTemp(){
        return String.valueOf(weatherResponse.getMain().getTempMax());
    }

    public String getMinTemp(){
        return String.valueOf(weatherResponse.getMain().getTempMin());
    }

    public String getHumidity(){
        return String.valueOf(weatherResponse.getMain().getHumidity());
    }

    public String getIconCode(){
        return String.valueOf(weatherResponse.getWeather().get(0).getIcon());
    }

    public String getConditionTitle(){
        return weatherResponse.getWeather().get(0).getMain();
    }

    public String getConditionDescription(){
        return weatherResponse.getWeather().get(0).getDescription();
    }

    public String getSunrise(){
        return String.valueOf(weatherResponse.getSys().getSunrise());
    }

    public String getSunset(){
        return String.valueOf(weatherResponse.getSys().getSunset());
    }

    public String getCountry(){
        return String.valueOf(weatherResponse.getSys().getCountry());
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
