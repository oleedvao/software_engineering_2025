package org.musicas.core.dto;

public class SongDTO {

    private final int id;
    private final String title;
    private final int lengthInSeconds;

    public SongDTO(int id, String title, int lengthInSeconds) {
        this.id = id;
        this.title = title;
        this.lengthInSeconds = lengthInSeconds;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getLengthInSeconds() {
        return lengthInSeconds;
    }
}
