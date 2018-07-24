package ua.nure.danylenko.practice1;

public class Part7 {

    public static int chars2digits(String number) {

        int pos = number.length();
        int res = 0;
        int step = 0;
        for (int i = pos - 1; i >= 0; i--) {
            res += (number.charAt(i) - 64) * Math.pow(26, step++);
        }
        System.out.print(res + " ==> ");
        return res;
    }

    public static String digits2chars(int number) {
        // region посчитать максимальный степень
        int numberDigit = number;
        int power = -1;
        do {
            power++;
        } while (Math.pow(26, power + 1) <= number);
        //endregion

        // region массивы для операций со степенями и коэффициентами
        int countLetters = power;
        int[] buffer = new int[power + 1];
        int[] letterIndex = new int[power + 1];
        //endregion

        // region вычисление коэффициентов возле степеней и заполнение буфера сдвигов по-умолчанию
        while (power >= 0) {
            int koef = 0;
            int maxPow = (int) Math.pow(26, power);
            while (koef * maxPow <= numberDigit) {
                koef++;
            }
            koef--;
            numberDigit -= koef * maxPow;
            letterIndex[power] = koef;
            buffer[power] = 0;
            power--;
        }
        // endregion

        StringBuilder res = new StringBuilder();

        for (int i = 0; i <= countLetters; i++) {
            // компенсируем розряды (не в первой итерации)
            letterIndex[i] -= buffer[i];
            while (letterIndex[i] <= 0) {
                power = i;
                // находим у кого занять
                while (power <= countLetters && letterIndex[power] <= 0) {
                    power++;
                }
                //занимаем розряд
                if (power <= countLetters && letterIndex[power] > 0) {
                    letterIndex[i] += Math.pow(26, power - i);
                    // фиксируем займ в буфере
                    buffer[power]++;
                } else {
                    break;
                }
            }
            // если занимали несколько раз
            if (letterIndex[i] > 26) {
                if (letterIndex[i] % 26 == 0) {
                    letterIndex[i + 1] = (letterIndex[i] - 26) / 26;
                    letterIndex[i] = 26;
                } else {
                    letterIndex[i + 1] = letterIndex[i] / 26;
                    letterIndex[i] = letterIndex[i] % 26;
                }
            }
            // добавляем к результату
            if (letterIndex[i] > 0) {
                res.append((char) ('A' + letterIndex[i] - 1));
            }
        }
        System.out.println(res.reverse().toString());
        return res.reverse().toString();
    }

    public static String rightColumn(String number) {
        return (digits2chars(chars2digits(number)));
    }

    public static void main(String args[]) {
        String[] str = {"A", "B", "Z", "AA", "AZ", "BA", "ZZ", "AAA"};
        for (String currStr : str) {
            System.out.print(currStr + " ==> ");
            rightColumn(currStr);
        }
    }
}
