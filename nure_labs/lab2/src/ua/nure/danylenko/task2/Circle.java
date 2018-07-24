package ua.nure.danylenko.task2;

import java.util.Locale;

public class Circle {
    private double x;
    private double y;
    private double r;

    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        if (r < 0) {
            throw new IllegalArgumentException("Радиус окружности должен иметь положительное значение!");
        }
        this.r = r;
    }

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean isInside(double x, double y) {
        double distance = ((x - this.x) * (x - this.x) + (y - this.y) * (y - this.y));
        if ( distance < r * r) {
            //System.out.println("Точка принадлежит окружности.");
            return true;
        } else if (distance == r * r) {
            //System.out.println("Точка лежит на окружности.");
            return false;
        } else {
            //System.out.println("Точка не принадлежит окружности.");
            return false;
        }
    }

    public boolean isInside(Circle other) {
        if (other == null) {
            System.err.println("Ошибка: передаваемая окружность не должна быть null-типа");
            return false;
        }
        if (Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y)) + other.r <= r) {
            //System.out.println("Окружность лежит внутри");
            return true;
        } else {
            //System.out.println("Окружность не лежит внутри");
            return false;
        }
    }

    public void print() {
        System.out.printf(Locale.ENGLISH, "Circle(%.1f, %.1f, %.1f)%n", x, y, r);
    }


    public static void main(String[] args) {
        System.out.println("~~~ c");
        Circle c = new Circle(0, 0, 1);
        c.print();
        System.out.println("~~~ c.move(1, 1)");
        c.move(1, 1);
        c.print();
        System.out.println("~~~ c.isInside(1, 1)");
        System.out.println(c.isInside(1, 1));
        System.out.println("~~~ c.isInside(10, 1)");
        System.out.println(c.isInside(10, 1));
        System.out.println("~~~ c2");
        Circle c2 = new Circle(1, 1, 2);
        c2.print();
        System.out.println("~~~ c.isInside(c2)");
        System.out.println(c.isInside(c2));
        System.out.println("~~~ c2.isInside(c)");
        System.out.println(c2.isInside(c));
    }
}
