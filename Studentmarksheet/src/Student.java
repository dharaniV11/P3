import java.util.*;

public class Student {
    private String name;
    private int rollNumber;
    private Map<String, Integer> subjectMarks;
    private int projectMark;

    // Default constructor for JavaBean compatibility
    public Student() {
        this.subjectMarks = new HashMap<>();
    }

    // Constructor with parameters
    public Student(String name, int rollNumber, Map<String, Integer> subjectMarks, int projectMark) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = subjectMarks;
        this.projectMark = projectMark;
    }

    // Getter and Setter methods for JavaBean compatibility
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Map<String, Integer> getSubjectMarks() {
        return subjectMarks;
    }

    public void setSubjectMarks(Map<String, Integer> subjectMarks) {
        this.subjectMarks = subjectMarks;
    }

    public int getProjectMark() {
        return projectMark;
    }

    public void setProjectMark(int projectMark) {
        this.projectMark = projectMark;
    }
}
