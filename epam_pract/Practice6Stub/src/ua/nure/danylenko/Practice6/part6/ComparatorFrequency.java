package ua.nure.danylenko.Practice6.part6;

import java.util.Comparator;
import java.util.Map;

public class ComparatorFrequency implements Comparator<String> {
    Map<String, Integer> base;

    public ComparatorFrequency(Map<String, Integer> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        if (base.get(a) <= base.get(b)) {
            return 1;
        } else {
            return -1;
        }
    }
}
