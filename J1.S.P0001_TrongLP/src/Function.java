import java.util.Random;
import java.util.Scanner;

public class Function {
    private static Scanner sc = new Scanner(System.in);
        
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
        
    public static int getRandomIntegerInRange(int max){
        int arr[] = new int[(2*max+1)];
        for(int i = -max; i<=max; i++){
            arr[(i+max)] = i;
        }
        Random rand = new Random();
        int randomNum = arr[rand.nextInt((2*max+1))];
        return randomNum;
    }
    
    public static int[] generateArray(int input){
        int[] arr = new int[input];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getRandomIntegerInRange(input);           
        }
        return arr;
    }
    
    public static int[] bubleSortArray(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        }
        return arr;
    }
    
}
