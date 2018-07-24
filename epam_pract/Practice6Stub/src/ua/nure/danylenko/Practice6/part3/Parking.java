package ua.nure.danylenko.Practice6.part3;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    public static int COUNT = 10;
    private List<Object> list;

    public Parking() {
        list = new ArrayList<>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            list.add(null);
        }
    }

    public boolean arrive(Object car, int n) {
        if (car != null && list.indexOf(car) == -1 && n < COUNT) {
            if (list.get(n) == null) {
                list.set(n, car);
                return true;
            } else {
                int pos = n + 1;
                while (pos++ < COUNT) {
                    if (list.get(pos) == null) {
                        list.set(n, car);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
    public boolean depart(Object obj){
        if (obj == null) {
            return false;
        }
        int index = list.indexOf(obj);
        if (index != -1) {
            list.set(index, null);
            return true;
        }
        return false;
    }

    public void print() {
        System.out.println(list);
    }

    public static void main(String[] args) {
        Parking p = new Parking();
        System.out.println(p.arrive(1, 2));
        p.arrive(2, 5);
        p.arrive(2, 6);
        p.arrive(3, 9);
        p.arrive(4, 9);
        p.arrive(5, 9);
        System.out.println(p.arrive(null, 9));
        p.print();
        p.depart(1);
        p.depart(3);
        p.depart(11);
        p.print();
        p.arrive(5, 9);
        p.print();
    }
}
