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
import timber.log.Timber;

public class WeatherApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            initDagger();
            Fresco.initialize(this);
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
            Sherlock.init(this);
            Traceur.enableLogging();
        }
    }

    private void initDagger() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return component;
    }
}
