import java.util.*;

public class Student implements Gradable {
    private String name;
    private int rollNumber;
    private Map<String, Integer> subjectMarks;

    public Student(String name, int rollNumber, Map<String, Integer> subjectMarks) throws InvalidMarksException {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = new HashMap<>();

        for (Map.Entry<String, Integer> entry : subjectMarks.entrySet()) {
            int mark = entry.getValue();

            try {
                if (mark < 0 || mark > 100) {
                    throw new InvalidMarksException("Marks for " + entry.getKey() + " are out of range (0–100).");
                }
                this.subjectMarks.put(entry.getKey(), mark);
            } catch (InvalidMarksException e) {
                throw e;
            }
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

    public double getAverage() {
        int total = 0;
        for (int mark : subjectMarks.values()) {
            total += mark;
        }
        return subjectMarks.size() > 0 ? (double) total / subjectMarks.size() : 0;
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
