package com.example.musicplayerfx.models;

import java.util.ArrayList;

public class PremiumUser extends User {
    private ArrayList<Playlist> playlists;

    public PremiumUser(String username, String password, ArrayList<Playlist> playlists) {
        super(username, password);
        this.playlists = playlists;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
