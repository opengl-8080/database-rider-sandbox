package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class ScriptableDataSetsTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value varchar(32))");
    }

//    @Test
    @DataSet("sandbox/dbrider/ScriptableDataSetsTest/testJavaScript/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/ScriptableDataSetsTest/testJavaScript/expected.yml")
    void testJavaScript() {}

    @Test
    @DataSet("sandbox/dbrider/ScriptableDataSetsTest/testGroovy/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/ScriptableDataSetsTest/testGroovy/expected.yml")
    void testGroovy() {}

    @Test
    @DataSet("sandbox/dbrider/ScriptableDataSetsTest/testWithExpectedDataSet/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/ScriptableDataSetsTest/testWithExpectedDataSet/expected.yml")
    void testWithExpectedDataSet() {}
}
