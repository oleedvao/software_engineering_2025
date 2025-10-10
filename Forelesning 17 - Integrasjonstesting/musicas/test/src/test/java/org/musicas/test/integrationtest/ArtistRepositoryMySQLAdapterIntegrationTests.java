package org.musicas.test.integrationtest;

import org.junit.jupiter.api.*;
import org.musicas.core.domain.Artist;
import org.musicas.core.domain.Song;
import org.musicas.storage.adapter.ArtistRepositoryMySQLAdapter;
import org.musicas.test.testdb.H2TestDatabase;
import org.musicas.test.testdb.TestContainerDatabase;
import org.musicas.test.testdb.TestDatabase;

import java.sql.Connection;
import java.util.ArrayList;

public class ArtistRepositoryMySQLAdapterIntegrationTests {

    //private final static TestDatabase testDB = new H2TestDatabase();
    private final static TestDatabase testDB = new TestContainerDatabase();
    private static Connection connection;

    private static ArtistRepositoryMySQLAdapter artistRepository;

    @BeforeAll
    public static void setUpTestDB() throws Exception{
        connection = testDB.startDB();
        testDB.createTables();

        connection.setAutoCommit(false);

        artistRepository = new ArtistRepositoryMySQLAdapter(connection);
    }

    @BeforeEach
    public void prepareTest() throws Exception{
        testDB.createDummyData();
    }

    @AfterEach
    public void cleanUpTest() throws Exception{
        connection.rollback();
    }

    @AfterAll
    public static void tearDownTestDB() throws Exception {
        testDB.stopDB();
    }

    @Test
    public void createArtist_ArtistIsCreatedSuccessfully() throws Exception{
        // Arrange
        Artist artist = new Artist("Ween");

        // Act
        artistRepository.createArtist(artist);

        // Assert
        Assertions.assertEquals(2, testDB.countRowsInTable("artists"));
        Assertions.assertEquals("Ween", testDB.getArtistName(2));
    }

    @Test
    public void getArtistSongs_ArtistSongsRetrievedSuccessfully() throws Exception {
        // Arrange
        int dummyArtistId = 1;

        // Act
        ArrayList<Song> artistSongs = artistRepository.getArtistSongs(dummyArtistId);

        // Assert
        Assertions.assertEquals(3, artistSongs.size());
        ArrayList<String> artistSongTitles = getTitlesFromSongList(artistSongs);
        Assertions.assertTrue(artistSongTitles.contains("Reckoner"));
    }

    private ArrayList<String> getTitlesFromSongList(ArrayList<Song> songs) {
        ArrayList<String> songTitles = new ArrayList<>();

        for (Song song : songs) {
            songTitles.add(song.getTitle());
        }

        return songTitles;
    }

}
