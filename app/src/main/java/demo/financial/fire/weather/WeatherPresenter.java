package demo.financial.fire.weather;

import java.util.Arrays;

import javax.inject.Inject;

import demo.financial.fire.BuildConfig;
import demo.financial.fire.weather.WeatherContract.Model;
import demo.financial.fire.weather.WeatherContract.Presenter;
import demo.financial.fire.weather.WeatherContract.View;
import demo.financial.fire.weather.api.WeatherAPI;
import demo.financial.fire.weather.api.models.WeatherResponse;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class WeatherPresenter implements Presenter {

    private final CompositeDisposable disposable = new CompositeDisposable();
    private void addDisposable(Disposable disposable) {
        this.disposable.add(disposable);
    }
    private Model model;
    private View view;

    @Inject
    WeatherPresenter(Model model) {
        this.model = model;
    }

    @Inject
    WeatherAPI weatherService;

    @Override
    public void attachView(View view) {
        if (view != null){
            this.view = view;
        }
    }

    @Override
    public void loadWeatherData() {
            addDisposable(weatherService
                    .getCurrentWeatherForLocation("35", "139", BuildConfig.API_KEY, "metric")
                    .observeOn(mainThread())
                    .doOnSubscribe(__ -> view.showProgress())
                    .doOnError(this::onError)
                    .doOnTerminate(() -> view.hideProgress())
                    .subscribe(this::onSuccess));
    }

    @Override
    public void onSuccess(WeatherResponse weather) {
        Timber.d("Received weather successfully: ", weather.toString());
        view.showWeather(weather);
    }

    @Override
    public void onError(Throwable throwable) {
        // TODO: 22/05/2018 to be implemented
        Timber.d("Error retrieving weather", Arrays.toString(throwable.getStackTrace()));
    }

    @Override
    public void detach() {

    }

}
