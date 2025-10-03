package org.musicas.core.port;

import org.musicas.core.domain.Artist;
import org.musicas.core.domain.Song;
import org.musicas.core.exception.ArtistRepositoryException;

import java.util.ArrayList;

public interface ArtistRepositoryPort {

    void createArtist(Artist artist) throws ArtistRepositoryException;
    ArrayList<Song> getArtistSongs(int artistId) throws ArtistRepositoryException;
}
