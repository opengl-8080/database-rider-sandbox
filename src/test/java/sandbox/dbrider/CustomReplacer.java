package sandbox.dbrider;

import com.github.database.rider.core.replacers.Replacer;
import org.dbunit.dataset.ReplacementDataSet;

public class CustomReplacer implements Replacer {
    @Override
    public void addReplacements(ReplacementDataSet dataSet) {
        dataSet.addReplacementSubstring("[replace-me]", "World");
    }
}
