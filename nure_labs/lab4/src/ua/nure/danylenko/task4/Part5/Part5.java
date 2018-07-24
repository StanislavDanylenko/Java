package ua.nure.danylenko.task4.Part5;

import ua.nure.danylenko.task4.Util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    //private static final String REGEX_FREQUENCY = "(\\b)([a-zA-Z]+)(\\W)";
    private static final String REGEX_FREQUENCY = "(?mU)(([^a-zA-ZА-Яа-яёЁЇїІіЄєҐґ]+)|(^))([a-zA-ZА-Яа-яёЁЇїІіЄєҐґ]+)" +
            "(?=([^a-zA-ZА-Яа-яёЁЇїІіЄєҐґ])|($))";
    public static void frequency(String input) {
        Map<String, Integer> hMap = new LinkedHashMap<>();
        ComparatorFrequency comp = new ComparatorFrequency(hMap);
        Map<String, Integer> map = new TreeMap<>(comp);

        Pattern p = Pattern.compile(REGEX_FREQUENCY);
        Matcher m = p.matcher(input);

        while (m.find()) {
            String str = m.group(4);
            int count = 0;
            Pattern p2 = Pattern.compile("(\\b)(" + str + ")(\\W)");
            Matcher m2 = p2.matcher(input);
            while (m2.find()) {
                count++;
            }
            if (!hMap.containsKey(str)) {
                hMap.put(str, count);
            }
        }
        map.putAll(hMap);
        printArrayWords(changeWordsOrder(getThreeFirst(map)));
    }

    private static MyWord[] getThreeFirst(Map<String, Integer> map){
        int n = 0;
        int len = (map.size() > 3) ? 3 : map.size();
        MyWord[] mas = new MyWord[len];
        for(Map.Entry<String, Integer> pair : map.entrySet())
        {
            if (n == 3) {
                break;
            }
            mas[n++] = new MyWord(pair.getKey(), pair.getValue());
        }
        if (n < 3) {
            System.err.println("Warning, the count of word is less then 3!");
        }
        return mas;
    }

    private static MyWord[] changeWordsOrder(MyWord[] mas) {
        Arrays.sort(mas, new CompareAlphabet());
        return mas;
    }

    private static void printArrayWords(MyWord[] mas){
        int len = (mas.length > 3) ? 3 : mas.length;
        for(int i = 0; i < len; i++) {
            System.out.println(mas[i].name + " ==> " + mas[i].parametr);
        }
    }

    public static void main(String[] args) {
        String input = Util.getInput("part5.txt");
        System.out.println(input);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        frequency(input);
    }

}
