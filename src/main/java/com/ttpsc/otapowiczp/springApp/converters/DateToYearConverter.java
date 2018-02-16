package com.ttpsc.otapowiczp.springApp.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateToYearConverter {

    public static final String YEAR_PATTERN = "uuuu";

    public static LocalDate parseStringToDate(String year) {
        return LocalDate.ofYearDay(Integer.valueOf(year),1);
    }

    public static int getYearFromDate(LocalDate sourceDate) {
        return sourceDate.getYear();
    }
}