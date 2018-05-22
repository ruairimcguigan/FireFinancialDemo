package demo.financial.fire.injection;

import javax.inject.Singleton;

import dagger.Component;
import demo.financial.fire.weather.WeatherActivity;

@Singleton
@Component(modules = {
        AppModule.class,
})
public interface AppComponent {

    void inject(WeatherActivity activity);

}
