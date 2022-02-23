import java.util.ArrayList;

public class Controller {
    private final ArrayList<Task> taskList;

    public Controller() {
        taskList = new ArrayList<>();
    }
    //return Task list
    public ArrayList<Task> getList(){
        return this.taskList;
    }
    //find the Task base on input ID
    public Task findTaskByID(String id){
        try{
            int ID = Integer.parseInt(id);
            for (Task task: taskList){
                if (task.getID() == ID){
                    return task;
                }
            }
            
        }
        catch (Exception e){
            return null;
        }
        return null;
    }
    //check if a task is existed in the list
    public boolean checkTaskExist(Task t) {
        //first to the last in studentList
        for (Task task : taskList) {
            //check if student s is in the list 
            if (task.getTaskTypeID() == t.getTaskTypeID()
                    && task.getRequirementName().equals(t.getRequirementName())
                    && task.getAssignee().equals(t.getAssignee())
                    && task.getDate().equals(t.getDate())
                    && task.getReviewer().equals(t.getReviewer())
                    && task.getPlanFrom() == t.getPlanFrom()
                    && task.getPlanTo() == t.getPlanTo()) {
                return true;
            }
        }
        return false;
    } 
    //add Task into list, throw exception if catch any error
    public int addTask(String requirementName, String assignee, String reviewer,
        int taskTypeID, String date,double planFrom, double planTo) throws Exception{       
        int ID;
        if (taskList.isEmpty()) ID = 1;
        //New ID = max ID + 1
        else ID = taskList.get(taskList.size()-1).getID() + 1; 
        Task newTask = new Task(ID, taskTypeID, requirementName, 
                assignee, reviewer, date, planFrom, planTo);
        if (taskList.isEmpty()){
            taskList.add(newTask);
            return ID;
        }
        else if(checkTaskExist(newTask)) return 0;
        else {
            taskList.add(newTask);
            return ID;
        }
    }
    //delete existed task, throw exception if not exist
    public void deleteTask(String id) throws Exception{
        Task deletedTask = findTaskByID(id);
        taskList.remove(taskList.indexOf(deletedTask));
    }
    
}
