package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class CleanUpTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value varchar(32))");
        db.executeUpdate("create table bar_table (id integer, value varchar(32))");
    }

    @BeforeEach
    void setUp() {
        db.executeUpdate("insert into foo_table values (1, 'hello')");
        db.executeUpdate("insert into foo_table values (2, 'world')");
        db.executeUpdate("insert into bar_table values (1, 'bar')");
    }

    @Test
    @DataSet("sandbox/dbrider/CleanUpTest/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/CleanUpTest/expected.yml")
    void test() {}
}
