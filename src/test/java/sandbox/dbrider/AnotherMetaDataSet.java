package sandbox.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@DataSet("sandbox/dbrider/MetaDataSetTest/another-meta-data-set.yml")
public @interface AnotherMetaDataSet {
}
