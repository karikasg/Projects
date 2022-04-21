package vaccine;

import java.util.Random;

import static java.lang.Math.random;

public class GenerateTaj {

    public static void main(String[] args) {


        Random random = new Random();
        String tajNr = "";
        for (int i = 0; i<8; i++) {
            tajNr = tajNr + (char)(random.nextInt(10)+48);
        }
        int sum = 0;
        char chr;

        for (int i = 0; i < tajNr.length(); i++) {
            chr = tajNr.charAt(i);
            if (i % 2 == 0) {
                sum += (chr - 48) * 3;
            } else {
                sum += (chr - 48) * 7;
            }
        }
        tajNr = tajNr + (char)(sum % 10 + 48);
        System.out.println(tajNr);
    }
}

