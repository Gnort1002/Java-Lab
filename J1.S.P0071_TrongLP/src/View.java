
import java.util.ArrayList;

public class View {

    private Controller c = new Controller();
    
    
    public void display(){       
        while(true) {            
            //display menu
            displayMenu();
            //input the option
            int option = Utility.getInt("Enter option: ", "Must in range [1-4]", "Only integer", 1, 4);
            switch(option){
                case 1:
                    //Create student
                    add();
                    break;
                case 2:
                    //Find and Sort student
                    delete();
                    break;
                case 3:
                    //Update / Delete student
                    getDataTasks();
                    break;
                case 4:
                    //Report student list
                    return;
            }
        }
    }  
    
    public void displayMenu() {
        System.out.println("========= Task program =========");
        System.out.println("        1. Add Task");
        System.out.println("        2. Delete Task");
        System.out.println("        3. Display Task");
        System.out.println("        4. Exit");
    }        
    //add task into list, print error base on exception caught
    public void add() {
        String requirementName = Utility.getString("Requirement Name: ", "", "");
        int taskTypeID = Utility.getInt("Task Type: ", "Must in range [1-4]", "Only integer!", 1, 4);
        String date = Utility.getDate("Date (dd-MM-yyy): ");
        double planFrom = Utility.getDouble("From: ", "Must in range [8.0 -> 17.0]!", 
                8.0, 17.0, "^([89]|1[0-7])\\.[05]", "Must in format 8.0, 8.5, ... 17.5");
        double planTo = Utility.getDouble("To: ", "Must in range [" + (planFrom + 0.5) + " -> 17.5]!", 
                (planFrom + 0.5), 17.5, "^([89]|1[0-7])\\.[05]", "Must in format 8.0, 8.5, ... 17.5");    
        String assignee = Utility.getString("Assignee: ", "", "");
        String reviewer = Utility.getString("Reviewer: ", "", "");
        try{
            int ID = c.addTask(requirementName, assignee, reviewer, 
                    taskTypeID, date, planFrom, planTo);
            if (ID == 0) System.out.println("Task existed!");
            else System.out.println("Add successfully!");
        }
        catch (Exception e){
            if (e instanceof NullPointerException)
                System.err.println("Null Pointer Error! Failed to add!");
            if (e instanceof NumberFormatException)
                System.err.println("Number Format Error! Failed to add!");
        }
    }
    //print error if catch exception
    public void delete() {
       String ID = Utility.getString("ID: ", "", "");
       try{
           c.deleteTask(ID);
           System.out.println("Delete successfully!");
       }
       catch (Exception e){
            System.out.println("ID is not existed! Failed to delete!");
            return;
       } 
    }
    //display task;s infomation
    public void getDataTasks() {
        ArrayList<Task> taskList = c.getList();
        if (taskList.isEmpty()) {
            System.out.println("List task is empty!");
            return;
        } else{
            System.out.format("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n", "Id", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
            //loop use to access each element of arraylist from begining to the end
            for (Task task : taskList) {
                System.out.println(task);
            }
        }
    }        
        
}



