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
    //return list of accounts
    ArrayList<Account> getList(){
        return this.accountList;
    }
    //change the language
    public void setLocale(Locale locale){
        this.rb = ResourceBundle.getBundle("Language", locale);
    }
    //return resource bundle to print out wanted language
    public ResourceBundle getBundle(){
        return this.rb;
    }
    //return error String if account number
    //does not have 10 digits
    public String checkAccountNumber(String input) {
        if (input.matches("[0-9]{10}")) {
            return null;
        } else {
            return rb.getString("AccNumErr");
        }
    }
    //Check if password is alphanumeric and has length between 8 and 31
    //return error String if false
    public String checkPassword(String input) {
        if  (input.matches("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,31})$")) {
            return null;
        } else {
            return rb.getString("PasswordErr");
        }
    }
    //generate random captcha
    public String generateCaptcha() {
        Random rand = new Random();
        StringBuilder captcha = new StringBuilder();
        StringBuilder characterSet = new StringBuilder();
        //Loops to access each character from 'a' to 'z'
        for (char i = 'a'; i <= 'z'; i++) {
            characterSet.append(i + "");
        }
        //Loops to access each character from 'A' to 'Z'
        for (char i = 'A'; i <= 'Z'; i++) {
            characterSet.append(i + "");
        }
        //Loops to access each character from '0' to '9'
        for (char i = '0'; i <= '9'; i++) {
            characterSet.append(i + "");
        }
            //Loops until captcha length = 5
            while(captcha.length() < 5) {
                captcha.append(characterSet.charAt(rand.nextInt(characterSet.length())));
            }
        return captcha.toString();
    }
    //Check if input captcha contains at least one letter from generated captcha
    public String checkCaptcha(String input, String captchaGenerate) {//String
        if (input.length() > 0 && captchaGenerate.contains(input)) {
            return null;
        } else {
            return rb.getString("CaptchaErr");
        }
    }    
    
    public boolean readFromFile(String filename){
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
