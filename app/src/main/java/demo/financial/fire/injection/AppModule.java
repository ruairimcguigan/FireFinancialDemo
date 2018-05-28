package demo.financial.fire.injection;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.financial.fire.location.LocationHelper;
import demo.financial.fire.util.Formatter;
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

    @Provides
    @Singleton
    LocationHelper provideLocationHelper(Application context){
        return new LocationHelper(context);
    }

    @Provides
    @Singleton
    public Formatter provideFormatter(){
        return new Formatter();
    }
}