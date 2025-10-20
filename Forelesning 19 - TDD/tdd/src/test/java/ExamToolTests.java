import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class ExamToolTests {

    /*
    Denne testen er skrevet med TDD. Vi starter altså dermed med en veldig enkel test og
    oppretter det som er nødvendig for å kompilere og kjører testen. Denne vil typisk feile (Red).
    Deretter, hvis testen feiler, oppdaterer vi enheten under test på den enkleste måten slik at den passerer (Green).
    Så ser vi på løsningen og vurderer å refaktorere enheten (Refactor).
    Vi følger denne prosessen helt til testen er komplett og vi har en enhetet som oppfyller alle kravene.
     */
    @Test
    public void countGrade_GradeIsCountedCorrectly() {
        // Arrange
        ArrayList<String> gradeList = new ArrayList<>();
        Collections.addAll(gradeList, "A", "B", "B", "A", "C", "D", "A");
        ArrayList<String> gradeList2 = new ArrayList<>();
        Collections.addAll(gradeList2, "B", "D", "D", "A", "C", "E", "F");

        // Act
        int countA = ExamTool.countGrade("A", gradeList);
        int countB = ExamTool.countGrade("B", gradeList);
        int countC = ExamTool.countGrade("C", gradeList);
        int countD = ExamTool.countGrade("D", gradeList);
        int countE = ExamTool.countGrade("E", gradeList);
        int countF = ExamTool.countGrade("F", gradeList);

        int countA2 = ExamTool.countGrade("A", gradeList2);
        int countB2 = ExamTool.countGrade("B", gradeList2);
        int countC2 = ExamTool.countGrade("C", gradeList2);
        int countD2 = ExamTool.countGrade("D", gradeList2);
        int countE2 = ExamTool.countGrade("E", gradeList2);
        int countF2 = ExamTool.countGrade("F", gradeList2);

        // Assert
        Assertions.assertEquals(3, countA);
        Assertions.assertEquals(2, countB);
        Assertions.assertEquals(1, countC);
        Assertions.assertEquals(1, countD);
        Assertions.assertEquals(0, countE);
        Assertions.assertEquals(0, countF);

        Assertions.assertEquals(1, countA2);
        Assertions.assertEquals(1, countB2);
        Assertions.assertEquals(1, countC2);
        Assertions.assertEquals(2, countD2);
        Assertions.assertEquals(1, countE2);
        Assertions.assertEquals(1, countF2);
    }

}
