package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.CompareOperation;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class CompareContainsTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table test_table (id integer, value varchar(32))");
    }

    @Test
    @DataSet("sandbox/dbrider/CompareContainsTest/data-set.yml")
    @ExpectedDataSet(
        value = "sandbox/dbrider/CompareContainsTest/expected.yml",
        compareOperation = CompareOperation.CONTAINS
    )
    void test() {}
}
