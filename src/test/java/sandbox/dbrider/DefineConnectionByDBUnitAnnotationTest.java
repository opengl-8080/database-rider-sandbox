package sandbox.dbrider;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
@DBUnit(url = "jdbc:hsqldb:mem:test", user = "sa", password = "")
public class DefineConnectionByDBUnitAnnotationTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("""
        create table test_table (
            id integer,
            value varchar(32)
        )
        """);
    }

    @Test
    @DataSet("sandbox/dbrider/hello.yml")
    @ExpectedDataSet("sandbox/dbrider/hello-expected.yml")
    void test() {
        db.executeUpdate("update test_table set value = 'WORLD' where id = 2");
    }
}
