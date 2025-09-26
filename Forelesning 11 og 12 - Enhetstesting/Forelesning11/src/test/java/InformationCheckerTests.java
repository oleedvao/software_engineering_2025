import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
Samler testene knyttet til klassen InformationChecker.
 */
public class InformationCheckerTests {

    /*
    Dette er enhetstest knyttet til enheten controlAge() som tester at input-verdier som er innenfor den aksepterte
    rekkevidden (0-120) håndteres riktig - Den returnerte alderen er samme som definert i input.
    Merk at vi har et utvalg av av flere input-verdier som representerer rekkevidden - Laveste aksepterte verdi,
    høyeste aksepterte verdi og en verdi "midt i" den aksepterte rekkevidden. Ideen er å gjøre et utvalg som godt nok
    representerer input-gruppen "aksepterte/normale verdier". Alternativet hadde vært å teste alle individuelle verdier
    i rekkevidden, men dette er lite effektivt og blir fort veldig tungt hvis man har enheter med flere parametere.
     */
    @Test
    @DisplayName("controlAge(): Age withing acceptable range is handled correctly")
    public void controlAge_ageWithinAcceptableRangeIsHandledCorrectly() {
        // Arrange
        InformationChecker informationChecker = new InformationChecker();

        // Act
        int result = informationChecker.controlAge(30);
        int lowerBoundResult = informationChecker.controlAge(0);
        int upperBoundResult = informationChecker.controlAge(120);

        // Assert
        Assertions.assertEquals(30, result);
        Assertions.assertEquals(0, lowerBoundResult);
        Assertions.assertEquals(120, upperBoundResult);
    }

    /*
    Enhetstest for å kontrollere at alder-verdier lavere enn den aksepterte rekkevidden også blir håndert riktig
    (konvertert til laveste aksepterte verdi).
     */
    @Test
    @DisplayName("controlAge(): Age lower than acceptable range is handled correctly")
    public void controlAge_ageLowerThanAcceptableRangeIsHandledCorrectly() {
        // Arrange
        InformationChecker informationChecker = new InformationChecker();

        // Act
        int higherBoundResult = informationChecker.controlAge(-1);
        int result = informationChecker.controlAge(-121);
        int lowerBoundResult = informationChecker.controlAge(Integer.MIN_VALUE);

        // Assert
        Assertions.assertEquals(0, higherBoundResult);
        Assertions.assertEquals(0, result);
        Assertions.assertEquals(0, lowerBoundResult);
    }

    /*
    Enhetstest for å kontrollere at alder-verdier høyere enn den aksepterte rekkevidden blir håndert riktig
    (konverteres til høyeste aksepterte verdi).
     */
    @Test
    @DisplayName("controlAge(): Age higher than acceptable range is handled correctly")
    public void controlAge_ageHigherThanAcceptableRangeIsHandledCorrectly() {
        // Arrange
        InformationChecker informationChecker = new InformationChecker();

        // Act
        int lowerBoundResult = informationChecker.controlAge(121);
        int result = informationChecker.controlAge(5000);
        int higherBoundResult = informationChecker.controlAge(Integer.MAX_VALUE);

        // Assert
        Assertions.assertEquals(120, lowerBoundResult);
        Assertions.assertEquals(120, result);
        Assertions.assertEquals(120, higherBoundResult);
    }

}
