package ua.nure.danylenko.Practice3.part5;

public class Part5 {

    public static String decimal2Roman(int x){
        String[] str10 = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] str100 = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String res;
        if (x < 10){
            res = str10[x];
        } else if (x == 100){
            res = "C";
        } else {
            int sDigit = x % 10;
            int fDigit = x / 10;
            StringBuilder sb = new StringBuilder();
            sb.append(str100[fDigit]).append(str10[sDigit]);
            res = sb.toString();
        }
        System.out.println(x + " ====> " + " " + res + " " + " ====> " + roman2Decimal(res));
        return res;
    }

    public static int roman2Decimal(String s) {
        String st = s;
        int res = 0;
        char prev = '\0';
        int prev_sum = 0;

        for (int i = 0; i < s.length(); i++) {
            res += toDec(st.charAt(i));
            if (i > 0) {
                prev_sum += toDec(st.charAt(i - 1));
            }
            if (i > 0 && toDec(st.charAt(i)) > toDec(st.charAt(i - 1))) {
                res -= 2 * prev_sum;
            }
            if (prev != st.charAt(i)) {
                prev_sum = 0;
                prev = st.charAt(i);
            }
        }
        return res;
    }

    private static int toDec(char s) {
        switch (s) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
        }
        return 0;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++){
            decimal2Roman(i);
        }
    }

}
