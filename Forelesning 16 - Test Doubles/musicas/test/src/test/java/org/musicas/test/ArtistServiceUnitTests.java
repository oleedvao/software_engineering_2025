package org.musicas.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.musicas.core.domain.Artist;
import org.musicas.core.domain.Song;
import org.musicas.core.dto.CreateArtistRequest;
import org.musicas.core.dto.GetArtistSongsWithLengthRequest;
import org.musicas.core.dto.GetArtistSongsWithLengthResult;
import org.musicas.core.port.ArtistRepositoryPort;
import org.musicas.core.service.ArtistService;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceUnitTests {

    // Mocker ArtistRepositoryPort slik at vi kan definere hvordan denne skal fungere i testene våre
    @Mock
    ArtistRepositoryPort mockArtistRepository;

    /*
    Dette er et eksempel hvor det er fornuftig å mocke for å forsikre oss om at vi kan teste filtreringslogikken i
    getArtistSongsWithLength(). Spesifikt fordi filtreringslogikken er avhengig av hva som returneres av
    det "ArtistRepositoriet" (implementasjon av ArtistRepositoryPort) som er i bruk. Vi lager altså en mock av
    ArtistReositoryPort og forfalsker resultatet til noe forutsigbart når enheten kaller .getArtistSongs() - Her en
    liste med sanger som har varierte lengder, slik at vi får tested filtreringslogikken.
     */
    @Test
    public void getArtistSongsWithLength_ArtistSongsAreFilteredSuccessully()
    throws Exception {
        // Arrange
        //ArtistRepositoryPort mockArtistRepository = Mockito.mock(ArtistRepositoryPort.class);
        ArrayList<Song> stubbedSongs = new ArrayList<>();
        stubbedSongs.add(new Song("Song 1", 300, 5, 10));
        stubbedSongs.add(new Song("Song 2", 150, 5 , 10));
        stubbedSongs.add(new Song("Song 3", 200, 5, 10));
        Mockito.when(mockArtistRepository.getArtistSongs(5)).thenReturn(stubbedSongs);

        ArtistService artistService = new ArtistService(mockArtistRepository);

        int artistId = 5;
        int lengthInSeconds = 200;
        GetArtistSongsWithLengthRequest request = new GetArtistSongsWithLengthRequest(
                artistId, lengthInSeconds
        );

        // Act
        GetArtistSongsWithLengthResult result = artistService.getArtistSongsWithLength(request);

        // Assert
        Assertions.assertEquals(5, result.getArtistId());
        Assertions.assertEquals(200, result.getFilteredLengthInSeconds());
        Assertions.assertEquals(2, result.getSongDTOs().size());
        Assertions.assertEquals("Song 1", result.getSongDTOs().get(0).getTitle());
        Assertions.assertEquals("Song 3", result.getSongDTOs().get(1).getTitle());
    }

    /* NB!
    Denne testen er fullstendig UNØDVENDIG ettersom den ikke gir noen mer innsikt enn ved å lese ArtistService sin
    createArtist() slik den er definert. Med andre ord, har testen en meget lav "test-verdi".
    Vi kan altså helt fint fjerne/utelate denne testen, men jeg lar den stå bare for å illustrere hvordan man IKKE
    bør bruke mocking.
     */
    @Test
    public void createArtist_ArtistRepositoryMethodIsCalled()
    throws Exception{
        // Arrange
        ArtistService artistService = new ArtistService(mockArtistRepository);

        CreateArtistRequest request = new CreateArtistRequest("Radiohead");

        // Act
        artistService.createArtist(request);

        // Assert
        Mockito.verify(mockArtistRepository, Mockito.atMostOnce()).createArtist(Mockito.any(Artist.class));
    }

}
