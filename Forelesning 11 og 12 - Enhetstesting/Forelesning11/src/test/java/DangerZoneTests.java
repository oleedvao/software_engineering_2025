import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DangerZoneTests {

    /*
    Hvis vi jobber med metoder som under visse omstendigheter er skrevet for å kaste Exceptions, bør vi også teste at
    dette blir gjort. Dette er et eksempel hvor et metodekall medfører en RuntimeException, så vi må asserte at et
    objekt av RunTimeException blir kastet.
     */
    @Test
    public void killTheProgram_ExceptionIsThrownSuccessfylly() {
        // Arrange
        DangerZone dangerZone = new DangerZone();

        // Act and Assert
        /*
        Første parameter representerer typen exception (klassen), andre parameter er en lambda-funksjon for å definere
        hva assertThrows skal kjøre for å kontrollere at exception blir kastet.
        */
        RuntimeException e = Assertions.assertThrows(RuntimeException.class, dangerZone::killTheProgram);
        // Vi kan også asserte at Exception-meldingen er som definert at den skal være.
        Assertions.assertEquals("The program has been killed!", e.getMessage());
    }
}
