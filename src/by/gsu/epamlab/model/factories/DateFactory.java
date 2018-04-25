package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.dates.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static by.gsu.epamlab.controller.constants.UtilsConstants.DATE_FORMAT;
import static by.gsu.epamlab.controller.constants.UtilsConstants.EMPTY_STRING;
import static by.gsu.epamlab.controller.constants.UtilsConstants.ONE_DAY;

public enum DateFactory {
    TODAY{
        String todayDate = formatter.format(new Date());

        @Override
        String getDate() {
            return todayDate;
        }
    },
    TOMORROW{
        String tomorrowDay = formatter.format(DateUtil.addDays(new Date(), ONE_DAY));

        @Override
        String getDate() {
            return tomorrowDay;
        }
    },
    SOMEDAY{
        @Override
        String getDate() {
            return EMPTY_STRING;
        }
    };

    abstract String getDate();

    protected DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    public static String tasksDate(String date){
        return valueOf(date.toUpperCase()).getDate();
    }

}
