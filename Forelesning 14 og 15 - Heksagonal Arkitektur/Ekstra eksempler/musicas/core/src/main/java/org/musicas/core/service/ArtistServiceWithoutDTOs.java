package org.musicas.core.service;

import org.musicas.core.domain.Artist;
import org.musicas.core.domain.Song;
import org.musicas.core.dto.CreateArtistRequest;
import org.musicas.core.dto.GetArtistSongsWithLengthRequest;
import org.musicas.core.dto.GetArtistSongsWithLengthResult;
import org.musicas.core.dto.SongDTO;
import org.musicas.core.exception.ArtistRepositoryException;
import org.musicas.core.port.ArtistRepositoryPort;

import java.util.ArrayList;

public class ArtistServiceWithoutDTOs {

    private ArtistRepositoryPort artistRepository;

    public ArtistServiceWithoutDTOs(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void createArtist(String name)
    throws ArtistRepositoryException{
        Artist artist = new Artist(name);
        artistRepository.createArtist(artist);
    }

    public ArrayList<SongDTO> getArtistSongsWithLength(int artistId, int lengthInSeconds)
    throws ArtistRepositoryException {

        ArrayList<Song> artistSongs = artistRepository.getArtistSongs(artistId);

        ArrayList<SongDTO> artistSongsWithLength = new ArrayList<>();

        for (Song song : artistSongs) {
            if (song.getLengthInSeconds() >= lengthInSeconds) {
                SongDTO songDTO = new SongDTO(
                        song.getId(),
                        song.getTitle(),
                        song.getLengthInSeconds()
                );
                artistSongsWithLength.add(songDTO);
            }
        }

        return artistSongsWithLength;
    }
}
