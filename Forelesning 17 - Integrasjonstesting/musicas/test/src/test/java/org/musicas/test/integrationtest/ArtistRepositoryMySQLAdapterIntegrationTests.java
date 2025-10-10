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

    /* Initialiserer hvilken type test-base som skal benyttes.
    In-memory H2-database er raskest og krever ikke noe eksternt (Anbefales i forhold til kursets scope).
    Testcontainers krever at Docker Desktop er installert på maskinen.
     */
    private final static TestDatabase testDB = new H2TestDatabase();
    //private final static TestDatabase testDB = new TestContainerDatabase();

    private static Connection connection;

    private static ArtistRepositoryMySQLAdapter artistRepository;

    /*
    Starter og setter opp det grunnleggende i test-databasen før noen av testene kjører.
     */
    @BeforeAll
    public static void setUpTestDB() throws Exception{
        // Starter test-databasen
        connection = testDB.startDB();
        testDB.createTables();

        // Tillater å senere kunne rulle tilbake data-endringer.
        connection.setAutoCommit(false);

        artistRepository = new ArtistRepositoryMySQLAdapter(connection);
    }

    /*
    Oppretter dummy data før hver enkelt test, slik at utganspunktet er likt for samtlige av dem.
     */
    @BeforeEach
    public void prepareTest() throws Exception{
        testDB.createDummyData();
    }

    /*
    Rydder opp etter testen ved å rulle tilbake endringene som testen gjør.
    Dette vil i praksis medføre at databasen blir tilbakestilt til de tomme tabellene.
     */
    @AfterEach
    public void cleanUpTest() throws Exception{
        connection.rollback();
    }

    /*
    Stopper databasen når alle testene er ferdig med å kjøre.
     */
    @AfterAll
    public static void tearDownTestDB() throws Exception {
        testDB.stopDB();
    }

    /*
    Tester at en artist kan opprettes på normalt vis.
     */
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

    /*
    Skjekker at sangene knyttet til artisten definert i dummy-data kan hentes ut på normalt vis.
     */
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
        Assertions.assertTrue(artistSongTitles.contains("Weird Fishes"));
        Assertions.assertTrue(artistSongTitles.contains("Faust Arp"));
    }

    /*
    Helpe-metode for testen over.
    Anskaffer en liste med bare sang-titler fra en liste med sanger.
     */
    private ArrayList<String> getTitlesFromSongList(ArrayList<Song> songs) {
        ArrayList<String> songTitles = new ArrayList<>();

        for (Song song : songs) {
            songTitles.add(song.getTitle());
        }

        return songTitles;
    }

}
