import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class Controller {
    private ArrayList<Account> accountList; 
    private ResourceBundle rb;

    public Controller() {
        this.accountList = new ArrayList<Account>();
    } 

    ArrayList<Account> getList(){
        return this.accountList;
    }

    public void setLocale(Locale locale){
        this.rb = ResourceBundle.getBundle("Language", locale);
    }
    
    public ResourceBundle getBundle(){
        return this.rb;
    }

    boolean checkAccountNumber(String input, ResourceBundle bundle) {
        if (input.matches("[0-9]{10}")) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkPassword(String input, ResourceBundle bundle) {
        if (input.length() >= 8 && input.length() <= 31
                && isAlphanumeric(input)) {
            return true;
        } else {
            return false;
        }
    }
    
    public String generateCaptcha() {
        Random rand = new Random();
        String captcha = "";
        String characterSet = "";
        //Loops to access each character from 'a' to 'z'
        for (char i = 'a'; i <= 'z'; i++) {
            characterSet += i;
        }
        //Loops to access each character from 'A' to 'Z'
        for (char i = 'A'; i <= 'Z'; i++) {
            characterSet += i;
        }
        //Loops to access each character from '0' to '9'
        for (char i = '0'; i <= '9'; i++) {
            characterSet += i;
        }
        //Loops until the captcha is alphanumeric
        while (!isAlphanumeric(captcha)) {
            captcha = "";
            //Loops until captcha length = 5
            while(captcha.length() < 5) {
                captcha += characterSet.charAt(rand.nextInt(characterSet.length()));
            }
        }
        return captcha;
    }
    
    boolean checkCaptcha(String input, String captchaGenerate, ResourceBundle bundle) {
        if (captchaGenerate.contains(input)) {
            return true;
        } else {
            return false;
        }
    }    

    public boolean isAlphanumeric(String input) {
        //Check if input is empty
        if (input.isEmpty()) {
            return false;
        }
        int numOfAlphabet = 0;
        int numOfDigit = 0;
        for (int i = 0; i < input.length(); i++) {
            //Check if character at index i is alphabet
            if ((input.charAt(i) >= 'a' && input.charAt(i) <= 'z')
                    || (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z')) {
                numOfAlphabet++;
                continue;
            }
            //Check if character at index i is digit
            if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                numOfDigit++;
                continue;
            }
            return false;
        }
        return numOfAlphabet > 0 && numOfDigit > 0;
    }    
    
    boolean readFromFile(String filename){
        File f = new File(filename);
        if(!f.exists()) return false;
        try{
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr); 
            String line;    
            while((line = bf.readLine())!=null){
                line = line.trim();
                if (line.length()>0){
                    StringTokenizer stk = new StringTokenizer(line, ";");
                    String accountNumber = stk.nextToken().trim();
                    String password = stk.nextToken().trim();
                    Account c = new Account(accountNumber, password);
                    accountList.add(c);
                }
            }
        }
        catch (Exception e){e.printStackTrace();}
        return true;        
    }    
}
