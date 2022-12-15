package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.Instant;

@DBRider
public class UnixTimestampReplacerTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value bigint)");
    }

    @Test
    @DataSet("sandbox/dbrider/UnixTimestampReplacerTest/data-set.yml")
    void test() {
        System.out.println("epoch seconds = " + Instant.now().getEpochSecond());
        db.printTable("foo_table");
    }
}
