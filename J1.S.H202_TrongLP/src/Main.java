public class Main {

    public static void main(String[] args) {
        displayMenu();
    }
    
    static void displayMenu(){
        System.out.println("====Reverse Character Program====");
        System.out.println("Input the string to be reversed!");
        String input = Function.getString("Your string: ");
        Function.printReverse(input);
        
    }
    
}
