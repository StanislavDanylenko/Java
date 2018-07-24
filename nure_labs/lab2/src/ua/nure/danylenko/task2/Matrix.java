package ua.nure.danylenko.task2;

public class Matrix {
    private int rows;
    private int cols;
    private double[][] ar;

    public Matrix(double[][] ar) {
        if (ar == null) {
            throw new IllegalArgumentException("Ошибка: входной массив не должен быть null-типа!");
        }
        /*this.ar = ar;*/
        rows = ar.length;

        if (rows == 0) {
            //System.err.println("Внимание: была введена матрица с нулевым количеством строк!");
            throw new IllegalArgumentException("Была введена матрица с нулевым количеством строк!");
        }
        try {
            cols = ar[0].length;
        } catch (ArrayIndexOutOfBoundsException ex){
            throw new IllegalArgumentException("Была введена матрица с нулевым количеством столбцов!");
            //System.err.println("Внимание: была введена матрица с нулевым количеством столбцов!");
        }
        this.ar = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.ar[i][j] += ar[i][j];
            }
        }

    }

    // сложение с другой матрицей:
    public void add(Matrix m) {
        if (m == null) {
            throw new IllegalArgumentException("Матрица не должна быть null-типа");
        } else {
            if (this.rows != m.rows || this.cols != m.cols) {
                System.err.println("Размеры не совпадают, сложение НЕВОЗМОЖНО!");
            } else {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        ar[i][j] += m.ar[i][j];
                    }
                }
            }
        }
    }

    // умножение на число:
    public void mul(double x) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ar[i][j] *= x;
            }
        }
    }

    // умножение на другую матрицу:
    public void mul(Matrix m) {
        if (m == null) {
            throw new IllegalArgumentException("Матрица не должна быть null-типа");
        } else {
            if (this.cols == m.rows) {
                double[][] tempAr = new double[this.rows][m.cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < m.ar[0].length; j++) {
                        for (int k = 0; k < m.rows; k++) {
                            tempAr[i][j] += ar[i][k] * m.ar[k][j];
                        }
                    }
                }
                ar = tempAr;
                cols = m.cols;
            } else {
                System.err.println("Невозможно умножить матрицы! Количество столбцов первой матрицы должно быть " +
                        "равным количеству строк второй матрицы");
            }
        }
    }

    // транспонирование:
    public void transpose() {
        double[][] tempAr = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tempAr[j][i] = ar[i][j];
            }
        }
        ar = tempAr;
        int temp = cols;
        cols = rows;
        rows = temp;
    }

    // печать матрицы на экран:
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(ar[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Matrix m = new Matrix(new double[][]{
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0}
        });

        Matrix m2 = new Matrix(new double[][]{
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0}
        });

        System.out.println("~~~ m");
        m.print();

        System.out.println("~~~ m2");
        m2.print();

        System.out.println("~~~ transpose m");
        m.transpose();
        m.print();

        System.out.println("~~~ mul m on m2");
        m.mul(m2);
        m.print();

        System.out.println("~~~ mul m2 on 2");
        m2.mul(2);
        m2.print();
    }
}