package demo.financial.fire.location;

import java.util.HashSet;
import java.util.Set;

import demo.financial.fire.weather.api.models.Main;
import demo.financial.fire.weather.api.models.WeatherResponse;

public class Locale {

    public Locale() { }

    public boolean checkLocale(String country){
        return imperialList().contains(country);
    }

    private Set<String> imperialList(){

        Set<String> countries = new HashSet<>();
        countries.add("BS, BZ, KY, PW, US, PR, GU, VI");
        return countries;
    }

    public Main convertIfImperial(WeatherResponse weather) {
        Main imperialTemps = new Main();
        imperialTemps.setTemp(convertToFahrenheit(weather.getMain().getTemp()));
        imperialTemps.setTempMax(convertToFahrenheit(weather.getMain().getTempMax()));
        imperialTemps.setTemp(convertToFahrenheit(weather.getMain().getTempMin()));
        return imperialTemps;
    }

    private double convertToFahrenheit(Double fahrenheit){
        return (((5/9)*(fahrenheit - 32)));
    }
}
