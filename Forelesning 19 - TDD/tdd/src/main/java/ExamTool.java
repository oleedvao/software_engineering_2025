import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ExamTool {
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
