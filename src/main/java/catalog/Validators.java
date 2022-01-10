package catalog;

import java.util.List;

public class Validators {

    public static boolean isBlank(String sg) {
        if (sg != null && !sg.trim().equals("") ) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(List lst) {
        if (lst != null && !lst.isEmpty() ) {
            return false;
        }
        return true;
    }
}
