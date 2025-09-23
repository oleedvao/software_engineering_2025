import org.junit.jupiter.api.Assertions;
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
    public void controlAge_ageWithinAcceptableRangeIsHandledCorrectly() {
        InformationChecker informationChecker = new InformationChecker();

        int result = informationChecker.controlAge(30);
        int lowerBoundResult = informationChecker.controlAge(0);
        int upperBoundResult = informationChecker.controlAge(120);

        Assertions.assertEquals(30, result);
        Assertions.assertEquals(0, lowerBoundResult);
        Assertions.assertEquals(120, upperBoundResult);
    }

}
