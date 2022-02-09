
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
    public void login() {
            ResourceBundle bundle = c.getBundle();
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
    
    public String getData(ResourceBundle bundle, int choice) {
        String captcha = "";
        Scanner sc = new Scanner(System.in);
        String input;
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
            boolean isValid = true;
            switch (choice) {
                case 1:
                    isValid = c.checkAccountNumber(input, bundle);
                    break;
                case 2:
                    isValid = c.checkPassword(input, bundle);
                    break;
                case 3:
                    isValid = c.checkCaptcha(input, captcha, bundle);
                    break;
            }
            //Check if msg equal "true"
            if (isValid == true) {
                return input;
            } else {
                switch (choice) {
                    case 1:
                        System.out.println(bundle.getString("AccNumErr"));
                        break;
                    case 2:
                        System.out.println(bundle.getString("PasswordErr"));
                        break;
                    case 3:
                        System.out.println(bundle.getString("CaptchaErr"));
                        break;
                }
            }
        }    
    }
}
