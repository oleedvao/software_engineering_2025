import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ArrayListTests {

    @Test
    public void addNumberToArrayList() {
        // Arrange
        ArrayList<String> list = new ArrayList<>();

        // Act
        list.add("Item 1");
        list.add("Item 2");

        // Assert
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals("Item 1", list.getFirst());
        Assertions.assertEquals("Item 2", list.get(1));

    }

}
