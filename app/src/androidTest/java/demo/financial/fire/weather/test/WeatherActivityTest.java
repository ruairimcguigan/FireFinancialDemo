package demo.financial.fire.weather.test;


import android.app.Application;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import javax.inject.Inject;

import demo.financial.fire.BuildConfig;
import demo.financial.fire.R;
import demo.financial.fire.api.models.WeatherResponse;
import demo.financial.fire.util.Formatter;
import demo.financial.fire.weather.TestWeatherApplication;
import demo.financial.fire.weather.WeatherActivity;
import demo.financial.fire.weather.rest.MockWeatherApi;
import demo.financial.fire.weather.util.UiAutomatorUtils;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.uiautomator.UiDevice.getInstance;
import static demo.financial.fire.weather.util.UiAutomatorUtils.assertViewWithTextIsVisible;
import static demo.financial.fire.weather.util.UiAutomatorUtils.childAtPosition;
import static demo.financial.fire.weather.util.UiAutomatorUtils.denyPermissionsIfNeeded;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WeatherActivityTest {

    @Inject
    MockWeatherApi weatherApi;

    @Inject
    Formatter formatter;

    @Inject
    Application application;

    private UiDevice device;
    private Resources res;

    @Rule
    public ActivityTestRule<WeatherActivity> activityTestRule = new ActivityTestRule<>(WeatherActivity.class);

    @Before
    public void setUp() {
        this.device = getInstance(getInstrumentation());

//        res = application.getResources();

        ((TestWeatherApplication) activityTestRule.getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Test
    public void a_weatherActivityTest() {
        onView(allOf(withId(R.id.snackbar_action), withText("OK"),
                childAtPosition(childAtPosition(
                        withClassName(is("android.support.design.widget.Snackbar$SnackbarLayout")), 0), 1),
                isDisplayed())).perform(click());
    }

    @Test
    public void b_shouldDisplayPermissionRequestDialogAtStartup() {

        SystemClock.sleep(1000);

        assertViewWithTextIsVisible(device, UiAutomatorUtils.TEXT_ALLOW);
        assertViewWithTextIsVisible(device, UiAutomatorUtils.TEXT_DENY);

        // cleanup for the next test
        denyPermissionsIfNeeded(device);
    }

    @Test
    public void c_clickDenySecondTime() {
        denyPermissionsIfNeeded(device);
    }

    @Test
    public void d_shouldDisplayPermissionDenialMessageInSnackbar() {
        onView(allOf(withText(R.string.permission_denied), isDisplayed()));
    }

    @Test
    public void e_userShouldBeTakenToSettingsToAdjustPermissions() {
        UiAutomatorUtils.allowPermissionsIfNeeded(device);
    }

    @Test
    public void f_correctWeatherDataDisplayed() {

        WeatherResponse weatherData = weatherApi.getCurrentWeatherForLocation(
                "55.01", "-7.34", BuildConfig.API_KEY, "metric").blockingFirst();


        onView(withId(R.id.activity_weather_city_name)).check(matches(withText(weatherData.getName())));


        onView(withId(R.id.activity_weather_description)).check(matches(withText(weatherData.getWeather().get(0).getDescription())));

        onView(withId(R.id.activity_weather_current_temp)).check(matches(withText(
                formatter.formatWithMeasurementUnit(
                        res, R.string.temperature_with_value, weatherData.getMain().getTemp()))));

        onView(withId(R.id.activity_weather_max_temp)).check(matches(withText(
                formatter.formatWithMeasurementUnit(
                        res, R.string.temperature_with_value, weatherData.getMain().getTempMax()))));

        onView(withId(R.id.activity_weather_min_temp)).check(matches(withText(
                formatter.formatWithMeasurementUnit(
                        res, R.string.temperature_with_value, weatherData.getMain().getTempMin()))));

        onView(withId(R.id.activity_weather_sunrise)).check(matches(withText(formatter.epochToDate(weatherData.getSys().getSunrise()))));

        onView(withId(R.id.activity_weather_sunset)).check(matches(withText(formatter.epochToDate(weatherData.getSys().getSunset()))));

        onView(withId(R.id.activity_weather_wind_speed)).check(matches(withText(
                formatter.formatWithMeasurementUnit(
                        res, R.string.wind_speed_with_value, weatherData.getWind().getSpeed()))));
    }
}

