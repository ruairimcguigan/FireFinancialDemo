package demo.financial.fire.util;

import android.content.res.Resources;

import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import static android.text.format.DateFormat.format;
import static java.lang.Math.toIntExact;
import static java.lang.String.valueOf;
import static java.util.Calendar.getInstance;

public class Formatter {

    @Inject
    public Formatter() {
    }

    public String epochToDate(long timestamp) {
        Calendar cal = getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp * 1000L);
        return format("hh:mm:ss", cal).toString();
    }

    public String formatWithMeasurementUnit(Resources res, int unit, double value){
        int roundUp = toIntExact(Math.round(value));
        return String.format(valueOf(res.getString(unit)), roundUp);
    }
}
