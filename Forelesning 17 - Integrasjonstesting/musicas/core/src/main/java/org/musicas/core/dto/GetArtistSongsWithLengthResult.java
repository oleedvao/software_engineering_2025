package org.musicas.core.dto;

import java.util.ArrayList;

public class GetArtistSongsWithLengthResult {

    private final int artistId;
    private final int filteredLengthInSeconds;
    private final ArrayList<SongDTO> songDTOs;

    public GetArtistSongsWithLengthResult(int artistId, int filteredLengthInSeconds, ArrayList<SongDTO> songDTOs) {
        this.artistId = artistId;
        this.filteredLengthInSeconds = filteredLengthInSeconds;
        this.songDTOs = songDTOs;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getFilteredLengthInSeconds() {
        return filteredLengthInSeconds;
    }

    public ArrayList<SongDTO> getSongDTOs() {
        return songDTOs;
    }
}
