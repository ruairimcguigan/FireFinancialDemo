package demo.financial.fire.injection;

import javax.inject.Singleton;

import dagger.Component;
import demo.financial.fire.MainActivity;

@Singleton
@Component(modules = {
        AppModule.class,
})
public interface AppComponent {

    void inject(MainActivity activity);

}
