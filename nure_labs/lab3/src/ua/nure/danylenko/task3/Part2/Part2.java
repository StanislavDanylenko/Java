package ua.nure.danylenko.task3.Part2;

import ua.nure.danylenko.task3.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    private static final String REGEX = "(?U)\\b(\\S+)";

    public static String deleteSimilarCharsWord(String input) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);

        char[] word;
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            word = matcher.group(1).toCharArray();
            boolean isDelete = false;
            loop:
            for (int i = 0; i < word.length - 1; i++) {
                for (int j = i + 1; j < word.length; j++) {
                    if (word[i] == word[j]) {
                        isDelete = true;
                        break loop;
                    }
                }
            }
            if (isDelete) {
                matcher.appendReplacement(result, "");
            } else {
                matcher.appendReplacement(result, new String(word));
            }
        }
        matcher.appendTail(result);
        return result.toString();
    }

    public static void main(String[] args) {
        String input = Util.getInput("part2.txt");
        System.out.println(input);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println(deleteSimilarCharsWord(input));
    }
}
