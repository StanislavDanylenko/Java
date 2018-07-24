package ua.nure.danylenko.Practice6.part2;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Part2{
	public static int K = 3;
	public static int N = 10000;

	public static void remove1(List<Object> list) {
		//System.out.println(list);
		long start = System.currentTimeMillis();
		int pos = 0;
		int counter = 1;
		while (list.size() > 1) {
			if (pos == list.size()) {
				pos = 0;
			}
			if (counter == K) {
				list.remove(pos);
				counter = 0;
			} else {
				pos++;
			}
			counter++;
		}
		System.out.print(list.get(0));
		System.out.println(" | remove1 | Время выполнения = " + (System.currentTimeMillis() - start));
	}

	public static void remove2(List<Object> list){
		long start = System.currentTimeMillis();
		Iterator<Object> iter;
		int count = 0;
		while (list.size() > 1) {
			iter = list.iterator();
			while (iter.hasNext()){
				iter.next();
				count++;
				if (count == K) {
					iter.remove();
					count = 0;
				}
			}
		}
		System.out.print(list.get(0));
		System.out.println(" | remove2 | Время выполнения = " + (System.currentTimeMillis() - start));
	}

	public static List<Object> init(List<Object> obj) {
		for (int i = 0; i < N; i++) {
			obj.add(i);
		}
		return obj;
	}


	public static void main(String[] args) {
		List<Object> arrayList = init(new ArrayList<>());
		List<Object> linkedList = init(new LinkedList<>());

		System.out.println("=========== Indexed");
		// ...
		remove1(arrayList);
		// ...
		remove1(linkedList);
		// ...

		System.out.println("=========== Iterable");
		arrayList = init(new ArrayList<>());
		linkedList = init(new LinkedList<>());

		// ...
		remove2(arrayList);
		// ...
		remove2(linkedList);
	}
}
