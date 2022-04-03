package java_advanced_training.university;

import java.util.ArrayList;
import java.util.List;

public class University {
    public int capacity;
    public List<Student> students;

    public University(int capacity) {
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getStudents() {
        return students;
    }
    public int getStudentCount(){
        return this.students.size();
    }
    public String registerStudent(Student student){

        if (capacity > students.size()){
            if (this.students.contains(student)){
                return "Student is already in the java_advanced_training.university";
            } else {
                this.students.add(student);
                return String.format("Added student %s %s", student.getFirstName(), student.getLastName());
            }
        } else {
            return "No seats in the java_advanced_training.university";
        }
    }

    public String dismissStudent(Student student){

        if (this.students.contains(student)) {
            this.students.remove(student);
            return String.format("Removed student %s %s",student.getFirstName(), student.getLastName());
        } else {
            return "Student not found";
        }
    }
    public Student getStudent(String firstName, String lastName){

        return this.students.stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findFirst().get();

    }
    public String getStatistics(){
        StringBuilder builder = new StringBuilder();

                students.forEach(student -> builder.append("==Student: First Name = ").append(student.getFirstName()).append(", ")
                        .append("Last Name = ").append(student.getLastName()).append(", ")
                        .append("Best Subject = ").append(student.getBestSubject()).append(System.lineSeparator()));

        return builder.toString();

    }

}
//==Student: First Name = {firstName}, Last Name = {lastName}, Best Subject = {bestSubject}
//"==Student: First Name = {firstName}, Last Name = {lastName}, Best Subject = {bestSubject}
