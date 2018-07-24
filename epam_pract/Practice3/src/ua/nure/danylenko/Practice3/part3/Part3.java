package ua.nure.danylenko.Practice3.part3;

import ua.nure.danylenko.Practice3.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String REGEX = "(?U)\\b(\\w)";

    public static String upperCase(String input) {

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            /*if (matcher.group(1) != null && sb.lastIndexOf(" ") != sb.length() - 1) {
                sb.append(matcher.group(1));
            }
            if (matcher.group(3).length() > 1) {
                sb.append(matcher.group(3).substring(0, 1).toUpperCase());
                sb.append(matcher.group(3).substring(1, matcher.group(3).length()));
            } else {
                sb.append(matcher.group(3).toUpperCase());
            }*/
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = Util.getInput("part3.txt");
        System.out.println(input);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println(upperCase(input));
    }
}
