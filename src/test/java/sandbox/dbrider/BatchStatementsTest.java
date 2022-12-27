package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@DBRider
public class BatchStatementsTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value varchar(32))");
    }

    @Test
    @DataSet("sandbox/dbrider/BatchStatementsTest/data-set.yml")
    void test() throws Exception {
        try (final PreparedStatement ps = db.getConnection().prepareStatement("select count(*) from foo_table");
             final ResultSet rs = ps.executeQuery()) {
            rs.next();
            final long count = rs.getLong(1);
            System.out.println("count = " + count);
        }
    }
}
