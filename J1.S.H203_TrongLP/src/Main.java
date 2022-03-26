public class Main {

    public static void main(String[] args) {
//        displayMenu();
        Function.printReverse("");
    }
    
    static void displayMenu(){
        System.out.println("====Reverse Word Program====");
        System.out.println("Input the string to be reversed!");
        String input = Function.getNonBlankStr("Your string: ");
        Function.printReverse(input);
       
    }
}
