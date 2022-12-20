package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class OrderByTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value varchar(32))");
    }

    @Test
    @DataSet("sandbox/dbrider/OrderByTest/data-set.yml")
    @ExpectedDataSet(
        value = "sandbox/dbrider/OrderByTest/expected.yml",
        orderBy = "id"
    )
    void test() {}
}
