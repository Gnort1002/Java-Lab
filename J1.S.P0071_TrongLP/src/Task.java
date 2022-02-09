import java.util.Date;

public class Task {
    private Controller c = new Controller();
    private int ID;
    private int taskTypeID;
    private String requirementName;
    private String assignee;
    private String reviewer;
    private String date;
    private double planFrom;
    private double planTo;

    public Task() {
        
    }

    public Task(int ID, int taskTypeID, String requirementName, String assignee, 
            String reviewer, String date, double planFrom, double planTo) {
        this.ID = ID;
        this.taskTypeID = taskTypeID;
        this.requirementName = requirementName;
        this.assignee = assignee;
        this.reviewer = reviewer;
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTaskTypeID() {
        return taskTypeID;
    }

    public void setTaskTypeID(int taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPlanFrom() {
        return planFrom;
    }

    public void setPlanFrom(double planFrom) {
        this.planFrom = planFrom;
    }

    public double getPlanTo() {
        return planTo;
    }

    public void setPlanTo(double planTo) {
        this.planTo = planTo;
    }
    
    public double getTime(){
        return this.getPlanTo() - this.getPlanFrom();
    }
    
    public String toString() {
        double time = planTo - planFrom;
        String planTime = String.format("%.1f", time);
        String taskType = "";
        switch(taskTypeID){
            case 1: taskType = "Code"; break;
            case 2: taskType = "Test"; break;
            case 3: taskType = "Design"; break;
            case 4: taskType = "Review"; break;
        }
        String result = String.format("%-7d%-20s%-12s%-15s%-7s%-15s%-15s", ID, requirementName, taskType, date, planTime, assignee, reviewer );
        return result;
    }    
}
