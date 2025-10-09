package org.musicas.core.dto;

public class GetArtistSongsWithLengthRequest {
    private final int artistId;
    private final int lengthInSeconds;

    public GetArtistSongsWithLengthRequest(int artistId, int lengthInSeconds) {
        this.artistId = artistId;
        this.lengthInSeconds = lengthInSeconds;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getLengthInSeconds() {
        return lengthInSeconds;
    }
}
