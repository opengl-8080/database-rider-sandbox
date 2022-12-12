package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class DataSetTypeTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value varchar(32))");
        db.executeUpdate("create table bar_table (id integer, value varchar(32))");
    }

    @Test
    @DataSet("sandbox/dbrider/DataSetTypeTest/testYaml/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/DataSetTypeTest/testYaml/expected.yaml")
    void testYaml() {}

    @Test
    @DataSet("sandbox/dbrider/DataSetTypeTest/testJson/data-set.json")
    @ExpectedDataSet("sandbox/dbrider/DataSetTypeTest/testJson/expected.json")
    void testJson() {}

    @Test
    @DataSet("sandbox/dbrider/DataSetTypeTest/testXml/data-set.xml")
    @ExpectedDataSet("sandbox/dbrider/DataSetTypeTest/testXml/expected.xml")
    void testXml() {}

//    @Test
//    @DataSet("sandbox/dbrider/DataSetTypeTest/testXls/data-set.xls")
//    @ExpectedDataSet("sandbox/dbrider/DataSetTypeTest/testXls/expected.xls")
//    void testXls() {}

    @Test
    @DataSet("sandbox/dbrider/DataSetTypeTest/testCsv/data-set/foo_table.csv")
    @ExpectedDataSet("sandbox/dbrider/DataSetTypeTest/testCsv/expected/foo_table.csv")
    void testCsv() {}
}
