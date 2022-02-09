
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utility {
    private static final Scanner sc = new Scanner(System.in);    
 //Get integer between min - max, must not empty
    public static int getInt(String msg, String errRange, String errInt, int min, int max) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            //Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Must not empty");
                continue;
            }
            try {
                int option = Integer.parseInt(input);
                if (min <= option && option <= max) {
                    return option;
                } else {
                    System.out.println(errRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(errInt);
            }
        }
    }
    
    public static double getDouble(String msg, String errRange, double min, double max, String regEx, String errFormat) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            //Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Must not empty");
                continue;
            }
            else if (!regEx.isEmpty()){ 
                if (!input.matches(regEx)){
                    System.out.println(errFormat);
                    continue;
                }
            }       
            double option = Double.parseDouble(input);
            if (min <= option && option <= max) {
                return option;
            } else {
                System.out.println(errRange);
            }
        }
    }
    
    public static String getDate(String msg){
        String input;
        Date date;
        String resultDate;
        do {
            System.out.print(msg);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Must not empty!");
                continue;
            } // \d{1,2}: the number have 1 or 2 digit number
            //[-]: contain character -
            //\d{4}: the number must have 4 digit
            else if (!input.matches("\\d{1,2}[-]\\d{1,2}[-]\\d{4}")) {
                System.out.println("Input is wrong format");
                continue;
            }
            try {
                date = dateFormat.parse(input);
                break;
            } catch (ParseException exception) {
                System.out.println("Date doesn't existed!!");
            }
        } while (true);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        resultDate = dateFormat.format(date);
        return resultDate;        
    }
    
    public static String getNonBlankString(String msg, String errFormat, String regex) {
        String input;
        do {
            System.out.print(msg);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Must not empty!");
            } else {
                if (regex.isEmpty()) {
                    break;
                    //compare input with regex
                } else if (input.matches(regex)) {
                    break;
                } else {
                    System.out.println(errFormat);
                }
            }
        } while (true);
        return input;
    }      
    
    public static String getString(String msg, String errFormat, String regex) {
        String input;
        do {
            System.out.print(msg);
            input = sc.nextLine();
            if (input.isEmpty()) {
                break;
            } else {
                if (regex.isEmpty()) {
                    break;
                    //compare input with regex
                } else if (input.matches(regex)) {
                    break;
                } else {
                    System.out.println(errFormat);
                }
            }
        } while (true);
        return input;
    }        
}
