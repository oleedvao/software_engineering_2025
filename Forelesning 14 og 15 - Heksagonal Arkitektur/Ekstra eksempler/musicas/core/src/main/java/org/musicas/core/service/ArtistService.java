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

public class ArtistService {

    private ArtistRepositoryPort artistRepository;

    public ArtistService(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void createArtist(CreateArtistRequest request)
    throws ArtistRepositoryException {
        Artist artist = new Artist(request.getName());
        artistRepository.createArtist(artist);
    }

    public GetArtistSongsWithLengthResult getArtistSongsWithLength(GetArtistSongsWithLengthRequest request)
    throws ArtistRepositoryException {

        ArrayList<Song> artistSongs = artistRepository.getArtistSongs(request.getArtistId());

        ArrayList<SongDTO> artistSongsWithLength = new ArrayList<>();

        for (Song song : artistSongs) {
            if (song.getLengthInSeconds() >= request.getLengthInSeconds()) {
                SongDTO songDTO = new SongDTO(
                        song.getId(),
                        song.getTitle(),
                        song.getLengthInSeconds()
                );
                artistSongsWithLength.add(songDTO);
            }
        }

        GetArtistSongsWithLengthResult result = new GetArtistSongsWithLengthResult(
                request.getArtistId(),
                request.getLengthInSeconds(),
                artistSongsWithLength
        );
        return result;
    }

}
