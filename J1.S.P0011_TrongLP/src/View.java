
import java.util.Scanner;

public class View {
    public static void displayMenu(){
        while(true){
            System.out.println("==================================================="); 
            System.out.println("== Change base number system (16, 10, 2) program ==");        
            System.out.println("Choose the base number input!");
            displayChoice();
            int in = Controller.getOption("Your choice: ", 1, 4);
            if (in ==4) {System.out.println("Thank for using application!"); break;}
            System.out.println("--------------------------------------");
            System.out.println("Choose the base number output!");
            displayChoice();
            int out = Controller.getOption("Your choice: ", 1, 4);
            if (out ==4) {System.out.println("Thank for using application!"); break;}
            String inputValue = Controller.inputValue(in);
            System.out.println("----------------Result----------------"); 
            if (in == out) System.out.println("Result: " + inputValue);
            else {
                String result = Controller.decimalTo(out, Controller.toDecimal(in, inputValue));
                System.out.println("===> Result: " + result);
                System.out.println("");
            }
        }
        
    }
    
    public static void displayChoice(){        
        System.out.println("1. Binary");
        System.out.println("2. Decimal");
        System.out.println("3. Hexadecimal");  
        System.out.println("4. Quit");
    }
    
 
}
    

