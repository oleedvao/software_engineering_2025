package org.musicas.core.domain;

public class Song {

    private int id;
    private String title;
    private int lengthInSeconds;
    private int artistId;
    private int albumId;

    public Song(String title, int lengthInSeconds, int artistId, int albumId) {
        this.title = title;
        this.lengthInSeconds = lengthInSeconds;
        this.artistId = artistId;
        this.albumId = albumId;
    }

    public Song(int id, String title, int lengthInSeconds, int artistId, int albumId) {
        this.id = id;
        this.title = title;
        this.lengthInSeconds = lengthInSeconds;
        this.artistId = artistId;
        this.albumId = albumId;
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

    public int getLengthInSeconds() {
        return lengthInSeconds;
    }

    public void setLengthInSeconds(int lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
