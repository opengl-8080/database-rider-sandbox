package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.dataset.DataSetExecutorImpl;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.sql.DriverManager;

@DBRider
public class MultiDataSourceTest {
    @RegisterExtension
    static DatabaseSupport db1 = new DatabaseSupport("jdbc:hsqldb:mem:test1");
    @RegisterExtension
    static DatabaseSupport db2 = new DatabaseSupport("jdbc:hsqldb:mem:test2");

    @BeforeAll
    static void beforeAll() {
        DataSetExecutorImpl.instance("db1",
                () -> DriverManager.getConnection("jdbc:hsqldb:mem:test1", "sa", ""));
        DataSetExecutorImpl.instance("db2",
                () -> DriverManager.getConnection("jdbc:hsqldb:mem:test2", "sa", ""));

        db1.executeUpdate("create table foo_table (id integer, value varchar(32))");
        db2.executeUpdate("create table bar_table (id integer, value varchar(32))");
    }

    @Test
    @DataSet(
        value = "sandbox/dbrider/MultiDataSourceTest/db1-data-set.yml",
        executorId = "db1"
    )
    @ExpectedDataSet("sandbox/dbrider/MultiDataSourceTest/db1-expected.yml")
    void test1() {}

    @Test
    @DataSet(
        value = "sandbox/dbrider/MultiDataSourceTest/db2-data-set.yml",
        executorId = "db2"
    )
    @ExpectedDataSet("sandbox/dbrider/MultiDataSourceTest/db2-expected.yml")
    void test2() {}
}
