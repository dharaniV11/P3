import java.util.*;

public class Student {
    private String name;
    private int rollNumber;
    private Map<String, Integer> subjectMarks;
    private int projectMark;

    // Constructor with parameters
    public Student(String name, int rollNumber, Map<String, Integer> subjectMarks, int projectMark) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = subjectMarks;
        this.projectMark = projectMark;
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
}
