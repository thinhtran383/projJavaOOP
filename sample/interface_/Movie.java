package interface_;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Movie implements Comparable<Movie> {
    private String name;
    private int year;
    private String director;
    private float price;
    private float star;

    public Movie() {}

    public Movie(String name, int year, String director, float price, float star) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.price = price;
        this.star = star;
    }

    @Override
    public int compareTo(Movie o) {
        return this.name.compareTo(o.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }
}

class run {
    public static void main(String[] args) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Thor", 2011, "ABC", 59, 4.4f));
        movies.add(new Movie("Iron man", 2013, "DEF", 75, 4.5f));
        movies.add(new Movie("Captain America", 2012, "GHI", 69, 4.6f));
        movies.add(new Movie("Avengers", 2015, "JKL", 89, 4.7f));
        System.out.println("Before sort: ");
        showMovie(movies);
        Collections.sort(movies, new SortByName());
        System.out.println("After sort: ");
        showMovie(movies);
    }

    private static void showMovie(ArrayList<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie.getName() + " " + movie.getYear() + " " + movie.getDirector() + " "
                    + movie.getPrice() + " " + movie.getStar());
        }
    }
}

class SortByName implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
