import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class View {

    private Controller controller = new Controller();
    
    
    public void display(){       
        while(true) {            
            //display menu
            displayMenu();
            //input the option
            int option = Utility.getInt("Enter option: ", "Must in range [1-5]", "Only integer", 1, 5);
            switch(option){
                case 1:
                    //Create student
                    create();
                    break;
                case 2:
                    //Find and Sort student
                    findAndSort();
                    break;
                case 3:
                    //Update / Delete student
                    updateOrDelete();
                    break;
                case 4:
                    //Report student list
                    report();
                    break;
                case 5:
                    return;
            }
        }
    }
    

    public void displayMenu() {
        System.out.println("=============================");
        System.out.println("WELCOME TO STUDENT MANAGEMENT");
        System.out.println("        1. Create");
        System.out.println("        2. Find and Sort");
        System.out.println("        3. Update/Delete");
        System.out.println("        4. Report");
        System.out.println("        5. Exit");
        System.out.println("(Please choose 1 to Create, 2 to Find and Sort, "
                + "3 to Update/Delete, 4 to Report and 5 to Exit program).");
    }    
    
    public void create(){ 
        int count = 0;
        // Loops to add student until user want to stop
        while (true) {
            String choice = Utility.getPatternStr("Do you want to randomly get student's information (Y/N)?  ", 
                    "Only (Y/N)", "[YN]");
            if (choice.equals("Y")){
                for (int i = 0; i < 10; i++) {
                    Student newStudent = getRandomStudent();
                    if (controller.checkIdExist(newStudent.getID())){i--;}
                    else if (controller.checkStudentExist(newStudent)){i--;}
                    else{
                        controller.addStudent(newStudent);
                        count++;
                        if (count >= 10) break;
                    }
                }
            }    
            else{            
                String ID = getInformation("ID");
                String studentName;
                //Check if ID exist
                if (!controller.checkIdExist(ID)) {
                    studentName = getInformation("name");
                } else {
                    studentName = controller.getStudentNameByID(ID);
                    System.out.println("Student name: " + studentName);
                }
                
                String semester = getInformation("semester");

                String courseName = getInformation("course");
                Student s = new Student(ID, studentName, semester, courseName);
                //Check if student is not exist
                if (!controller.checkStudentExist(s)) {
                    controller.addStudent(s);
                    System.out.println("Successfully add student " + controller.getListStudent().size());
                    count++;
                } else {
                    System.out.println("Student's information is existed");
                    continue;
                }
            }
            //Check if count >= 5
            if (count >= 10) {
                //the regex require string must be "Y" or "N"
                     choice = Utility.getPatternStr("Do you want to "
                        + "continue create new student (Y/N)? ", "Invalid choice", "[YN]");
                //Check if choice equal "N"
                if (choice.equals("N")) {
                    break;
                }
            }
        }
        displayStudentList(controller.getListStudent());        
    }
    
    public void findAndSort(){
        ArrayList<Student> studentList = controller.getListStudent();
        // check if studentList is empty
        if (studentList.isEmpty()) {
            System.out.println("The student list is empty");
            return;
        }
        
        String name = Utility.getPatternStr("Enter student name to search: ", "Name invalid", "[a-zA-Z ]+");
        ArrayList<Student> findList = controller.getListStudentByName(name);
        
        // check if findList is empty
        if (findList.isEmpty()) {
            System.out.println("Didn't find any students");
            return;
        } else {
            controller.sortList(findList);
            System.out.format("%-20s%-20s%-20s\n", "Student name",
                    "Semester", "Course name");
            //Loops to access each student from the first to the last in findList
            for (Student student : findList) {
                System.out.format("%-20s%-20s%-20s\n", student.getStudentName(),
                        student.getSemester(), student.getCourseName());
            }
        }        
    }
    
    public void updateOrDelete(){
        ArrayList<Student> studentList = controller.getListStudent();
        if (studentList.isEmpty()) {
            System.out.println("The student list is empty");
            return;
        }
        String ID;
        // Loops to validate the ID
        while (true) {
            ID = getInformation("ID");
            if (!controller.checkIdExist(ID)) {
                System.out.println("The student ID is not exist");
            } else {
                break;
            }
        }
        ArrayList<Student> findList = new ArrayList<>();

        for (Student student : studentList) {
            if (student.getID().equals(ID)) {
                findList.add(student);
            }
        }
        displayStudentIndexList(findList);
        //the regex require string must be "U" or "D"
        String choice = Utility.getPatternStr("Do you want to update "
                + "(U) or delete (D) student? ", "Choice invalid", "[UD]");

        if (choice.equals("D")) {
            int index = Utility.getInt("Enter index to delete: ",
                    "Out of range", "Only", 1, findList.size());
            Student selectStudent = findList.get(index - 1);
            controller.removeStudent(selectStudent);
            System.out.println("Delete successfully");
            displayStudentList(studentList);
        } else {
            int index = Utility.getInt("Enter index to update: ",
                    "Out of range", "Only integer", 1, findList.size());
            Student selectedStudent = findList.get(index - 1);
            Student newStudent = update(selectedStudent);       

            if (controller.checkStudentExist(newStudent)) {
                System.out.println("Student is exist");
                return;
            }

            if (controller.checkIdExist(newStudent.getID())) {
                for (Student student : studentList) {
                    //check if accessing student has id equal to newID
                    if (student.getID().equals(newStudent.getID())) {
                        student.setStudentName(newStudent.getStudentName());
                    }
                }
            }
            selectedStudent.setID(newStudent.getID());
            selectedStudent.setStudentName(newStudent.getStudentName());
            selectedStudent.setCourseName(newStudent.getCourseName());
            selectedStudent.setSemester(newStudent.getSemester());
            System.out.println("Update successful");
            displayStudentList(controller.getListStudent());
        }        
    }
    
    public void report() {
        ArrayList<Student> studentList = controller.getListStudent();
            // check if studentList is empty
            if (studentList.isEmpty()) {
                System.out.println("The student list is empty");
                return;
            }
            System.out.println("Student name | Course | Total of course");
            ArrayList<String> listID = controller.getUniqueIDList(studentList);
            //Loops to access each student in studentList from begin to end
            //Loops to access each ID in listID from begin to end
            int[] arrOfFrequency = new int[3];
            for (String ID : listID) {
                arrOfFrequency = controller.getFrequencyOfCourses(ID, studentList);
                //Check if countJava > 0
                if (arrOfFrequency[0] > 0) {
                    System.out.println(controller.getStudentNameByID(ID) + " | Java | " + arrOfFrequency[0]);
                }
                //Check if countNet > 0
                if (arrOfFrequency[1] > 0) {
                    System.out.println(controller.getStudentNameByID(ID) + " | .Net | " + arrOfFrequency[1]);
                }
                //Check if countC > 0
                if (arrOfFrequency[2]> 0) {
                    System.out.println(controller.getStudentNameByID(ID) + " | C/C++ | " + arrOfFrequency[2]);
                }
            }
        }    

    public static Student getRandomStudent(){
        return new Student(Utility.getRandomID(), 
                           Utility.getRandomName(),
                           Utility.getRandomSemester(),
                           Utility.getRandomCourse());
    }

    public void displayStudentIndexList(ArrayList<Student> findList){
        System.out.format("%-10s%-10s%-20s%-20s%-20s\n", "Index", "ID",
                "Student name", "Semester", "Course name");
        
        for (int i = 0; i < findList.size(); i++) {
            System.out.format("%-10s%-10s%-20s%-20s%-20s\n", i + 1, findList.get(i).getID(),
                    findList.get(i).getStudentName(), findList.get(i).getSemester(),
                    findList.get(i).getCourseName());
        }        
    }

    public void displayStudentList(ArrayList<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("The student list is empty");
            return;
        }
        System.out.format("%-20s%-20s%-20s%-20s\n", "Student ID",
                "Student name", "Semester", "Course name");
        for (Student student : studentList) {
            System.out.format("%-20s%-20s%-20s%-20s\n",
                    student.getID(), student.getStudentName(),
                    student.getSemester(), student.getCourseName());
        }
    }      

    public int getChoice(){
        String[] arrayString = {"Choose information you want to update!", "1. Student's name", "2. Student's semester ", "3. student's Course"};
        int option = Utility.getChoiceInArray(arrayString);
        return option;
    }
    public String getInformation(String type){
        String input = "";
        if (type.equals("ID")){
            // the regex require a string in form (HE000000)
            input = Utility.getPatternStr("Enter ID: ",
                    "Wrong format of ID", "[H][E][0-9]{6}");
        }
        else if (type.equals("name")){
            // the regex require string contains only alphabet and space
            input = Utility.getPatternStr("Enter "
                    + "student name: ", "Name invalid", "[a-zA-Z ]+");
        }
        // the regex require string contains only alphabet and digit
        else if (type.equals("semester")){
            input = Utility.getPatternStr("Enter"
                    + " semester: ", "Semester invalid", "[a-zA-Z0-9]+");
        }
        // the regex require string must be "Java", ".Net" or "C/C++"
        else if (type.equals("course")) {
            input = Utility.getPatternStr("Enter course name: ",
                    "Course not exist.", "Java|[.]Net|C[/]C[+][+]");
        }
        return input;  
    }
    
    public Student update(Student selectedStudent){
        Student newStudent = selectedStudent;       
        String choice = Utility.getPatternStr("Do you want to update selected student's ID? (Y/N) ", 
                "Only (Y/N)", "[YN]");
        if (choice.equals("Y")) {String updateID = getInformation("ID"); newStudent.setID(updateID);}
        choice = Utility.getPatternStr("Do you want to update selected student's name? (Y/N) ", 
                "Only (Y/N)", "[YN]");
        if (choice.equals("Y")) {String updateName = getInformation("Name"); newStudent.setID(updateName);}
        choice = Utility.getPatternStr("Do you want to update selected student's semester? (Y/N) ", 
                "Only (Y/N)", "[YN]");
        if (choice.equals("Y")) {String updateSemester = getInformation("semester");newStudent.setID(updateSemester);}
        choice = Utility.getPatternStr("Do you want to update selected student's course? (Y/N) ", 
                "Only (Y/N)", "[YN]");
        if (choice.equals("Y")) {String updateCourse = getInformation("course");newStudent.setID(updateCourse);}        
        return newStudent;
    }     
}
