import java.util.*;

public class Student {
    private String name;
    private int rollNumber;
    private Map<String, Integer> subjectMarks;
    private int projectMark;
    private double average;
    private String grade;

    // Constructor with parameters
    public Student(String name, int rollNumber, Map<String, Integer> subjectMarks, int projectMark) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = subjectMarks;
        this.projectMark = projectMark;
        this.average = 0.0;
        this.grade = "F";
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public Map<String, Integer> getSubjectMarks() {
        return subjectMarks;
    }

    public int getProjectMark() {
        return projectMark;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
