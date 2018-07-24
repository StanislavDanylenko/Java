package ua.nure.danylenko.task4.Part1;

import ua.nure.danylenko.task4.Util;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 implements java.lang.Iterable {
    private static final String REGEX = "(?mU)(([^a-zA-ZА-Яа-яёЁЇїІіЄєҐґ]+)|(^))([a-zA-ZА-Яа-яёЁЇїІіЄєҐґ]+)" +
            "(?=([^a-zA-ZА-Яа-яёЁЇїІіЄєҐґ])|($))";
    private String input;

    public Part1(String str){
        input = str;
    }

    public Iterator<String> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<String> {
        private int start = 0;
        private int sizeBuffer = 0;
        private Pattern pattern = Pattern.compile(REGEX);
        private Matcher matcher = pattern.matcher(input);

        private String str = null;
        int textLen = input.length();

        @Override
        public boolean hasNext() {
            if (start >= textLen){
                return  false;
            }
            if (matcher.find(start)){
                return true;
            }
            return false;
        }

        @Override
        public String next() {
            if (matcher.find(start)) {
                sizeBuffer = matcher.group().length();
                start += sizeBuffer;
                str = matcher.group(4);
                return str;
            } else {
                return null;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
        String input = Util.getInput("part1.txt");
        System.out.println(input);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        Part1 clazz = new Part1(input);
        Iterator<String> it = clazz.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + System.lineSeparator());
        }
    }
}
