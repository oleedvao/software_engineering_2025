package org.musicas.test.integrationtest;

import org.junit.jupiter.api.*;
import org.musicas.core.dto.CreateArtistRequest;
import org.musicas.core.dto.GetArtistSongsWithLengthRequest;
import org.musicas.core.dto.GetArtistSongsWithLengthResult;
import org.musicas.core.dto.SongDTO;
import org.musicas.core.service.ArtistService;
import org.musicas.storage.adapter.ArtistRepositoryMySQLAdapter;
import org.musicas.test.testdb.H2TestDatabase;
import org.musicas.test.testdb.TestDatabase;

import java.sql.Connection;
import java.util.ArrayList;

public class ArtistServiceIntegrationTests {

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
    Tester at createArtist() i ArtistService medfører at artisten blir opprettet i test-databasen.
     */
    @Test
    @DisplayName("createArtist(): Artist is created successfully")
    public void createArtist_ArtistIsCreatedSuccessfully() throws Exception {
        // Arrange
        ArtistService artistService = new ArtistService(artistRepository);

        CreateArtistRequest request = new CreateArtistRequest("Ween");

        // Act
        artistService.createArtist(request);

        // Assert
        Assertions.assertEquals(2, testDB.countRowsInTable("artists"));
        Assertions.assertEquals("Ween", testDB.getArtistName(2));
    }

    /*
    Tester at dummy-artistens sanger både hentes ut og filtreres riktig.
     */
    @Test
    @DisplayName("getArtistSongsWithLength(): Artist songs with minimum length are retrived sucessfully")
    public void getArtistSongsWithLength_ArtistSongsWithMinimumLengthRetrievedSuccessfully()
    throws Exception {
        // Arrange
        ArtistService artistService = new ArtistService(artistRepository);

        GetArtistSongsWithLengthRequest request = new GetArtistSongsWithLengthRequest(
                1, 200
        );

        // Act
        GetArtistSongsWithLengthResult result = artistService.getArtistSongsWithLength(request);

        // Assert
        Assertions.assertEquals(2, result.getSongDTOs().size()); // Én sang er filtrert bort

        ArrayList<String> songTitles = getSongTitlesFromResult(result);
        Assertions.assertTrue(songTitles.contains("Reckoner"));
        Assertions.assertTrue(songTitles.contains("Weird Fishes"));

        Assertions.assertFalse(songTitles.contains("Faust Arp")); // Sjekker at denne er blitt filtrert bort
    }

    /*
    Hjelpe-metode for testen over.
     */
    private ArrayList<String> getSongTitlesFromResult(GetArtistSongsWithLengthResult result) {
        ArrayList<String> songTitles = new ArrayList<>();

        for (SongDTO song : result.getSongDTOs()) {
            songTitles.add(song.getTitle());
        }

        return songTitles;
    }

}
