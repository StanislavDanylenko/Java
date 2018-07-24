package ua.nure.danylenko.Practice6.part1;

import java.util.Scanner;

public class Part1 {

	public static void main(String[] args) {
        WordContainer wc = new WordContainer();
        Scanner scan = new Scanner(System.in);
        String word = null;
        while(!"stop".equals(word = scan.nextLine())){
            wc.add(new Word(word));
        }
        wc.print();
	}
}
