package ua.nure.danylenko.Practice4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final String REGEX = "(?U)\\b([\\w`]{3,})";

    public static String processText(String input) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        String word;
        StringBuilder currentWord = new StringBuilder();
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            word = matcher.group(1);
            for (int i = 0; i < word.length(); i++) {
                currentWord.append(Character.toUpperCase(word.charAt(i)));
            }
            matcher.appendReplacement(result, currentWord.toString());
            currentWord.setLength(0);
        }
        matcher.appendTail(result);
        return result.toString();
    }

    public static void main(String[] args) {
        String input = Util.getInput("part1.txt");
        System.out.println(input);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println(processText(input));
    }

}
