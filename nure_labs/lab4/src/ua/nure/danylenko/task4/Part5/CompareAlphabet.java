package ua.nure.danylenko.task4.Part5;

import java.util.Comparator;

public class CompareAlphabet implements Comparator<MyWord> {
    @Override
    public int compare(MyWord a, MyWord b) {
        int aLen = a.name.length();
        int bLen = b.name.length();
        int minLen = (aLen > bLen)? bLen : aLen;

        for (int i = 0; i < minLen; i++) {
            if (a.name.charAt(i) > b.name.charAt(i)) {
                return -1;
            } else if (a.name.charAt(i) < b.name.charAt(i)){
                return  1;
            }
            return -aLen + bLen;
        }

        return 0;
    }
}
