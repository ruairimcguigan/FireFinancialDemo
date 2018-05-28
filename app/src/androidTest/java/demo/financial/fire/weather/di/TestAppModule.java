package demo.financial.fire.weather.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import demo.financial.fire.api.WeatherAPI;
import demo.financial.fire.location.LocationHelper;
import demo.financial.fire.util.PermissionsChecker;
import demo.financial.fire.weather.WeatherContract;
import demo.financial.fire.weather.WeatherModel;
import demo.financial.fire.weather.rest.MockWeatherApi;

@Module
public class TestAppModule {

    private final Application application;

    public TestAppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    public Context provideAppContext() {
        return application;
    }

    @Provides
    public WeatherAPI provideWeatherApiClient() {
        return new MockWeatherApi();
    }

    @Provides
    WeatherContract.Model provideWeatherModel(){
        return new WeatherModel();
    }

    @Provides
    LocationHelper provideLocationHelper(Application context){
        return new LocationHelper(context);
    }

    @Provides
    PermissionsChecker providePermissionsCheck(Application context){
        return new PermissionsChecker(context);
    }

    @Provides
    MockWeatherApi provideMockApi(){
        return new MockWeatherApi();
    }
}
