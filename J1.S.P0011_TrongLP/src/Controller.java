import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Controller {
    private static Scanner sc = new Scanner(System.in);
    //Get integer between min - max, must not empty
    public static int getOption(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Must not empty!");
                continue;
            }
            try {
                int option = Integer.parseInt(input);
                if (min <= option && option <= max) {
                    return option;
                } else {
                    System.out.println("Only ["+ min + " - " + max +"]!");
                }
            } catch (Exception e) {
                System.out.println("Only integer!");
            }
        }
    }
    //Get string with expected format, use regular expression
    public static String getPatternStr(String msg, String regEx){
        String result;
        do{
            System.out.print(msg);
            result = sc.nextLine();
        }
        while(!result.matches(regEx));
        return result;
    }
    //Get the string contain value of the input number    
    public static String inputValue(int in){
        String value = "";
        switch (in){
            case 1: value = getPatternStr("Enter the input value (base 2): ", "[01]+"); break;
            case 2: value = getPatternStr("Enter the input value (base 10): ", "[0-9]+"); break;
            case 3: value = getPatternStr("Enter the input value (base 16): ", "[0-9A-Fa-f]+"); break;
        } 
        return value;
    }
    //Convert decimal into other base
    public static String decimalTo(int out, BigInteger dec){
        StringBuilder result = new StringBuilder();
        //out = 1: base 10 to base 2
        if (out == 1){
            System.out.println("base 2");
            //while dec > 0
            while (dec.compareTo(new BigInteger("0")) > 0){
                //result add the remainder (after dec/2) at the end
                result.append(dec.mod(new BigInteger("2")));
                dec = dec.divide(new BigInteger("2"));
            }
            //reverse the string result to get the true value
            result = result.reverse();
        }
        else if (out == 3){
            System.out.println("base 16");
            //while dec > 0
            while (dec.compareTo(new BigInteger("0")) > 0){
                BigInteger mod = dec.mod(new BigInteger("16"));
                //if the remainder < 10, add it at the end of result
                if (mod.compareTo(new BigInteger("10")) < 0){
                    result.append(mod);
                }
                //the the remainder >= 10: 
                else{
                    if (mod.compareTo(new BigInteger("10")) == 0) result.append("A");
                    if (mod.compareTo(new BigInteger("11")) == 0) result.append("B");
                    if (mod.compareTo(new BigInteger("12")) == 0) result.append("C");
                    if (mod.compareTo(new BigInteger("13")) == 0) result.append("D");
                    if (mod.compareTo(new BigInteger("14")) == 0) result.append("E");
                    if (mod.compareTo(new BigInteger("15")) == 0) result.append("F");
                }
                dec = dec.divide(new BigInteger("16"));
            }
            result.append(dec);
            result = result.reverse();
        }
        else if (out == 2){
            System.out.println("base 10");
            result.append(dec.toString());
        }
        return result.toString();     
    }
    
    public static BigInteger toDecimal(int in, String inputValue){
        BigInteger result = new BigInteger("0");
        //input number base 2
        if (in == 1){
            System.out.print("Convert " + inputValue + " (base 2) to " );
            //result = (result + digit of base 2 number) * 2
            for (int i = 0; i < inputValue.length(); i++){
                String temp = inputValue.charAt(i)+"";
                result = result.add(new BigInteger(temp));
                result = result.multiply(new BigInteger("2"));
            }
            //At the end, devide the result by 2
            result = result.divide(new BigInteger("2"));
        }
        //input number base 16
        else if (in == 3){
            System.out.print("Convert " + inputValue + " (base 16) to " );
            for (int i = 0; i < inputValue.length(); i++){
                char c = inputValue.charAt(i);
                //if element of input value is digit, add to result
                if (Character.isDigit(c)){
                    result = result.add(new BigInteger(c + ""));
                }
                //else convert A to 10, B to 11, C to 12, D to 13, E to 14 and F to 15, then add to result
                else{
                    //aBcDef -> ABCDEF
                    Character.toUpperCase(c);
                    //getNumericValue('A') = 10
                    result = result.add(new BigInteger(Character.getNumericValue(c) + ""));
                }
                result = result.multiply(new BigInteger("16"));
            }
            result = result.divide(new BigInteger("16"));
        }
        else if (in ==2){
            System.out.print("Convert " + inputValue + " (base 10) to " );
            result = new BigInteger(inputValue);
        }
        return result;
        
    }
}
