package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class ExecuteScriptsBeforeTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @Test
    @DataSet(
        value = "sandbox/dbrider/ExecuteScriptsBeforeTest/data-set.yml",
        executeScriptsBefore = "sandbox/dbrider/ExecuteScriptsBeforeTest/create-table.sql"
    )
    @ExpectedDataSet("sandbox/dbrider/ExecuteScriptsBeforeTest/expected.yml")
    void test() {}
}
