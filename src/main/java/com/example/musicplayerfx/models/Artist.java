package com.example.musicplayerfx.models;


import com.example.musicplayerfx.enums.Genre;

import java.time.LocalDate;
import java.util.ArrayList;

public class Artist {
    private String name;
    private LocalDate yearOfBirth;
    private Genre genre;
    private ArrayList<Album> albums;

    public Artist(String name, LocalDate yearOfBirth, Genre genre, ArrayList<Album> albums) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.genre = genre;
        this.albums = albums;
    }

    public Artist(String artistName) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(LocalDate yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return String.format("Artist %s  %d  %s  %s}",
                name, yearOfBirth, genre, albums);

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
