package demo.financial.fire.weather.di;

import dagger.Component;
import demo.financial.fire.injection.AppComponent;
import demo.financial.fire.weather.test.WeatherActivityTest;

@Component(modules = TestAppModule.class)
public interface TestAppComponent extends AppComponent {

    void inject(WeatherActivityTest test);
}
