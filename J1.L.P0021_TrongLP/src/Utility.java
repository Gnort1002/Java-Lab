import java.util.Random;
import java.util.Scanner;

public class Utility {
    private static Scanner sc = new Scanner(System.in);
    
    //Get integer between min - max, must not empty
    public static int getInt(String msg, String errRange, String errInt, int min, int max) {
        Scanner sc = new Scanner(System.in);
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
            } catch (Exception e) {
                System.out.println(errInt);
            }
        }
    }

    public static String getPatternStr(String msg, String error, String regEx){
        String str;
        while(true){
            System.out.print(msg);
            str = sc.nextLine();
            if (str.isEmpty()) {
                System.out.println("Must not empty");
                continue;  
            }
            if(!str.matches(regEx)){
                System.out.println(error);  
            }
            else return str;
        }
    }
    
    public static int getChoiceInArray(String[] arrayString){
        for (int i = 0; i < arrayString.length; i++) {
            System.out.println(arrayString[i]);
        }
        int option = getInt("Enter option: ", "Option must in range [1-" + arrayString.length + "]", 
                "Option must be an integer", 1, arrayString.length);
        return option;
    }
    
    public static int getRandomInRange(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }    
    public static String getRandomFirstName(){
        final String[] FirstNames = {"Le", "Dao", "Pham", "Tran", "Nguyen", "Hoang", "An", "Chu", "Bui"};
        return FirstNames[getRandomInRange(0, FirstNames.length-1)];
    }
    
    public static String getRandomLastName(){
        final String[] LastNames = {"Phu Trong", "Duy Hung", "Tuan Viet", "Quynh Trang", "Ngoc Hoa", "Hang Nga", "Viet Ha", "Duc Huy", "Van Anh"};
        return LastNames[getRandomInRange(0, LastNames.length-1)];
    }
    
    public static String getRandomName(){
        return getRandomFirstName() + " " + getRandomLastName();
    }
    
    public static String getRandomInRange2(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        if (0<=randomNum && randomNum< 10) return "00000" + randomNum;
        if (randomNum< 100) return "0000" + randomNum;
        if (randomNum< 1000)return "000" + randomNum;
        if (randomNum< 10000) return "00" + randomNum;           
        return "0" + randomNum;
    }
    
    public static String getRandomID(){
        return "HE" + getRandomInRange2(0, 99999); 
    }
    
    public static String getRandomSemester(){
        return getRandomInRange(1, 9) + "";
    }
    
    public static String getRandomCourse(){
        final String[] LastNames = {"Java", ".Net", "C/C++"};
        return LastNames[getRandomInRange(0, LastNames.length-1)];        
    }
    
}
