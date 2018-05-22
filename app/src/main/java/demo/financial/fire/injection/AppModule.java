package demo.financial.fire.injection;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import demo.financial.fire.weather.WeatherActivity;

@Module()
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    WeatherActivity provideWeatherActivity(){
        return new WeatherActivity();
    }

}
