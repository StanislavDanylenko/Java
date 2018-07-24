package ua.nure.danylenko.Practice3.part4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Part4 {

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());
        byte[] hash = digest.digest();
        //String[] hashString = new String[hash.length];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            sb.append(getHexCode(convertToValidBinary(Integer.toBinaryString(hash[i]))));
        }
       /* System.out.println(Arrays.toString(hash));
        System.out.println(Arrays.toString(hashString));*/
        return sb.toString();
    }

    private static String getHexCode(String binaryValue) {
        int bLength = binaryValue.length();
        String b1 = binaryValue.substring(bLength - 4, bLength);
        String b2 = binaryValue.substring(bLength - 8, bLength - 4);
        StringBuilder sb = new StringBuilder();
        sb.append(checkHex(b2));
        sb.append(checkHex(b1));
        return sb.toString();
    }

    private static String convertToValidBinary(String str) {
        int len = str.length();
        if (len < 8) {
            int addition = 8 - len;
            StringBuilder sb = new StringBuilder();
            while (addition > 0) {
                sb.append("0");
                addition--;
            }
            sb.append(str);
            return sb.toString();
        }
        return str;
    }

    private static String checkHex(String str) {
        String res = "";
        switch (str) {
            case "0000":
                res = "0";
                break;
            case "0001":
                res = "1";
                break;
            case "0010":
                res = "2";
                break;
            case "0011":
                res = "3";
                break;
            case "0100":
                res = "4";
                break;
            case "0101":
                res = "5";
                break;
            case "0110":
                res = "6";
                break;
            case "0111":
                res = "7";
                break;
            case "1000":
                res = "8";
                break;
            case "1001":
                res = "9";
                break;
            case "1010":
                res = "A";
                break;
            case "1011":
                res = "B";
                break;
            case "1100":
                res = "C";
                break;
            case "1101":
                res = "D";
                break;
            case "1110":
                res = "E";
                break;
            case "1111":
                res = "F";
                break;
        }
        return res;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("password to hash", "MD5"));
        System.out.println(hash("password", "SHA-256"));
        System.out.println(hash("passwort", "SHA-256"));
    }
}
