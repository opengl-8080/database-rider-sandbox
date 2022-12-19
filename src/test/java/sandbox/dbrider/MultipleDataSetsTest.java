package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class MultipleDataSetsTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table hoge_table (id integer, value varchar(32))");
        db.executeUpdate("create table fuga_table (id integer, value varchar(32))");
        db.executeUpdate("create table piyo_table (id integer, value varchar(32))");

        db.executeUpdate("insert into hoge_table values (9, 'default value')");
        db.executeUpdate("insert into fuga_table values (9, 'default value')");
        db.executeUpdate("insert into piyo_table values (9, 'default value')");
    }

    @Test
    @DataSet(value = {
        "sandbox/dbrider/MultipleDataSetsTest/data-set1.yml",
        "sandbox/dbrider/MultipleDataSetsTest/data-set2.yml"
    })
    @ExpectedDataSet("sandbox/dbrider/MultipleDataSetsTest/expected.yml")
    void test() {}
}
