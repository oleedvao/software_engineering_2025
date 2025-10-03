package org.musicas.core.port;

import org.musicas.core.domain.Artist;
import org.musicas.core.domain.Song;

import java.util.ArrayList;

public interface ArtistRepositoryPort {

    void createArtist(Artist artist);
    ArrayList<Song> getArtistSongs(int artistId);
}
