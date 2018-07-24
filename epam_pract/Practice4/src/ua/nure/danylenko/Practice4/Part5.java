package ua.nure.danylenko.Practice4;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
    public static void readProperties(String input) {
        String[] words = input.split(" ");

        if (words.length != 2) {
            throw new IllegalArgumentException("Count of argument must be 2");
        }

         if (words[0].equals("ru")) {
            Locale.setDefault(new Locale("ru"));
        } else {
            Locale.setDefault(new Locale("en"));
        }
        ResourceBundle rb = ResourceBundle.getBundle("resources");
        String str = null;
        try {
            str = rb.getString(words[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("No such property exist");
        }
        System.out.println(str);
        Locale.setDefault(new Locale("en"));
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = null;
        while (!"stop".equals(line = scan.nextLine())) {
            readProperties(line);
        }
    }
}