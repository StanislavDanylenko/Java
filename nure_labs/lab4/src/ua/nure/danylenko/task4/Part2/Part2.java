package ua.nure.danylenko.task4.Part2;

import ua.nure.danylenko.task4.Part1.Part1;
import ua.nure.danylenko.task4.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) {
        String input = Util.getInput("part2.txt");
        System.out.println(input);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        WordContainer wc = new WordContainer();
        //String[] words = (input.split(" "));

        Part1 clazz = new Part1(input);
        Iterator<String> it = clazz.iterator();
        List<String> words = new ArrayList<>();
        while (it.hasNext()) {
            words.add(it.next());
        }

        for (String str : words) {
            wc.add(new Word(str));
        }
        wc.print();
	}
}
