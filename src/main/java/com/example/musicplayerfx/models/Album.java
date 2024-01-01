package com.example.musicplayerfx.models;


import com.example.musicplayerfx.enums.Genre;
import com.example.musicplayerfx.enums.Mood;

import java.time.LocalDate;
import java.util.ArrayList;

public class Album {
    private String title;
    private Artist artist;
    private LocalDate releaseDate;
    private ArrayList<Song> songs;
    private Genre genre;
    private Mood mood;

    public Album(String title, Artist artist, LocalDate releaseDate, ArrayList<Song> songs, Genre genre, Mood mood) {
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.songs = songs;
        this.genre = genre;
        this.mood = mood;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    @Override
    public String toString() {
        return String.format("Album  %s  %s  %s  %s  %s  %s}",
                title, artist, releaseDate, songs, genre, mood);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
