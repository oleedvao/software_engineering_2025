package org.musicas.app;

import org.musicas.core.dto.CreateArtistRequest;
import org.musicas.core.dto.GetArtistSongsWithLengthRequest;
import org.musicas.core.dto.GetArtistSongsWithLengthResult;
import org.musicas.core.dto.SongDTO;
import org.musicas.core.exception.ArtistRepositoryException;
import org.musicas.core.port.ArtistRepositoryPort;
import org.musicas.core.service.ArtistService;
import org.musicas.storage.adapter.ArtistRepositoryMySQLAdapter;
import org.musicas.storage.database.MySQLDatabase;

import java.sql.Connection;
import java.util.ArrayList;

public class BasicApplication {

    private final static String URL = "jdbc:mysql://localhost:3306/musicas"; // Eksisterer ikke. Bare et eksempel.
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";

    public static void main(String[] args) {

        // Konfigurerer database
        MySQLDatabase database = new MySQLDatabase(URL, USERNAME, PASSWORD);
        Connection dbConnection = database.startDB();

        // konfigurerer repository-klasse (for database-spørringer)
        ArtistRepositoryPort artistRepository = new ArtistRepositoryMySQLAdapter(dbConnection);

        // Oppretter ArtistService
        ArtistService artistService = new ArtistService(artistRepository);

        // Oppretter artist i persistent lagring
        try {
            artistService.createArtist(new CreateArtistRequest(
                    "Radiohead"
            ));
        } catch (ArtistRepositoryException e) {
            System.err.println(e.getMessage());
        }


        // Henter artist med id 5 sine sanger over 200 sekunder i lengde
        GetArtistSongsWithLengthRequest request = new GetArtistSongsWithLengthRequest(
                5, 200
        );
        ArrayList<SongDTO> artistSongsWithLength;
        try {
            GetArtistSongsWithLengthResult result = artistService.getArtistSongsWithLength(request);
            artistSongsWithLength = result.getSongDTOs();
        } catch (ArtistRepositoryException e) {
            System.err.println(e.getMessage());
        }

    }
}
