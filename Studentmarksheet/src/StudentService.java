import java.util.*;

public class StudentService implements Gradable {
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

            // Fixed subjects
            String[] subjects = { "English", "Tamil", "Maths", "Science", "Social" };
            Map<String, Integer> marks = new HashMap<>();

            for (String subject : subjects) {
                int mark = getValidMark(subject);
                marks.put(subject, mark);
            }

            int projectMark = getValidProjectMark();

            // Create Student object (bean)
            Student student = new Student(name, rollNumber, marks, projectMark);
            students.put(rollNumber, student);
            System.out.println("Student added successfully!");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private String getValidName() {
        String name;
        while (true) {
            System.out.print("Enter student name: ");
            name = scanner.nextLine().trim();
            if (name.matches("[A-Za-z\\s]+")) break;
            else{
                System.out.println("Invalid name. Name cannot be empty or numeric only.");
            }
        }
        return name;
    }

    private int getUniqueRollNumber() {
        int rollNumber;
        while (true) {
            System.out.print("Enter roll number: ");
            rollNumber = getIntInput();
            if (students.containsKey(rollNumber)) {
                System.out.println("Roll number already exists. Please enter a unique roll number.");
            } else {
                break;
            }
        }
        return rollNumber;
    }

    private int getValidMark(String subject) {
        int mark;
        while (true) {
            System.out.print("Enter marks for " + subject + ": ");
            mark = getIntInput();
            try {
                if (mark < 0 || mark > 100) {
                    throw new InvalidMarksException("Marks must be between 0 and 100.");
                }
                break;
            } catch (InvalidMarksException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return mark;
    }

    private int getValidProjectMark() {
        int projectMark;
        while (true) {
            System.out.print("Enter project mark (out of 10): ");
            projectMark = getIntInput();
            try {
                if (projectMark < 0 || projectMark > 10) {
                    throw new InvalidMarksException("Project mark must be between 0 and 10.");
                }
                break;
            } catch (InvalidMarksException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return projectMark;
    }

    private int getIntInput() {
        while (true) {
            try {
                int num = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return num;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // flush
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
