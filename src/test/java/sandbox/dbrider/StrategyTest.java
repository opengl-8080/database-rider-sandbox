package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class StrategyTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value varchar(32))");
    }

    @BeforeEach
    void setUp() {
        db.executeUpdate("insert into foo_table values (9, 'default value')");
    }

    @Test
    @DataSet(
        value = "sandbox/dbrider/StrategyTest/data-set.yml",
        strategy = SeedStrategy.INSERT
    )
    @ExpectedDataSet("sandbox/dbrider/StrategyTest/expected.yml")
    void test() {}
}
