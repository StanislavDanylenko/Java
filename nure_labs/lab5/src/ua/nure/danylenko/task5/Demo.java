package ua.nure.danylenko.task5;

import ua.nure.danylenko.task5.Part1.Spam;
import ua.nure.danylenko.task5.Part2.Part2;

import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("=============PART1==========");
        Spam.main(args);
        System.out.println("=============PART2==========");
        Part2.main(args);
        System.out.println("============================");
    }
}
