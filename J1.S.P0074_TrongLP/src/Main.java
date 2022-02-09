import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        while (true) {
            displayMenu();
            int option = getOption("Your choice: ");
            switch (option) {
                case 1:
                    Function.addition();
                    break;
                case 2:
                    Function.subtraction();
                    break;
                case 3:
                    Function.multiplication();
                    break;
                case 4: 
                    return;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("==============================");
        System.out.println("======Calculator Program======");
        System.out.println("1. Addition Matrix");
        System.out.println("2. Subtraction Matrix");
        System.out.println("3. Multiplication Matix");
        System.out.println("4. Quit");        
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
                if (1 <= option && option <= 4) {
                    return option;
                } else {
                    System.out.println("Only [1-4]!");
                }
            } catch (Exception e) {
                System.out.println("Only integer!");
            }
        }
    }
}
    

