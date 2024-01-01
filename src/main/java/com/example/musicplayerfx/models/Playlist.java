package com.example.musicplayerfx.models;

import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Song> songs;

    public Playlist(String name, ArrayList<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return String.format("Playlist  %s  %s", name, songs);
    }

}
