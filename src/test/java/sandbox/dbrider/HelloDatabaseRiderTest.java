package sandbox.dbrider;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ExtendWith(DBUnitExtension.class)
public class HelloDatabaseRiderTest {
    private static final ConnectionHolder connectionHolder =
        () -> DriverManager.getConnection("jdbc:hsqldb:mem:test", "sa", "");

    private static Connection connection;

    @BeforeAll
    static void initDatabase() throws Exception {
        connection = connectionHolder.getConnection();
        executeUpdate("""
        create table test_table (
            id integer,
            value varchar(32)
        )
        """);
    }

    @Test
    @DataSet("sandbox/dbrider/hello.yml")
    @ExpectedDataSet("sandbox/dbrider/hello-expected.yml")
    void hello() throws Exception {
        executeUpdate("""
        update
            test_table
        set
            value = 'WORLD'
        where
            id = 2
        """);
    }

    @AfterAll
    static void closeConnection() throws Exception {
        connection.close();
    }

    private static void executeUpdate(String sql) throws SQLException {
        try (final PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.executeUpdate();
        }
    }
}
