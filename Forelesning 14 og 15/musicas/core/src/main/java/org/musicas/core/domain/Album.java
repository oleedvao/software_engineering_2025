package org.musicas.core.domain;

import java.util.ArrayList;

public class Album {

    private int id;
    private String title;
    private ArrayList<Song> songs;

    public Album(String title, ArrayList<Song> songs) {
        this.title = title;
        this.songs = songs;
    }

    public Album(int id, String title, ArrayList<Song> songs) {
        this.id = id;
        this.title = title;
        this.songs = songs;
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

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
