package ua.nure.danylenko.Practice4;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements java.lang.Iterable{

        private static final String REGEX = "(?mU)(\\s*)(\\p{javaUpperCase}.*?[\\.\\?\\!])";
        private String input;

        public Part4(String str){
            input = str;
        }

        public void iterPattern(){
            Iterator<String> it = iterator();
            while (it.hasNext()) {
                System.out.print(it.next() + System.lineSeparator());
            }
        }

        public Iterator<String> iterator() {
            return new IteratorImpl();
        }

        private class IteratorImpl implements Iterator<String> {
            private int start = 0;
            private int sizeBuffer = 0;
            private Pattern p = Pattern.compile(REGEX);
            private Matcher matcher = p.matcher(input);
            private String str = null;

            @Override
            public boolean hasNext() {
                if (start >= input.length()){
                    return  false;
                }
                if (matcher.find(start)){
                    return true;
                }
                return false;
            }

            @Override
            public String next() {
                if (matcher.find(start)){
                    sizeBuffer = matcher.group().length();
                    start += sizeBuffer + 1;
                    str = matcher.group(2);
                    return str;
                } else {
                    str = null;
                    return null;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
        public static void main(String[] args) {
            String input = Util.getInput("part4.txt");
            System.out.println(input);
            System.out.println("~~~~~~~~~~~~~~~~~~~~");
            Part4 clazz = new Part4(input);
            Iterator<String> it = clazz.iterator();
            while (it.hasNext()) {
                System.out.print(it.next() + System.lineSeparator());
            }
            //System.out.println(iterPattern());
        }
    }

