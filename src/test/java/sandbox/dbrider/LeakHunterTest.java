package sandbox.dbrider;

import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;

@DBRider
public class LeakHunterTest {

    @Test
    void test() throws Exception {
        createLeak();
    }

    private void createLeak() throws Exception {
        DriverManager.getConnection("jdbc:hsqldb:mem:test", "sa", "");
    }
}
