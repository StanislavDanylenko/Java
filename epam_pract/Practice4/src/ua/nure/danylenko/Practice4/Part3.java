package ua.nure.danylenko.Practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String REGEX_STRING = "(?mU)(?<=(\\W)|(^))([^\\PL[0-9\\s]]{2,})(?=(\\W)|($))";
    private static final String REGEX_INT = "(?mU)(?<=(\\s)|(^))(-?[0-9]+)(?=(\\s)|($))";
    private static final String REGEX_CHAR = "(?mU)(?<=(\\W)|(^))([^\\PL[0-9\\s]])(?=(\\W)|($))";
    private static final String REGEX_DOUBLE = "(?mU)(?<=(\\s)|(^))(-?((\\.\\d+)|(\\d+\\.\\d+)|(\\d+\\.)))(?=(\\s)|($))";

    public static void findPrimitives(String input, String prim) {

        System.out.print(prim + ": ");

        String text = input;
        Pattern pattern = null;

        switch (prim) {
            case "char":
                pattern = Pattern.compile(REGEX_CHAR);
                break;
            case "String":
                pattern = Pattern.compile(REGEX_STRING);
                break;
            case "int":
                pattern = Pattern.compile(REGEX_INT);
                break;
            case "double":
                pattern = Pattern.compile(REGEX_DOUBLE);
                break;
        }

            Matcher matcher = pattern.matcher(text);
            StringBuilder sb = new StringBuilder();

            while (matcher.find()) {
                if (matcher.group(3).length() == 0) {
                    continue;
                }
                sb.append(matcher.group(3) + " ");
            }
            System.out.println(sb.toString());
        }

    public static void main(String[] args) {
        String input = Util.getInput("part3.txt");
        System.out.println(input);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Scanner scan = new Scanner(System.in);
        String line = null;
        while(!"stop".equals(line = scan.nextLine())){
            findPrimitives(input, line);
        }
    }
}
