package demo.financial.fire.weather;

import demo.financial.fire.WeatherApplication;
import demo.financial.fire.weather.di.DaggerTestAppComponent;
import demo.financial.fire.weather.di.TestAppComponent;
import demo.financial.fire.weather.di.TestAppModule;

public class TestWeatherApplication extends WeatherApplication {

    private TestAppComponent testAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        testAppComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(this))
                .build();
    }

    @Override
    public TestAppComponent getAppComponent() {
        return testAppComponent;
    }
}
