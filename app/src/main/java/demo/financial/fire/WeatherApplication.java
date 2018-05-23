package demo.financial.fire;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.singhajit.sherlock.core.Sherlock;
import com.squareup.leakcanary.LeakCanary;
import com.tspoon.traceur.Traceur;

import demo.financial.fire.injection.AppComponent;
import demo.financial.fire.injection.AppModule;
import demo.financial.fire.injection.DaggerAppComponent;
import demo.financial.fire.repository.ApiModule;
import demo.financial.fire.weather.WeatherModule;
import timber.log.Timber;

public class WeatherApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Fresco.initialize(this);
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
            Sherlock.init(this);
            Traceur.enableLogging();
            initDagger();
        }
    }

    private void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .weatherModule(new WeatherModule())
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(this, BuildConfig.BASE_URL))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
