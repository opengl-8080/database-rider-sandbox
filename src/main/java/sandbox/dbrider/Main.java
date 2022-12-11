package sandbox.dbrider;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        set.add("PUBLIC.FIRST_TABLE");
        set.add("PUBLIC.SECOND_TABLE");

        for (String value : set) {
            System.out.println(value);
        }
    }
}
