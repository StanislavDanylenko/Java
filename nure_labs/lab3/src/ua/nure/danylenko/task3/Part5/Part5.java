package ua.nure.danylenko.task3.Part5;

import ua.nure.danylenko.task3.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    private static final String REGEX1 = "(?mu)^(.*);(.*\\s.*);(.*@.*\\..*)$";
    private static final String REGEX3 = "(?mu)^(.*);(.*\\s.*);(.*)@(.*\\.\\S*)$";
   // private static final String REGEX4 = "(?mu)^(.*);(.*);([^@]*)$";

    public static String convert1(String input) {
        Pattern pattern = Pattern.compile(REGEX1);
        Matcher matcher = pattern.matcher(input);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group(1))
                    .append(" ==> ")
                    .append(matcher.group(3))
                    .append(System.lineSeparator());
        }
        result.delete(result.lastIndexOf(System.lineSeparator()), result.length());
        return result.toString();
    }

    public static String convert2(String input) {
        Pattern p = Pattern.compile(REGEX1);
        Matcher m = p.matcher(input);

        StringBuilder result = new StringBuilder();
        while (m.find()) {
            result.append(m.group(2))
                    .append(" (email: ")
                    .append(m.group(3))
                    .append(")")
                    .append(System.lineSeparator());
        }
        result.delete(result.lastIndexOf(System.lineSeparator()), result.length());
        return result.toString();
    }

    public static String convert3(String input) {
        Pattern pattern = Pattern.compile(REGEX3);
        Matcher matcher = pattern.matcher(input);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String domen = matcher.group(4);
            if (result.lastIndexOf(matcher.group(4)) == -1) {
                result.append(matcher.group(4)).append(" ==> ");

                Matcher matcher2 = pattern.matcher(input);

                while (matcher2.find()) {
                    if (matcher2.group(4).equals(domen)) {
                        result.append(matcher2.group(1)).append(", ");
                    }
                }
                int lastPoint = result.lastIndexOf(", ");
                result.deleteCharAt(lastPoint);
                result.append(System.lineSeparator());
            }
        }
        result.delete(result.lastIndexOf(System.lineSeparator()), result.length());
        return result.toString();
    }
//region comment
/*    public static String convert4(String input) {
        StringBuilder sb = new StringBuilder();

        Pattern p2 = Pattern.compile(REGEX4);
        Matcher m2 = p2.matcher(input);

        while (m2.find()) {
            sb.append(m2.group()).append(";Password").append(System.lineSeparator());
        }

        Pattern p = Pattern.compile(REGEX1);
        Matcher m = p.matcher(input);

        while (m.find()) {
            sb.append(m.group()).append(";").append((int)(Math.random()*9000)+1000).append(System.lineSeparator());
        }
        sb.delete(sb.lastIndexOf(System.lineSeparator()), sb.length());
        return sb.toString();
    }*/
//endregion
    public static void main(String[] args) {
        String input = Util.getInput("part5.txt");
        System.out.println(input);
        System.out.println("=====Convert1=====");
        System.out.println(convert1(input));
        System.out.println("=====Convert2=====");
        System.out.println(convert2(input));
        System.out.println("=====Convert3=====");
        System.out.println(convert3(input));
      /*System.out.println("=====Convert4=====");
        System.out.println(convert4(input));*/
    }
}
