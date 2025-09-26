import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ArrayListTests {

    /*
    Denne enhetstesten tester en void-metode. Void-metoder er litt spesielle i den forstand at de ikke returneres en
    verdi eller objekt som vi kan asserte. Det er i stedet vanlig at void-metoder påvirker noe annet enn seg selv
    (f.eks. endrer på et relatert objekt), og vi må i så fall asserte at kjøringen har hatt denne effekten.
    MERK: ArrayList og .add() er ferdigskrevet i Java, så det er ikke egentlig hensiktsmessig at vi enhetstester disse
    og eksemplet er bare ment for å illustrere hvordan vi må enhetsteste når vi selv har skrevet en void-metode.
     */
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
