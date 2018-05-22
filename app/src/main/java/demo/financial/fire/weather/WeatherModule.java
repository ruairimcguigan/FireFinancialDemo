package demo.financial.fire.weather;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.financial.fire.weather.WeatherContract.Model;
import demo.financial.fire.weather.WeatherContract.Presenter;

@Module
public class WeatherModule {

    @Provides
    @Singleton
    Presenter provideWeatherPresenter(Model model) {
        return new WeatherPresenter(model);
    }

    @Provides
    @Singleton
    Model provideWeatherModel(){
        return new WeatherModel();
    }

}
