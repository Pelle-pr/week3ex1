/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;

/**
 *
 * @author Tha-Y
 */
public class DTOmovie {
    
    
   private String title;
   private int year; 
   private String[] actors;

    public DTOmovie(Movie movie) {
        this.title = movie.getTitle();
        this.year =movie.getYear();
        this.actors= movie.getActors();
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }
    
}
