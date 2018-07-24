package ua.nure.danylenko.practice1;

public class Part5 {
    public static void main(String args[]) {
        int num = Integer.parseInt(args[0]);
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
        }
        System.out.println(res);
    }
}
