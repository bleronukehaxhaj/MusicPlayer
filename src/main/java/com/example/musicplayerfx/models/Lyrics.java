package com.example.musicplayerfx.models;

public class Lyrics {
    private int id;
    private int songId;
    private String content;

    public Lyrics(int id, int songId, String content) {
        this.id = id;
        this.songId = songId;
        this.content = content;
    }

    public Lyrics(int songId, String content) {
       this(0,songId,content);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Lyrics{" +
                "id=" + id +
                ", songId=" + songId +
                ", content='" + content + '\'' +
                '}';
    }
}
