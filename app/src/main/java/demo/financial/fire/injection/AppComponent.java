package demo.financial.fire.injection;

import javax.inject.Singleton;

import dagger.Component;
import demo.financial.fire.repository.ApiModule;
import demo.financial.fire.weather.WeatherActivity;
import demo.financial.fire.weather.WeatherModule;

@Singleton
@Component(modules = {
        AppModule.class,
        WeatherModule.class,
        ApiModule.class
})
public interface AppComponent {

    void inject(WeatherActivity activity);

}
