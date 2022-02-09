
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Controller {
    private ArrayList<Student> studentList;
    
    
    public Controller() {
        studentList = new ArrayList<>();
    }
    
    public void addStudent(Student s){
        studentList.add(s);
    }
 
    public void removeStudent(Student s){
        studentList.remove(s);
    }
    
    public ArrayList<Student> getListStudent(){
        return studentList;
    }
    
    public boolean checkStudentExist(Student s) {
        //first to the last in studentList
        for (Student student : studentList) {
            //check whether student s is in the list 
            if (student.getID().equals(s.getID())
                    && student.getSemester().equals(s.getSemester())
                    && student.getCourseName().equals(s.getCourseName())) {
                return true;
            }
        }
        return false;
    }           

    public boolean checkIdExist(String ID) {
        for (Student student : studentList) {
            // Check if ID of accessing student is equal to the input ID
            if (student.getID().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public String getStudentNameByID(String ID) {
        for (Student student : studentList) {
            // Check if ID of accessing student is equal to the input ID
            if (student.getID().equals(ID)) {
                return student.getStudentName();
            }
        }
        return null;
    }
    
    public ArrayList<Student> getListStudentByName(String name){
        ArrayList<Student> findList = new ArrayList<>();
        for (Student student : studentList) {
            //Check if student name of accessing student contains search name
            if (student.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                findList.add(student);
            }
        } 
        return findList;
    }
    
    public void sortList(ArrayList<Student> findList){
        Collections.sort(findList, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
//                    return o1.getReversedName().compareTo(o2.getReversedName());
                    return o1.getReversedName().compareTo(o2.getReversedName());
                }
            });        
    }
    
    public ArrayList<String> getUniqueIDList(ArrayList<Student> studentList){
        ArrayList<String> listID = new ArrayList<>();
        //Loops to access each student in studentList from begin to end
        for (Student student : studentList) {
            boolean isInListID = false;
            //Loops to access each ID in listID from begin to end
            for (String ID : listID) {
                //Check if ID is equal to the ID of student
                if (ID.equals(student.getID())) {
                    isInListID = true;
                    break;
                }
            }
            //Check if isInListID is false
            if (!isInListID) {
                listID.add(student.getID());
            }
        }
        return listID;       
    }
    
    public int[] getFrequencyOfCourses(String ID, ArrayList<Student> studentList){
            int[] arrayFrequency = new int[3];
            int countJava = 0, countNet = 0, countC = 0;
            //Loops to access each student in studentList from begin to end
            for (Student student : studentList) {
                //Check if ID is equal to the ID of student
                if (ID.equals(student.getID())) {
                    //Check if course name is "Java"
                    if (student.getCourseName().equals("Java")) {
                        countJava++;
                    }
                    //Check if course name is ".Net"
                    if (student.getCourseName().equals(".Net")) {
                        countNet++;
                    }
                    //Check if course name is "C/C++"
                    if (student.getCourseName().equals("C/C++")) {
                        countC++;
                    }
                }
            }
            arrayFrequency[0] = countJava;
            arrayFrequency[1] = countNet;
            arrayFrequency[2] = countC;
            return arrayFrequency;
    }

}
