package dz4;

import java.util.TreeSet;

public class Student implements Comparable<Student>{

    private int grade;
    private String Name;

    public Student(int grade, String Name){
        this.Name=Name;
        this.grade=grade;
    }
    int getStudentGrade(){
        return grade;
    }

    String getStudentName(){
        return Name;
    }


    public int compareTo(Student s){
        if (grade>s.getStudentGrade()){
            return 1;
        }
        else if (grade<s.getStudentGrade()){
            return -1;
        }
        else {
            return Name.compareTo(s.getStudentName());
        }

    }

    @Override
    public String toString() {
        return "Student: " + grade +
                " " + Name;
    }

    public static void main(String args[]){
        TreeSet<Student> students = new TreeSet<Student>();
        students.add(new Student(11,"Nikita"));
        students.add(new Student(1,"Anna"));
        students.add(new Student(3,"Sereja"));
        students.add(new Student(11,"Elizaveta"));
        students.add(new Student(11,"Anna"));
        students.add(new Student(10,"Nikita"));
        students.add(new Student(7,"Misha"));
        students.add(new Student(6,"Suzanna"));
        students.add(new Student(7,"Veronika"));
        students.add(new Student(6,"Janna"));
        int grade =0;

        for (Student s:students){
            if (s.getStudentGrade() != grade){
                System.out.println("==========================");
                grade=s.getStudentGrade();
            }
            System.out.println(s);
        }

    }


}
