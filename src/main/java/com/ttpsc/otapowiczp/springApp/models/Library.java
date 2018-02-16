package com.ttpsc.otapowiczp.springApp.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

public class Library {
    @XStreamImplicit
    @XStreamAlias("movies")
    private static final List<Movie> movies = new ArrayList<>();

    private Library() {
    }

    static {
        movies.add(new Movie("Welcome to The Jungle", "PL", "2001", Movie.MovieState.AVAILABLE));
        movies.add(new Movie("Matrix", "USA", "1999", Movie.MovieState.UNAVAILABLE));
        movies.add(new Movie("Lord Of the Rings", "USA", "2001", Movie.MovieState.AVAILABLE));
        movies.add(new Movie("Just do it", "ENG", "2008", Movie.MovieState.UNAVAILABLE));
        movies.add(new Movie("Well done", "ES", "2001", Movie.MovieState.AVAILABLE));
        movies.add(new Movie("Lorem ipsum", "PT", "2010", Movie.MovieState.AVAILABLE));
        movies.add(new Movie("Pepiczki", "CZ", "1970", Movie.MovieState.AVAILABLE));
        movies.add(new Movie("Who stole my Lada?", "RUS", "2018", Movie.MovieState.AVAILABLE));
    }

    public static List<Movie> getInstance() {
        return movies;
    }
}