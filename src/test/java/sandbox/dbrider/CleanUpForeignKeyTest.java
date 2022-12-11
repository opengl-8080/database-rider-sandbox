package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class CleanUpForeignKeyTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("""
        create table parent_table (
            id integer primary key,
            value varchar(32)
        )""");
        db.executeUpdate("""
        create table child_table (
            parent_id integer primary key,
            value varchar(32),
            foreign key (parent_id) references parent_table (id)
        )""");
        db.executeUpdate("""
        create table grand_child (
            child_id integer primary key,
            value varchar(32),
            foreign key (child_id) references child_table (parent_id)
        )""");
    }

    @BeforeEach
    void setUp() {
        db.executeUpdate("insert into parent_table values (1, 'parent')");
        db.executeUpdate("insert into child_table values (1, 'child')");
        db.executeUpdate("insert into grand_child_table values (1, 'grand_child')");
    }

    @Test
    @DataSet(value = "sandbox/dbrider/CleanUpForeignKeyTest/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/CleanUpForeignKeyTest/expected.yml")
    void test() {}
}
