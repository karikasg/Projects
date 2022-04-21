package tavalyiZV.digitscounter;

import java.util.HashSet;

public class DigitsCounter {

    public int getCountOfDigits(String input) {

        if (input == null) return 0;

        HashSet<Character> characters = new HashSet<>();

        for (char chr : input.toCharArray()) {
            if (Character.isDigit(chr)) {
                characters.add(chr);
            }
        }

        return characters.size();
    }
}
