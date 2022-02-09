
public class Main {

    public static void main(String[] args) {
        displayMenu();
    }
    public static void displayMenu(){
        System.out.println("=================================");
        System.out.println("===Selectionsort Array Program===");        
        System.out.println("Enter positive number for input value");
        int input;
        while (true){
            System.out.println("-------------------------------------");
            System.out.println("Enter 0 or negative number to quit program!");
            input = Function.getIntegerGreater("Enter number of array: ", false, 0);
            if (input <= 0){ System.out.println("Thank for using program!"); return;}
            int[] arr = new int[input];
            System.out.println("-------------------------------------");
            System.out.println("Enter positive number for elements!");
            arr = Function.inputArray(input);
            System.out.print("Unsorted array: "); 
            displayArray(arr);
            System.out.print("Sorted array: ");
            displayArray(Function.selectionSortArray(arr));
        }
        
    }
    
    public static void displayArray(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arr.length - 1]);
        System.out.println("]");
    }
}
