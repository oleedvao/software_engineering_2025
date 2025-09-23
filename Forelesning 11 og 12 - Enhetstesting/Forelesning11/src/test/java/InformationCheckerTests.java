import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InformationCheckerTests {

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
