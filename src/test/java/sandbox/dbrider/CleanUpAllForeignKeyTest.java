package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class CleanUpAllForeignKeyTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value varchar(32))");
        db.executeUpdate("""
        create table first_table (
            id integer primary key,
            value varchar(32)
        )""");
        db.executeUpdate("""
        create table second_table (
            first_id integer primary key,
            value varchar(32),
            foreign key (first_id) references first_table (id)
        )
        """);
    }

    @BeforeEach
    void setUp() throws Exception {
        db.executeUpdate("insert into foo_table values (1, 'foo')");
        db.executeUpdate("insert into first_table values (1, 'first')");
        db.executeUpdate("insert into second_table values (1, 'second')");
    }

    @Test
    @DataSet(value = "sandbox/dbrider/CleanUpAllForeignKeyTest/data-set.yml", cleanBefore = true)
    @ExpectedDataSet("sandbox/dbrider/CleanUpAllForeignKeyTest/expected.yml")
    void test() {}
}
