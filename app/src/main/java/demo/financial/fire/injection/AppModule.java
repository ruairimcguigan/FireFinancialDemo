package demo.financial.fire.injection;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.financial.fire.util.PermissionsChecker;
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
    WeatherActivity provideWeatherActivity(){
        return new WeatherActivity();
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    PermissionsChecker providePermissionsCheck(Application context){
        return new PermissionsChecker(context);
    }
}