public class Random {
    public static int getRandomInt(){
        java.util.Random rand = new java.util.Random();
        return rand.nextInt(Integer.MAX_VALUE);
    }
    public static int getRandomInRange(int min, int max){
        java.util.Random rand = new java.util.Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    public static String getRandomInRange2(int min, int max){
        java.util.Random rand = new java.util.Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        if (0<=randomNum && randomNum< 10) return "00" + randomNum;
        if (10<=randomNum && randomNum< 100) return "0" + randomNum;
        return "" + randomNum;
    }

    public static boolean getRandomBo(){
        java.util.Random rand = new java.util.Random();
        return rand.nextBoolean();
    }    
//    public  static  int gerRandomIntInRange2(int min, int max){
//        
//    }
    public static String getRandomChars(int numChars){
        final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final int N = alphabet.length();
        String result = "";
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < numChars; i++){
          result += alphabet.charAt(random.nextInt(N));
        }
        return result;
    }
    
    public static String[] getRandomWords(int numberOfWords){
        String[] randomStrings = new String[numberOfWords];
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < numberOfWords; i++){
            char[] word = new char[random.nextInt(8)+3];
            for (int j = 0; j < word.length; j++){
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }
    
    public static String getRandomColor(){
        final String[] listColors = {"Black", "White", "Gray", "Silver", "Maroon", "Red", "Purple", "Fushsia", "Green", "Lime", "Olive", "Yellow", "Navy", "Blue", "Teal", "Aqua"};
        return listColors[getRandomInRange(0, listColors.length-1)];
    }
    
    public static String getRandomMaterial(){
        final String[] listMaterials = {"Ceramics", "Glass", "Aluminium", "Brass", "Bronze", "Wood"};
        return listMaterials[getRandomInRange(0, listMaterials.length-1)];
    }
    
    public static String getRandomFirstName(){
        final String[] FirstNames = {"Le", "Dao", "Pham", "Tran", "Nguyen", "Hoang", "An", "Chu", "Bui"};
        return FirstNames[getRandomInRange(0, FirstNames.length-1)];
    }
    
    public static String getRandomLastName(){
        final String[] LastNames = {"Trong", "Hung", "Viet", "Trang", "Hoa", "Nga", "Ha", "Huy", "Anh"};
        return LastNames[getRandomInRange(0, LastNames.length-1)];
    }
    
    public static String getRandomName(){
        return getRandomFirstName() + " " + getRandomLastName();
    }
    
    public static String getRandomID(){
        return "P" + getRandomInRange2(0, 999);
    }    
}
