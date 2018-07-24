package ua.nure.danylenko.Practice5.Part4;

public class Part4 {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(4, 100);
        m1.randomFillArray();
        m1.findMaxValue();
        System.out.println("~~~~~~~~~~~~~~");
        m1.findMaxValueWithThreads();
    }
}
