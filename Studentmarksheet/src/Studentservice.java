import java.util.*;

public class Studentservice {
    private Map<Integer, Student> students;
    private Scanner scanner;

    public Studentservice(Map<Integer, Student> students, Scanner scanner) {
        this.students = students;
        this.scanner = scanner;
    }

    public void addStudent() {
        try {
            String name;
            while (true) {
                System.out.print("Enter student name: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty() || name.matches("\\d+")) {
                    System.out.println("Invalid name. Name cannot be empty or numeric only.");
                } else {
                    break;
                }
            }

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

            // Fixed subjects
            String[] subjects = { "English", "Tamil", "Maths", "Science", "Social" };
            Map<String, Integer> marks = new HashMap<>();

            for (String subject : subjects) {
                int mark;
                while (true) {
                    System.out.print("Enter marks for " + subject + ": ");
                    mark = getIntInput();
                    try
                    {
                        if (mark < 0 || mark > 100)
                        {
                            throw new InvalidMarksException("Marks must be between 0 and 100.");
                        }
                        break;
                    } catch (InvalidMarksException e)
                    {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                marks.put(subject, mark);
            }

            Student student = new Student(name, rollNumber, marks);
            students.put(rollNumber, student);
            System.out.println("Student added successfully!");

        } catch (Exception e) {

        }
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
}
