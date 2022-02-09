import static java.lang.System.in;
import java.util.Scanner;

public class Function {
    private static Scanner sc = new Scanner(System.in);
    private static int[][] matrix1;
    private static int[][] matrix2;   
    private static int[][] result;
    
    //numOfRow, numOfCol, elements must be integer
    public static int getInteger(String msg){
        while (true) {
            try {
                System.out.print(msg);
                int result = Integer.parseInt(sc.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println("Value of matrix is digit");
            }
        }        
    }
    
    public static int getIntegerGraterThan0(String msg){
        while (true) {
            try {
                System.out.print(msg);
                int result = Integer.parseInt(sc.nextLine());
                if (result <= 0){System.out.println("Must be greater than 0"); continue;}
                return result;
            } catch (Exception e) {
                System.out.println("Value of matrix is digit");
            }
        }        
    }
    
    //Require user to input numOfRow, numOFCol and elements of two matrices
    public static int[][] inputMatrix(int matrixN){
        int numOFRow = getIntegerGraterThan0("Enter Row Matrix " + matrixN + ": ");
        int numOFCol = getIntegerGraterThan0("Enter Column Matrix " + matrixN + ": ");
        int[][] matrix = new int[numOFRow][numOFCol];
        //Enter the entry ij
        for (int i = 0; i < numOFRow; i++) {
            for (int j = 0; j < numOFCol; j++) {
                matrix[i][j] = getInteger("Enter Matrix" + matrixN + "[" + i + "]" + "[" + j + "]: ");
            }        
        }
        return matrix;
    }
    //Display matrix in form numOfRow x numOfCol
    public static void displayMatrix(int[][] matrix){
        int numOfRow = matrix.length;
        int numOfCol = matrix[0].length;
        for(int i = 0; i < numOfRow; i++){
            for(int j = 0; j < numOfCol; j++){
                System.out.print("[" + matrix[i][j] + "]");
            }
            System.out.println("");
        }
    }
    //Adding two matrices by adding the corresponding entries together
    public static int[][] additionMatrix(int[][] matrix1, int[][] matrix2){
        int numOfRow1 = matrix1.length;
        int numOfCol1 = matrix1[0].length;
        int numOfRow2 = matrix2.length;
        int numOfCol2 = matrix2[0].length;
        if ((numOfCol1 != numOfCol2) || (numOfRow1 != numOfRow2)) {
            return null;
        }
        int[][] result = new int[numOfRow1][numOfCol1];
        for (int i = 0; i < numOfRow1; i++) {
            for (int j = 0; j < numOfCol1; j++) {
                result[i][j] =  matrix1[i][j] + matrix2[i][j];
            } 
        }    
        return result;
    }
    ////Subtracting two matrices by subtracting the corresponding entries together
    public static int[][] subtractionMatrix(int[][] matrix1, int[][] matrix2){
        int numOfRow1 = matrix1.length;
        int numOfCol1 = matrix1[0].length;
        int numOfRow2 = matrix2.length;
        int numOfCol2 = matrix2[0].length;
        if ((numOfCol1 != numOfCol2) || (numOfRow1 != numOfRow2)) {
            return null;
        }
        int[][] result = new int[numOfRow1][numOfCol1];
        for (int i = 0; i < numOfRow1; i++) {
            for (int j = 0; j < numOfCol1; j++) {
                result[i][j] =  matrix1[i][j] - matrix2[i][j];
            } 
        }    
        return result;        
    }
    
    public static int[][] multiplicationMatrix(int[][] matrix1, int[][] matrix2){
        int numOfRow1 = matrix1.length;
        int numOfCol1 = matrix1[0].length;
        int numOfRow2 = matrix2.length;
        int numOfCol2 = matrix2[0].length;
        if (numOfCol1 != numOfRow2) {
            return null;
        }
        int[][] result = new int[numOfRow1][numOfCol2];
        //Correspondingly multiply entries in one row of matrix 1
        //with entries in one column of matrix 2
        //Choose row ith of first matrix
        for (int i = 0; i < numOfRow1; i++) {
            //Choose column jth of second matrix
            for (int j = 0; j < numOfCol2; j++) {
                //Choose column kth of the first matrix and row kth of the second matrix
                for (int k = 0; k < numOfCol1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
    //return error if two matrix do not have the same size, else return result
    public static void addition(){
        System.out.println("---------- Addition ----------");          
        matrix1 = inputMatrix(1);
        matrix2 = inputMatrix(2);  
        result = additionMatrix(matrix1, matrix2);
        System.out.println("----------- Result -----------");  
        displayMatrix(matrix1);
        System.out.println("+");
        displayMatrix(matrix2);
        System.out.println("=");
        if (result == null) System.out.println("Added fail! Two matrix do not have the same size.");
        else Function.displayMatrix(result);
    }
    
    public static void subtraction(){
        System.out.println("--------- Subtraction ---------");        
        matrix1 = inputMatrix(1);
        matrix2 = inputMatrix(2);  
        result = subtractionMatrix(matrix1, matrix2);
        System.out.println("----------- Result -----------");        
        displayMatrix(matrix1);
        System.out.println("-");
        displayMatrix(matrix2);
        System.out.println("=");        
        if (result == null) System.out.println("Suctracted fail! Two matrix do not have the same size.");
        else    
            Function.displayMatrix(result);
    }   
    
    public static void multiplication(){
        System.out.println("------- Multiplication -------");        
        matrix1 = inputMatrix(1);
        matrix2 = inputMatrix(2);  
        result = multiplicationMatrix(matrix1, matrix2);
         System.out.println("----------- Result -----------");   
        displayMatrix(matrix1);
        System.out.println("*");
        displayMatrix(matrix2);
        System.out.println("=");
        if (result == null) System.out.println("Multiplicated fail! Col 1 does not equal Row 2");
        else 
            Function.displayMatrix(result);
    }      
    
}
