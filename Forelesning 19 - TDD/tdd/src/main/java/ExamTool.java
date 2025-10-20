import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ExamTool {

    /*
    Denne enheten er skrevet eksklusivt ut ifra iterativt oppdaterte Assersions gjennom TDD.
    Merk at løsningene under starter med veldig enkle sjekker og statisk returnerte verdier. Dette vil fungere så lenge
    vi bare tester for én liste med karakterer, men vil ikke fungerer for flere unike lister.
    Dermed når testene utvides til å undersøke resultater for 2 forskjellige lister, blir det et behov for en mer
    generell løsning. Til slutt ser vi at denne løsningen kan refaktoreres til én enkel metode.
     */
    public static int countGrade(String grade, ArrayList<String> gradeList) {
        /*
        return switch (grade) {
            case "A" -> 3;
            case "B" -> 2;
            case "E", "F" -> 0;
            default -> 1;
        };
         */
        /*
        int count = 0;
        for (String gradeInList : gradeList) {
            if (gradeInList.equals(grade)) {
                count++;
            }
        }
        return count;
        */
        return Collections.frequency(gradeList, grade);
    }
}
