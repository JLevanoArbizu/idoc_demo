package servicios;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptarS {

    public static String encriptarPssw(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String encriptarDocumento(String input) {
        int output = 0;
        String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        try {
            char caracteres[] = input.toUpperCase().toCharArray();
            int total = 0;
            for (char caracter : caracteres) {
                total += Integer.parseInt(String.valueOf(caracter));
            }
            for (char caracter : caracteres) {
                output += Integer.parseInt(String.valueOf(caracter)) * total;
            }
            output %= 27;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(abecedario.charAt(output));
    }
}
