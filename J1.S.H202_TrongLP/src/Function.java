import java.io.*;
import java.util.*;
import java.lang.*;

public class Function {
    public static Scanner sc = new Scanner(System.in);
    
    public static void printReverse(String input){
        StringBuilder result = new StringBuilder();
        if (input.equals("")) return;
        for (int i = input.length()-1; i >=0; i--) {
            result.append(input.charAt(i) + "");           
        }
        System.out.println(result.toString());
    }

    static String getString(String msg) {
        String result;
        System.out.print(msg);
        result = sc.nextLine();
        return result;
    }
}