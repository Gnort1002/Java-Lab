/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class Main {

    public static void main(String[] args) {
        displayMenu();
    }
    
    static void displayMenu(){
        System.out.println("====Reverse Word Program====");
        System.out.println("Input the string to be reversed!");
        String input = Function.getNonBlankStr("Your string: ");
        Function.printReverse(input);
        
    }
}
