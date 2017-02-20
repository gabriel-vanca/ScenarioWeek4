/**
 * Created by Gabriel on 20/02/2017.
 */

public class NumberScanner
{
    private String s;
    private int position;

    NumberScanner(String s)
    {
        this.s = s;
        position = 0;
    }

    private String SearchForNextDouble() {
        String number = "";
        for (; position < s.length(); position++) {
            char ch = s.charAt(position);
            if (Character.isDigit(ch) || ch == '-')
                break;
        }

        if (position >= s.length())
            return null;

        number += s.charAt(position);
        for (position++; position < s.length(); position++) {
            if (Character.isDigit(s.charAt(position)) || s.charAt(position) == '.')
                number += s.charAt(position);
            else
                break;
        }
        return number;
    }

    public double GetNextDouble()
    {

        String s = SearchForNextDouble();
        if(s == null)
            return 0;

        double result  = Double.parseDouble(s);
        return result;
    }

    public Boolean HasNextDouble()
    {
        int posAux = position;
        String s = SearchForNextDouble();
        position = posAux;
        return s != null;
    }
}
