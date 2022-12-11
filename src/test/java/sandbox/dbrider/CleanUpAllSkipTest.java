package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class CleanUpAllSkipTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table hoge_table (id integer, value varchar(32))");
        db.executeUpdate("create table fuga_table (id integer, value varchar(32))");
        db.executeUpdate("create table piyo_table (id integer, value varchar(32))");
    }

    @BeforeEach
    void setUp() {
        db.executeUpdate("insert into hoge_table values (1, 'hoge')");
        db.executeUpdate("insert into fuga_table values (1, 'fuga')");
        db.executeUpdate("insert into piyo_table values (1, 'piyo')");
    }

    @Test
    @DataSet(
        value = "sandbox/dbrider/CleanUpAllSkipTest/data-set.yml",
        cleanBefore = true,
        skipCleaningFor = "PIYO_TABLE" // ★
    )
    @ExpectedDataSet("sandbox/dbrider/CleanUpAllSkipTest/expected.yml")
    void test() {}
}
