package ua.nure.danylenko.Practice3.part1;

import ua.nure.danylenko.Practice3.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final String REGEX1 = "(?mu)^(.*);(.*\\s.*);(.*@.*\\..*)$";
    private static final String REGEX3 = "(?mu)^(.*);(.*\\s.*);(.*)@(.*\\.\\S*)$";
    private static final String REGEX4 = "(?mu)^(.*);(.*);([^@]*)$";

    public static String convert1(String input) {
        Pattern pattern = Pattern.compile(REGEX1);
        Matcher matcher = pattern.matcher(input);

        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group(1))
                    .append(" ==> ")
                    .append(matcher.group(3))
                    .append(System.lineSeparator());
        }
        sb.delete(sb.lastIndexOf(System.lineSeparator()), sb.length());
        return sb.toString();
    }

    public static String convert2(String input) {
        Pattern pattern = Pattern.compile(REGEX1);
        Matcher matcher = pattern.matcher(input);

        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group(2))
                    .append(" (email: ")
                    .append(matcher.group(3))
                    .append(")")
                    .append(System.lineSeparator());
        }
        sb.delete(sb.lastIndexOf(System.lineSeparator()), sb.length());
        return sb.toString();
    }

    public static String convert3(String input) {
        Pattern pattern = Pattern.compile(REGEX3);
        Matcher matcher = pattern.matcher(input);

        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String str = matcher.group(4);
            if (sb.lastIndexOf(matcher.group(4)) == -1) {
                sb.append(matcher.group(4)).append(" ==> ");

                Matcher m2 = pattern.matcher(input);

                while (m2.find()) {
                    if (m2.group(4).equals(str)) {
                        sb.append(m2.group(1)).append(", ");
                    }
                }
                int lastPoint = sb.lastIndexOf(", ");
                sb.deleteCharAt(lastPoint);
                sb.append(System.lineSeparator());
            }
        }
        sb.delete(sb.lastIndexOf(System.lineSeparator()), sb.length());
        return sb.toString();
    }

    public static String convert4(String input) {
        StringBuilder sb = new StringBuilder();

        Pattern p2 = Pattern.compile(REGEX4);
        Matcher m2 = p2.matcher(input);

        while (m2.find()) {
            sb.append(m2.group()).append(";Password").append(System.lineSeparator());
        }

        Pattern pattern = Pattern.compile(REGEX1);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            sb.append(matcher.group()).append(";").append((int)(Math.random()*9000)+1000).append(System.lineSeparator());
        }
        sb.delete(sb.lastIndexOf(System.lineSeparator()), sb.length());
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = Util.getInput("part1.txt");
        System.out.println(input);
        System.out.println("=====Convert1=====");
        System.out.println(convert1(input));
        System.out.println("=====Convert2=====");
        System.out.println(convert2(input));
        System.out.println("=====Convert3=====");
        System.out.println(convert3(input));
        System.out.println("=====Convert4=====");
        System.out.println(convert4(input));
    }
}
