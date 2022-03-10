package Projekt;

import java.util.ArrayList;

public class Movie {
    private String year;
    private String length;
    private String title;
    private String subject;
    private String popularity;
    private String awards;

    public Movie(String year, String length, String title, String subject, String popularity, String awards){
        this.year=year;
        this.length=length;
        this.title=title;
        this.subject=subject;
        this.popularity=popularity;
        this.awards=awards;
    }

    public String getYear() {
        return year;
    }
    public String getLength() {
        return length;
    }
    public String getTitle() {
        return title;
    }
    public String getSubject() {
        return subject;
    }
    public String getPopularity() {
        return popularity;
    }
    public String isAwards() {
        return awards;
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        data.add("Year;" + year);
        data.add("Length;" + length);
        data.add("Title;" + title);
        data.add("Subject;" + subject);
        data.add("Popularity;" + popularity);
        data.add("Awards;" + isAwards());
        return data;
    }

    public String toString(){
        return "Title: " + title + ", Length: " + length + " minutes.\n"
                + "Subject: " + subject + ", Popularity: " + popularity + ", Has won awards?: " + awards;
    }
}
