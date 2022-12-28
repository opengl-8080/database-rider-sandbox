package sandbox.dbrider;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseSupport implements BeforeAllCallback, AfterAllCallback {
    private final String url;
    private Connection connection;

    public DatabaseSupport() {
        this("jdbc:hsqldb:mem:test");
    }

    public DatabaseSupport(String url) {
        this.url = url;
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        connection = DriverManager.getConnection(url, "sa", "");
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

    public void printTable(String table) {
        try (
            final PreparedStatement ps = connection.prepareStatement("select * from " + table);
            final ResultSet rs = ps.executeQuery();
        ) {
            final ResultSetMetaData metaData = rs.getMetaData();

            while (rs.next()) {
                Map<String, Object> record = new HashMap<>();
                for (int i=1; i<=metaData.getColumnCount(); i++) {
                    final String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    if (value instanceof String) {
                        value = "\"" + value + "\"";
                    }
                    record.put(columnName, value);
                }
                System.out.println(record);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
