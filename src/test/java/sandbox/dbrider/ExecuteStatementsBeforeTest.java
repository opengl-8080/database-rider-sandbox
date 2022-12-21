package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class ExecuteStatementsBeforeTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @Test
    @DataSet(
        value = "sandbox/dbrider/ExecuteStatementsBeforeTest/data-set.yml",
        executeStatementsBefore = "create table foo_table (id integer, value varchar(32))"
    )
    @ExpectedDataSet("sandbox/dbrider/ExecuteStatementsBeforeTest/expected.yml")
    void test() {}
}
