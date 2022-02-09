
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ReadWriteFile {
    public boolean loadFromFile(String filename) {
        File f = new File(filename); //checking existence of the file
        if(!f.exists()) return false;    
        try{
            FileReader fr = new FileReader(f); //open file for reading chars
            BufferedReader bf = new BufferedReader(fr); //for reading lines        
            String line; //a line will be read from file
            //while still reading successfully a line from file
            while((line = bf.readLine())!=null){
                //Process this line, format: P006, SharpTv 40, 210
                line = line.trim();
                if (line.length()>0){//extracting 3 part
                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String ID = stk.nextToken().trim();
                    String name = stk.nextToken().trim();
                    int price = Integer.parseInt(stk.nextToken().trim());
//                    Object p = new Product(ID, name, price);
//                    this.add(p);
                }
            }
        }//All file contents are processed
        catch (Exception e){System.out.println(e);}
        return true;
    }

    public boolean saveToFile(String filename) {
        try{
            FileWriter fw = new FileWriter(filename);
            PrintWriter pw = new PrintWriter(fw); //for writing lines
            //Writing product to file
//            for (Product p : this) pw.println(p);
            pw.close();
            fw.close();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }    
}
