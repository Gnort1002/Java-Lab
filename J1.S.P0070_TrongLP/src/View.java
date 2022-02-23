
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class View {
    
    Controller c = new Controller();
    
    void display(){
        if(!c.readFromFile("src\\Data.txt")){
            System.out.println("Fail to read file data!");
            return;
        }
        else{
        // Loops until user want to exit program
        while (true){
            // Display menu
            displayMenu();
            int option = getOption("Please choice one option: ");
            switch (option) {
                case 1:
                    // perform check login function with Vietnamese
                    c.setLocale(new Locale("Vi"));
                    login();
                    break;
                case 2:
                    // perform check login function with English
                    c.setLocale(new Locale("En"));
                    login();
                    break;
                case 3:
                    // Exit program
                    return;
            }
        }   
        }
    }
    
    public static void displayMenu() {
        System.out.println("-------Login Program-------");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
    }  
    //Option must be between [1,3], not empty and only integer
    public static int getOption(String msg) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Must not empty");
                continue;
            }
            try {
                int option = Integer.parseInt(input);
                if (1 <= option && option <= 3) {
                    return option;
                } else {
                    System.out.println("Must in range [1-3]");
                }
            } catch (Exception e) {
                System.out.println("Only integer");
            }
        }
    }
    //Allows the user to input the account number, password and captcha from the keyboard.
    public void login() {
            ResourceBundle bundle = c.getBundle();
            //Input data
            String accountNumber = getData(bundle, 1);
            String password = getData(bundle, 2);
            String captcha = getData(bundle, 3);
            ArrayList<Account> accountList = c.getList();
            for (Account account : accountList) {
                //Check if input accountNumber equal accountNumber of accessing account
                if (account.getAccountNumber().equals(accountNumber)) {
                    //Check if input password equal password of accessing account
                    if (account.getPassword().equals(password)) {
                        System.out.println(bundle.getString("LoginSuccess"));
                        return;
                    } else {
                        System.out.println(bundle.getString("WrongPass"));
                        return;
                    }
                }
            }
            System.out.println(bundle.getString("AccNumNotExist"));
        }
    //Input data base on input type, 1: input acc, 2: input password, 3: input captcha
    //print error if input String does not pass the condition
    public String getData(ResourceBundle bundle, int choice) {
        String captcha = "";
        Scanner sc = new Scanner(System.in);
        String input, output = "";
        //Loops to validate the input
        while (true) {
            switch (choice) {
                case 1:
                    System.out.print(bundle.getString("AccNumMsg"));
                    break;
                case 2:
                    System.out.print(bundle.getString("PasswordMsg"));
                    break;
                case 3:
                    captcha = c.generateCaptcha();
                    System.out.println(bundle.getString("Captcha") + captcha);
                    System.out.print(bundle.getString("CaptchaMsg"));
                    break;
            }
            input = sc.nextLine();
            switch (choice) {
                case 1:
                    output = c.checkAccountNumber(input);
                    if (output == null) break;
                    else System.out.println(output);
                    break;
                case 2:
                    output = c.checkPassword(input);
                    if (output == null) break;
                    else System.out.println(output);
                    break;                    
                case 3:
                    output = c.checkCaptcha(input, captcha);
                    if (output == null) break;
                    else System.out.println(output);
                    break;                     
            }
            if (output == null) return input;
        }    
    }
}
