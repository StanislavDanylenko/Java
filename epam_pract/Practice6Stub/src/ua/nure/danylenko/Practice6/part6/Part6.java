package ua.nure.danylenko.Practice6.part6;

import ua.nure.danylenko.Practice6.Util;
import ua.nure.danylenko.Practice6.part1.Word;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

	private static final String REGEX_FREQUENCY = "(\\b)([a-zA-Z]+)(\\W)";
	Map<String, Integer> map;
	Map<String, Integer> resMap;

	public Part6(){
	}

	private void frequency(String input) {
		Map<String, Integer> hMap = new LinkedHashMap<>();
		ComparatorFrequency comp = new ComparatorFrequency(hMap);
		map = new TreeMap<>(comp);

		Pattern p = Pattern.compile(REGEX_FREQUENCY);
		Matcher m = p.matcher(input);
		//StringBuffer sb = new StringBuffer();

		while (m.find()) {
			String str = m.group(2);
			int count = 0;
			//hMap.put(str, count);
			Pattern p2 = Pattern.compile("(\\b)(" + str + ")(\\W)");
			Matcher m2 = p2.matcher(input);
			while (m2.find()) {
				count++;
			}
			if (!hMap.containsKey(str)) {
				hMap.put(str, count);
			}
		}
		map.putAll(hMap);
		printArrayWords(changeWordsOrder(getThreeFirst(map)));
		//printFrequency();
	}

	private void length(String input) {
		Map<String, Integer> hMap = new LinkedHashMap<>();
		ComparatorFrequency comp = new ComparatorFrequency(hMap);
		map = new TreeMap<>(comp);

		Pattern p = Pattern.compile(REGEX_FREQUENCY);
		Matcher m = p.matcher(input);

		while (m.find()) {
			String str = m.group(2);
			int len = str.length();
			if (!hMap.containsKey(str)) {
				hMap.put(str, len);
			}
		}
		map.putAll(hMap);
		printArrayWords(getThreeFirst(map));
		//printFrequency();
	}

	private void dublicates(String input) {
		String[] words = new String[3];
		Pattern p = Pattern.compile(REGEX_FREQUENCY);
		Matcher m = p.matcher(input);
		int countWords = 0;

		loop: while (m.find()) {
			String str = m.group(2);
			int count = 0;
			Pattern p2 = Pattern.compile("(\\b)(" + str + ")(\\W)");
			Matcher m2 = p2.matcher(input);
			while (m2.find()) {
				count++;
				if (count == 2) {
					words[countWords] = str;
					countWords++;
					if (countWords == 3) {
						break loop;
					}
				}
			}
		}
		processWords(words);
	}

	private MyWord[] getThreeFirst(Map<String, Integer> m){
		int n = 0;
		MyWord[] mas = new MyWord[3];
		for(Map.Entry<String, Integer> pair : map.entrySet())
		{
			if (n == 3) {
				break;
			}
			mas[n++] = new MyWord(pair.getKey(), pair.getValue());
		}
		return mas;
	}

	private MyWord[] changeWordsOrder(MyWord[] mas) {
		Arrays.sort(mas, new CompareAlphabet());
		return mas;
	}

	private void processWords(String[] mas) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			for (int j = mas[i].length() - 1; j >= 0 ; j--) {
				sb.append(Character.toUpperCase(mas[i].charAt(j)));
			}
			System.out.println(sb.toString());
			sb.setLength(0);
		}
	}

	private void printFrequency(){
		for(Map.Entry<String, Integer> pair : map.entrySet())
		{
			System.out.println(pair.getKey() + " ==> " + pair.getValue());
		}
	}

	public void operateString(String operation, String input) {
		if ("length".equals(operation)) {
            length(input);
		} else if ("frequency".equals(operation)) {
            frequency(input);
		} else if ("duplicates".equals(operation)) {
            dublicates(input);
		}
	}

	private void printArrayWords(MyWord[] mas){
		for(int i = 0; i < 3; i++) {
			System.out.println(mas[i].name + " ==> " + mas[i].parametr);
		}
	}

	public static void main(String[] args) {
		if (args.length < 4) {
			throw new IllegalArgumentException();
		}
		Part6 p = new Part6();
		if ("--input".equals(args[0]) || "-i".equals(args[0])) {
			String input = Util.getInput(args[1]);
			System.out.println(input);
			System.out.println("~~~~~~~~~~~~~~~~~~~~");
			if ("--task".equals(args[2])) {
                p.operateString(args[3], input);
            }
		}
	}
}
