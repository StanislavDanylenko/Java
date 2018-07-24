package ua.nure.danylenko.task4;

import ua.nure.danylenko.task4.Part1.Part1;
import ua.nure.danylenko.task4.Part2.Part2;
import ua.nure.danylenko.task4.Part3.Part3;
import ua.nure.danylenko.task4.Part4.Part4;
import ua.nure.danylenko.task4.Part5.Part5;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Demo {

    private static final InputStream STD_IN = System.in;
    private static final String ENCODING = "Cp1251";

    public static void main(String[] args) throws IOException {

        System.out.println("=========================== PART1");
        Part1.main(args);
        System.out.println("=========================== PART2");
        Part2.main(args);
        System.out.println("=========================== PART3");
        System.setIn(new ByteArrayInputStream(
                "en apple^ru table^ru apple^en table^stop".replace("^", System.lineSeparator())
                        .getBytes(ENCODING)));
        Part3.main(args);
        // restore the standard input
        System.setIn(STD_IN);
        System.out.println("=========================== PART4");
        Part4.main(args);
        System.out.println("=========================== PART5");
        Part5.main(args);
    }
}
