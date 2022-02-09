import java.util.ArrayList;

public class Controller {
    private final ArrayList<Task> taskList;

    public Controller() {
        taskList = new ArrayList<>();
    }
    
    public ArrayList<Task> getList(){
        return this.taskList;
    }

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
    public boolean checkTaskExist(Task t) {
        //first to the last in studentList
        for (Task task : taskList) {
            //check whether student s is in the list 
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
    
    public int addTask(String requirementName, String assignee, String reviewer,
        int taskTypeID, String date,double planFrom, double planTo) throws Exception{       
        int ID;
        if (taskList.isEmpty()) ID = 1;
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
    
    public void deleteTask(String id) throws Exception{
        Task deletedTask = findTaskByID(id);
        taskList.remove(taskList.indexOf(deletedTask));
    }
    
}
