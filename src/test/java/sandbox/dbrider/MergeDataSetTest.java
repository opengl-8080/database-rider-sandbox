package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
@DataSet(
    cleanBefore = true,
    executeStatementsBefore = "insert into bar_table values (10, 'class level')"
//    value = "sandbox/dbrider/MergeDataSetTest/class-level-data-set.yml",
//    executeStatementsBefore = "insert into bar_table values (10, 'class level')"
)
public class MergeDataSetTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value varchar(32))");
        db.executeUpdate("create table bar_table (id integer, value varchar(32))");
    }

    @Test
    @DataSet(
        value = "sandbox/dbrider/MergeDataSetTest/method-level-data-set.yml",
        executeStatementsBefore = "insert into bar_table values (1, 'method level')"
    )
    void test() {
        System.out.println("=== foo_table ===");
        db.printTable("foo_table");
        System.out.println("=== bar_table ===");
        db.printTable("bar_table");
    }

    @BeforeEach
    void setUp() {
        db.executeUpdate("insert into foo_table values (9, 'initial value')");
    }

    @Test
    @DataSet(
        cleanBefore = false,
        executeStatementsBefore = "insert into bar_table values (1, 'method level')"
    )
    void test2() {
        System.out.println("=== foo_table ===");
        db.printTable("foo_table");
        System.out.println("=== bar_table ===");
        db.printTable("bar_table");
    }
}
