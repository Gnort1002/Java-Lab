
import java.util.Scanner;

public class Function {
    private static Scanner sc = new Scanner(System.in);
    
    public static int[] selectionSortArray(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min])
                    min = j;
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
                
        }
        return arr;
    }    
    
    public static int getInteger(String msg){
        while (true) {
            try {
                System.out.print(msg);
                int result = Integer.parseInt(sc.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println("Must be integer!");
            }
        } 
    }
    
    public static int getIntegerGreater(String msg, boolean greater, int min){
        while (true) {
            try {
                System.out.print(msg);
                int result = Integer.parseInt(sc.nextLine());
                if (greater)
                    if (result <= min){
                        System.out.println("Must be greater than " + min + "!");
                        continue;
                    }
                return result;
            } catch (Exception e) {
                System.out.println("Must be integer!");
            }
        } 
    }

    public static int[] inputArray(int input) {
        int[] arr = new int[input];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getIntegerGreater("Enter the " + (i + 1) + "th element: ", true, 0);           
        }
        return arr;        
    }
    
}
