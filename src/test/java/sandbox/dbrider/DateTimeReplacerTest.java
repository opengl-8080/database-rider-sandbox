package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class DateTimeReplacerTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, date_value timestamp)");
    }

    @Test
    @DataSet("sandbox/dbrider/DateTimeReplacerTest/data-set.yml")
    void testDateTimeReplacer() {
        db.printTable("foo_table");
    }
}
