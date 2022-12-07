package sandbox.dbrider;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSupport implements BeforeAllCallback, AfterAllCallback {
    private Connection connection;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        connection = DriverManager.getConnection("jdbc:hsqldb:mem:test", "sa", "");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        connection.close();
    }

    public void executeUpdate(String sql) {
        try (final PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
