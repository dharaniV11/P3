import java.util.*;

public class Student implements Gradable {
    private String name;
    private int rollNumber;
    private Map<String, Integer> subjectMarks;
    private int projectMark;

    public Student(String name, int rollNumber, Map<String, Integer> subjectMarks, int projectMark) throws InvalidMarksException {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = new HashMap<>();
        this.projectMark = projectMark;

        for (Map.Entry<String, Integer> entry : subjectMarks.entrySet()) {
            int mark = entry.getValue();
            if (mark < 0 || mark > 100) {
                throw new InvalidMarksException("Marks for " + entry.getKey() + " are out of range (0–100).");
            }
            this.subjectMarks.put(entry.getKey(), mark);
        }
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
        int total = 0;
        for (int mark : subjectMarks.values()) {
            total += mark;
        }
        // normalize project mark (0-10) to scale of 100
        total += (projectMark * 10);
        return (double) total / (subjectMarks.size() + 1);
    }

    public String calculateGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        else if (avg >= 75) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 40) return "D";
        else return "F";
    }
}
