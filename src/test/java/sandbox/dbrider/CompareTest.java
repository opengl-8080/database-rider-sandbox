package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DBRider
public class CompareTest {
    @RegisterExtension
    static DatabaseSupport db = new DatabaseSupport();

    @BeforeAll
    static void beforeAll() {
        db.executeUpdate("create table foo_table (id integer, value varchar(32))");
        db.executeUpdate("create table bar_table (id integer, value varchar(32))");
    }

    @Test
    @DataSet("sandbox/dbrider/CompareTest/期待値の方がテーブル数が少ない/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/CompareTest/期待値の方がテーブル数が少ない/expected.yml")
    void 期待値の方がテーブル数が少ない() {}

    @Test
    @DataSet("sandbox/dbrider/CompareTest/期待値の方がテーブル数が多い/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/CompareTest/期待値の方がテーブル数が多い/expected.yml")
    void 期待値の方がテーブル数が多い() {}

    @Test
    @DataSet("sandbox/dbrider/CompareTest/期待値の方がレコード数が少ない/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/CompareTest/期待値の方がレコード数が少ない/expected.yml")
    void 期待値の方がレコード数が少ない() {}

    @Test
    @DataSet("sandbox/dbrider/CompareTest/期待値の方がレコード数が多い/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/CompareTest/期待値の方がレコード数が多い/expected.yml")
    void 期待値の方がレコード数が多い() {}

    @Test
    @DataSet("sandbox/dbrider/CompareTest/期待値の方がカラム数が少ない/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/CompareTest/期待値の方がカラム数が少ない/expected.yml")
    void 期待値の方がカラム数が少ない() {}

    @Test
    @DataSet("sandbox/dbrider/CompareTest/期待値の方がカラム数が多い/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/CompareTest/期待値の方がカラム数が多い/expected.yml")
    void 期待値の方がカラム数が多い() {}

    @Test
    @DataSet("sandbox/dbrider/CompareTest/レコードの順序が異なる/data-set.yml")
    @ExpectedDataSet("sandbox/dbrider/CompareTest/レコードの順序が異なる/expected.yml")
    void レコードの順序が異なる() {}
}
