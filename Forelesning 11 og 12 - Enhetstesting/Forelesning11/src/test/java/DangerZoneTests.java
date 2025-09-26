import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DangerZoneTests {

    @Test
    public void killTheProgram_ExceptionIsThrownSuccessfylly() {
        DangerZone dangerZone = new DangerZone();

        Assertions.assertThrows(RuntimeException.class, dangerZone::killTheProgram);
    }
}
