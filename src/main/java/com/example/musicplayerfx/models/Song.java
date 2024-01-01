package com.example.musicplayerfx.models;


import com.example.musicplayerfx.enums.FileFormat;
import com.example.musicplayerfx.enums.Genre;
import com.example.musicplayerfx.enums.Mood;

import java.time.LocalDate;

public class Song {
    private int id;
    private String title;
    private String artist;
    private int durationInSeconds;
    private LocalDate releaseDate;
    private String language;
    private String producer;
    private String songwriter;
    private String feature;
    private Genre genre;
    private Mood mood;
    private FileFormat fileFormat;

    public Song(int id, String title, String artist, int durationInSeconds, LocalDate releaseDate, String language,
                String producer, String songwriter, String feature, Genre genre, Mood mood,
                FileFormat fileFormat) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.durationInSeconds = durationInSeconds;
        this.releaseDate = releaseDate;
        this.language = language;
        this.producer = producer;
        this.songwriter = songwriter;
        this.feature = feature;
        this.genre = genre;
        this.mood = mood;
        this.fileFormat = fileFormat;
    }

    public Song(String title, String artist, int durationInSeconds, LocalDate releaseDate, String language,
                String producer, String songwriter, String feature, Genre genre, Mood mood,
                FileFormat fileFormat) {
        this(0, title, artist, durationInSeconds, releaseDate, language,
                producer, songwriter, feature, genre, mood, fileFormat);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSongwriter() {
        return songwriter;
    }

    public void setSongwriter(String songwriter) {
        this.songwriter = songwriter;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
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

    public FileFormat getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(FileFormat fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override
    public String toString() {

        return String.format("%d - %s  %s", id, title, artist);
    }
}
