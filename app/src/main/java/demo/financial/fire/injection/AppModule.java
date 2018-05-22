package demo.financial.fire.injection;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

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

}
