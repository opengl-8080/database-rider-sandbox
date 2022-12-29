package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DBRider
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Main.class)
public class SpringBootTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("create table if not exists foo_table (id integer, value varchar(32))");
    }

    @Test
    @DataSet("sandbox/dbrider/SpringBootTest/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/SpringBootTest/expected.yml")
    void test() {}
}
