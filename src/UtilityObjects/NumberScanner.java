package UtilityObjects;

/**
 * Created by Gabriel on 20/02/2017.
 */

public class NumberScanner {
    private String loadedString;
    private int position;

    public NumberScanner(String loadedString) {
        this.loadedString = loadedString;
        position = 0;
    }

    public Boolean hasNextDouble() {

        //Ignore all characters until a number starts
        for (; position < loadedString.length(); position++) {
            char ch = loadedString.charAt(position);
            //A number starts either with a digit or with a minus sign
            if (Character.isDigit(ch) || ch == '-')
                break;
        }

        //We check if we have actually found a number
        return !(position >= loadedString.length()
                ||
                (!Character.isDigit(loadedString.charAt(position))
                        &&
                        loadedString.charAt(position) != '-'));


    }

    public double getNextDouble() {

        if (!hasNextDouble())
            return 0;

        String number = "";
        number += loadedString.charAt(position);
        for (position++; position < loadedString.length(); position++) {
            if (Character.isDigit(loadedString.charAt(position)) || loadedString.charAt(position) == '.')
                number += loadedString.charAt(position);
            else
                break;
        }

        double result = Double.parseDouble(number);
        return result;
    }
}
