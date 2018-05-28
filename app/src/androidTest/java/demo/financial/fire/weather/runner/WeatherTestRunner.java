package demo.financial.fire.weather.runner;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import demo.financial.fire.weather.TestWeatherApplication;

public class WeatherTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        String testApplicationClassName = TestWeatherApplication.class.getCanonicalName();
        return super.newApplication(cl, testApplicationClassName, context);
    }
}
