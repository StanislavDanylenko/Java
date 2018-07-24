package ua.nure.danylenko.Practice6;

import ua.nure.danylenko.Practice6.part1.Part1;
import ua.nure.danylenko.Practice6.part2.Part2;
import ua.nure.danylenko.Practice6.part3.Part3;
import ua.nure.danylenko.Practice6.part4.Part4;
import ua.nure.danylenko.Practice6.part5.Part5;
import ua.nure.danylenko.Practice6.part6.Part6;
import ua.nure.danylenko.Practice6.part7.Part7;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Demo {

	private static final InputStream STD_IN = System.in;
	private static final String ENCODING = "Cp1251";

	public static void main(String[] args) throws Exception {
		/*System.out.println("~~~~~~~~~~~~Part1");
		System.setIn(new ByteArrayInputStream(
				"asd^asdf^asd^asdf^asdf^43^asdsf^43^43^434^stop".replace("^", System.lineSeparator()).getBytes(ENCODING)));
		Part1.main(args);
		// restore the standard input
		System.setIn(STD_IN);;
		
		System.out.println("~~~~~~~~~~~~Part2");
		Part2.main(args);

		System.out.println("~~~~~~~~~~~~Part3");
		Part3.main(args);
		
		System.out.println("~~~~~~~~~~~~Part4");
		Part4.main(args);
		
		System.out.println("~~~~~~~~~~~~Part5");
		Part5.main(args);		
*/
		System.out.println("~~~~~~~~~~~~Part6");
		//Part6.main(args);
		Part6.main(new String[]{"--input", "part61.txt", "--task", "length"});
		Part6.main(new String[]{"--input", "part61.txt", "--task", "frequency"});
		Part6.main(new String[]{"--input", "part61.txt", "--task", "duplicates"});

		/*System.out.println("~~~~~~~~~~~~Part7");
		Part7.main(args);		*/
	}

}