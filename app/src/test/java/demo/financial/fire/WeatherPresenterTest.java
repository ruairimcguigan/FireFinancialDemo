package demo.financial.fire;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import demo.financial.fire.api.models.WeatherResponse;
import demo.financial.fire.weather.WeatherContract.Model;
import demo.financial.fire.weather.WeatherContract.View;
import demo.financial.fire.weather.WeatherPresenter;

public class WeatherPresenterTest {

    @Mock
    private Model model;
    @Mock
    private View view;

    private WeatherPresenter presenter;
    private WeatherResponse weather;

    @Before
    public void setup(){
        weather = new WeatherResponse();

    }

    @Test
    public void ifUserHasNotProvidedPreviouslyPermissions_SnackbarBarShouldInitiatePermissionsRequestSequence() {

    }
}