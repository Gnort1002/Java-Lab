import java.io.*;
import java.util.*;
import java.lang.*;

public class Function {
    public static Scanner sc = new Scanner(System.in);
    //input until get non-black string
    public static String getNonBlankStr(String msg) {
        String result;
        do{
            System.out.print(msg);
            result = sc.nextLine().trim();
            if (result.length() == 0){ System.out.println("Must be not blank!"); continue;}
            return result;
        }
        while(true);     
    }
    
    public static void printReverse(String input){
        int len = input.length();
        //String contain only 1 word.
        if (input.split(" ").length == 1) {System.out.println(input.substring(0, 1).toUpperCase() + input.substring(1, len)); return;}
        StringBuilder result = new StringBuilder();
        StringBuilder punctuation = new StringBuilder("");
        ArrayList<String> arrayOfToken = new ArrayList<String>();
        ArrayList<String> arrayOfReversedWord = new ArrayList<String>();
        ArrayList<Integer> arrayOfStatus = new ArrayList<Integer>();
        //Split string by " "
        StringTokenizer stk = new StringTokenizer(input, " ");
        String sample = ".!?";
        String pattern, temp, lastPunctuation = "";
        int current;
        //Loop until reach the last token
        while(stk.hasMoreElements()){
            pattern = stk.nextToken();
            len = pattern.length();
            //"..." is not a punctuation
            if (pattern.endsWith("...")){
                arrayOfToken.add(pattern);
                arrayOfStatus.add(0);
            }
            //get punctuation from word
            else if (pattern.endsWith("!") || pattern.endsWith(".") || pattern.endsWith("?")){
                while (pattern.endsWith("!") || pattern.endsWith(".") || pattern.endsWith("?")){
                    //punctuation will be add at the end "punctuation" 
                    punctuation.append(pattern.substring(len-1, len));
                    pattern = pattern.substring(0, len-1);
                    len--;
                }
                //flag word with no punctation by 1 and punctuation by 0
                arrayOfToken.add(pattern.substring(0, 1).toUpperCase() + pattern.substring(1, len));
                arrayOfStatus.add(0);
                arrayOfToken.add(punctuation.reverse().toString()); 
                arrayOfStatus.add(1);
                punctuation = new StringBuilder("");
            }
            else{
                arrayOfToken.add(pattern);
                arrayOfStatus.add(0);
            }
        }
        len = arrayOfToken.size();
        temp = "";

        for (int i = len-1; i >0; i--) {
            if (arrayOfStatus.get(i) == 0){
                while(i >= 0 && arrayOfStatus.get(i) == 0){
                    arrayOfReversedWord.add(arrayOfToken.get(i));
                    arrayOfReversedWord.add(" ");
                    i--;
                }
                arrayOfReversedWord.remove(arrayOfReversedWord.size()-1);
                arrayOfReversedWord.add("");
                arrayOfReversedWord.add(" ");
            }
            else continue;
        }
        int i  = 0;
        while(i<arrayOfToken.size()){
            for(int j = 0; j < arrayOfReversedWord.size(); j++){
                if (j>= 0 && arrayOfReversedWord.get(j).equals("") && i<arrayOfToken.size()){
                    if (i<arrayOfToken.size() && arrayOfStatus.get(i) == 0){
                        while (i<arrayOfToken.size() && arrayOfStatus.get(i) == 0){ i++; j--;}
                    }
                    else {
                            arrayOfReversedWord.remove(j);
                            arrayOfReversedWord.add(j, arrayOfToken.get(i));
                            i++;
                        }
                    }
                }
            }
        temp =  arrayOfReversedWord.get(0);
        result.append(temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()));
        for (int k = 1; k < arrayOfReversedWord.size(); k++) {
            result.append(arrayOfReversedWord.get(k));
        }
        System.out.println(result.toString());
    }

}
