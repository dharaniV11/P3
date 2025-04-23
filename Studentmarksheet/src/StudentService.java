import java.util.*;
import java.util.logging.*;

public class StudentService implements Gradable {
    Logger logger = Logger.getLogger(StudentService.class.getName());
    private Map<Integer, Student> students;
    private Scanner scanner;

    public StudentService(Map<Integer, Student> students, Scanner scanner) {
        this.students = students;
        this.scanner = scanner;
    }

    public void addStudent() {
        try {
            String name = getValidName();
            int rollNumber = getUniqueRollNumber();

            String[] subjects = { "English", "Tamil", "Maths", "Science", "Social" };
            Map<String, Integer> marks = new HashMap<>();

            for (String subject : subjects) {
                int mark = getValidMark(subject);
                marks.put(subject, mark);
            }

            int projectMark = getValidProjectMark();

            Student student = new Student(name, rollNumber, marks, projectMark);

            double avg = getAverage(student);
            String grade = calculateGrade(student);

            student.setAverage(avg);
            student.setGrade(grade);

            students.put(rollNumber, student);
            logger.info("Student added successfully!");

        } catch (Exception e) {
            logger.warning("An error occurred: " + e.getMessage());
        }
    }

    private String getValidName() {
        String name;
        while (true) {
            logger.info("Enter student name: ");
            name = scanner.nextLine().trim();
            if (name.matches("[A-Za-z\\s]+")) break;
            else{
                logger.severe("Invalid name. Name cannot be empty or numeric only.");
            }
        }
        return name;
    }

    private int getUniqueRollNumber() {
        int rollNumber;
        while (true) {
            logger.info("Enter roll number: ");
            rollNumber = getIntInput();
            if (students.containsKey(rollNumber)) {
                logger.warning("Roll number already exists. Please enter a unique roll number.");
            } else {
                break;
            }
        }
        return rollNumber;
    }

    private int getValidMark(String subject) {
        int mark;
        while (true) {
            logger.info("Enter marks for " + subject + ": ");
            mark = getIntInput();

            if (mark < 0 || mark > 100)
                logger.warning("Subject mark must be between 0 and 100.");
            else
            {
                break;
            }
        }

        return mark;
    }

    private int getValidProjectMark() {
        int projectMark;
        while (true) {
            logger.info("Enter project mark (out of 10): ");
            projectMark = getIntInput();

            if (projectMark < 0 || projectMark > 10)
                logger.warning("Project mark must be between 0 and 10.");
            else
                break;
        }
        return projectMark;
    }

    private int getIntInput() {
        while (true) {
            try {
                int num = scanner.nextInt();
                scanner.nextLine();
                return num;
            } catch (InputMismatchException e) {
                logger.warning("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    @Override
    public double getAverage(Student student) {
        int total = 0;
        for (int mark : student.getSubjectMarks().values()) {
            total += mark;
        }
        // Normalize project mark (0-10) to scale of 100
        total += (student.getProjectMark() * 10);
        return (double) total / (student.getSubjectMarks().size() + 1);
    }

    @Override
    public String calculateGrade(Student student) {
        double avg = getAverage(student);
        if (avg >= 90) return "A";
        else if (avg >= 75) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 40) return "D";
        else return "F";
    }

}
