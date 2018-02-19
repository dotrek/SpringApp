package com.ttpsc.otapowiczp.springApp.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.ttpsc.otapowiczp.springApp.converters.DateToYearConverter;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@XStreamAlias("movie")
public class Movie {
    @XStreamOmitField
    private long id;

    @XStreamAlias("title")
    private String title;

    @XStreamAlias("country")
    private String country;

    @XStreamAlias("year")
    private LocalDate year;

    @XStreamAlias("state")
    private MovieState state;

    private static final AtomicLong counter = new AtomicLong(100);

    public Movie() {
        this.id = counter.incrementAndGet();
        this.state = MovieState.AVAILABLE;
    }

    public Movie(String title, String country, String year, MovieState state) {
        this.title = title;
        this.country = country;
        this.year = DateToYearConverter.parseStringToDate(year);
        this.state = state;
        this.id = counter.incrementAndGet();
    }

    public String getTitle() {
        return title;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getYear() {
        return year;
    }

    public long getId() {
        return id;
    }

    public String getState() {
        return state.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setYear(int year) {
        this.year = LocalDate.ofYearDay(year, 1);
    }

    public void changeState() {
        if (this.state == MovieState.UNAVAILABLE)
            this.state = MovieState.AVAILABLE;
        else this.state = MovieState.UNAVAILABLE;
    }

    public String toString() {
        return this.title + " " + this.country + " " + this.year;
    }

    public boolean isAvailable() {
        return this.state == MovieState.AVAILABLE;
    }

    public enum MovieState {
        AVAILABLE,
        UNAVAILABLE
    }
}
