package ua.nure.danylenko.Practice3.part2;

import ua.nure.danylenko.Practice3.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static final String REGEX = "(?mU)(?<=(\\W)|(^))(\\w*?)(?=(\\W)|($))";

    public static String findMinMax(String input) {

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);

        StringBuilder min = new StringBuilder();
        min.append("Min: ");
        StringBuilder max = new StringBuilder();
        max.append("Max: ");

        int maxLen = 0;
        int minLen = Integer.MAX_VALUE;

        while (matcher.find()) {
            if (matcher.group(3).length() == 0){
                continue;
            }
            if (matcher.group(3).length() < minLen){
                minLen = (matcher.group(3).length());
                min = new StringBuilder();
                min.append("Min: ");
                min.append(matcher.group(3));
            } else if ( matcher.group(3).length() == minLen) {
                if (min.lastIndexOf(matcher.group(3)) == -1) {
                    min.append(", ").append(matcher.group(3));
                }
            }
            if (matcher.group(3).length() > maxLen){
                maxLen = (matcher.group(3).length());
                max = new StringBuilder();
                max.append("Max: ");
                max.append(matcher.group(3));
            } else if ( matcher.group(3).length() == maxLen) {
                if (max.lastIndexOf(matcher.group(3)) == -1) {
                    max.append(", ").append(matcher.group(3));
                }
            }
        }
        min.append(System.lineSeparator()).append(max);
        return min.toString();
    }

    public static void main(String[] args) {
        String input = Util.getInput("part2.txt");
        System.out.println(input);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println(findMinMax(input));
    }

}
