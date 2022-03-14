package dz4;

import java.util.TreeSet;

public class Student_list implements Comparable<Student_list>{
    private String info;

    public Student_list(String info){
        this.info=info;
    }

    String getStudentName(){return info.replaceAll("\\d+ ", "");}
    String getStudentGrade(){return info.replaceAll("\\D+","");}

    public int compareTo(Student_list s){
        int grade = Integer.parseInt(info.replaceAll("\\D+",""));
        if (grade > Integer.parseInt(s.getStudentGrade())){
            return 1;
        }
        else if (grade < Integer.parseInt(s.getStudentGrade())){
            return -1;
        }
        else {
            return info.replaceAll("\\d+ ", "").compareTo(s.getStudentName());
        }

    }

    @Override
    public String toString() {
        return getStudentName();
    }

    public static void main(String args[]){
        TreeSet<Student_list> students = new TreeSet<Student_list>();
        students.add(new Student_list("11 Nikita"));
        students.add(new Student_list("11 Aleksander"));
        students.add(new Student_list("6 Nikita"));
        students.add(new Student_list("6 Nikita"));
        students.add(new Student_list("5 Aleksander"));
        students.add(new Student_list("7 Natataliya"));
        students.add(new Student_list("4 Sereja"));
        students.add(new Student_list("3 Sereja"));
        students.add(new Student_list("11 Elizaveta"));
        students.add(new Student_list("6 Anna"));
        students.add(new Student_list("7 Nikita"));
        students.add(new Student_list("11 Misha"));
        students.add(new Student_list("1 Suzanna"));
        students.add(new Student_list("2 Veronika"));
        students.add(new Student_list("11 Janna"));
        students.add(new Student_list("10 Derjava"));
        int grade = 0;

        for (Student_list s:students){
            int studentsGrade = Integer.parseInt(s.getStudentGrade());
            if (grade != studentsGrade) {
                System.out.printf("___________Class %s%n",studentsGrade);
                grade=studentsGrade;
            }
            System.out.println(s);
        }

    }


}
