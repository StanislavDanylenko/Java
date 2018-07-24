package ua.nure.danylenko.Practice6.part7;

import java.util.Iterator;

public class Part7 implements Iterable<Integer> {
	private int start;
	private int finish;
	private int[] arr;
	private int len;
	private boolean reverse;

	public Part7(int a, int b, boolean rev) {
        start = a;
        finish = b;
        len = finish - start + 1;
        arr = new int[len];
        int inc = start;
        for (int i = 0; i < len; i++) {
            arr[i] = inc++;
        }
        reverse = rev;
    }

    public Part7(int a, int b) {
        start = a;
        finish = b;
        len = finish - start + 1;
        arr = new int[len];
        int inc = start;
        for (int i = 0; i < len; i++) {
            arr[i] = inc++;
        }
        reverse = false;
    }

	public void print(){
		Iterator<Integer> it = iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()){
			sb.append(it.next() + ", ");
		}
		String str = sb.substring(0, sb.length() - 2);
        System.out.println(str);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iter();
	}

	private class Iter implements Iterator<Integer> {
		int currPos = (reverse) ? (len - 1) : 0;

		@Override
		public boolean hasNext() {
			if (currPos < len && currPos >= 0) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public Integer next() {
			return (reverse) ? (arr[currPos--]) : (arr[currPos++]);
		}
	}

	public static void main(String[] args) {
		Part7 t1 = new Part7(3, 10, true);
        Part7 t2 = new Part7(3, 10, false);
		/*t1.print();
		t2.print();*/
		for (Integer el : t1) {
			System.out.print(el + " ");
		}
		System.out.println();
		for (Integer el : t2) {
			System.out.print(el + " ");
		}
	}}
