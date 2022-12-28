package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class TableTypeTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value varchar(32))");
        db.executeUpdate("create view foo_view as select * from foo_table");
    }

    @Test
    @DataSet("sandbox/dbrider/TableTypeTest/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/TableTypeTest/expected.yml")
    void test() {}
}
