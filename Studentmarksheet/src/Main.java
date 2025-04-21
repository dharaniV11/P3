import java.util.*;

public class Main {
    private static Map<Integer, Student> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService = new StudentService(students, scanner);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n====== Student Report Card Generator ======");
            System.out.println("1. Add Student");
            System.out.println("2. View Report Cards (All / By Grade)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    studentService.addStudent();
                    break;
                case 2:
                    viewReportCards();
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static int getIntInput() {
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

    private static void viewReportCards() {
        if (students.isEmpty()) {
            System.out.println("No students to show.");
            return;
        }

        System.out.println("\n1. View All Students\n2. Filter by Grade");
        System.out.print("Choose option: ");
        int option = getIntInput();

        List<Student> toDisplay = new ArrayList<>();

        if (option == 1) {
            toDisplay.addAll(students.values());
        } else if (option == 2) {
            String grade = getGradeFilter();
            for (Student s : students.values()) {
                if (studentService.calculateGrade(s).equals(grade)) {
                    toDisplay.add(s);
                }
            }
        }
        else
            {
            System.out.println("invalid input, try again");
                return;
        }

        if (toDisplay.isEmpty()) {
            System.out.println("No students found for the given criteria.");
            viewReportCards();
        } else {
            for (Student s : toDisplay) {
                printReportCard(s);
            }
        }
    }

    private static String getGradeFilter() {
        String grade = "";
        while (true) {
            System.out.print("Enter grade to filter (A/B/C/D/F): ");
            grade = scanner.nextLine().trim().toUpperCase();
        if (grade.matches("[ABCDF]")) {
                break;
            }
            System.out.println("Invalid input. Try again.");
        }
        return grade;
    }

    private static void printReportCard(Student student) {
        System.out.println("\nREPORT CARD: " + student.getName().toUpperCase());
        System.out.println("----------------------------------------");
        System.out.println("Name      : " + student.getName().toUpperCase());
        System.out.println("Roll No.  : " + student.getRollNumber());
        System.out.println("Subjects  :");
        for (Map.Entry<String, Integer> entry : student.getSubjectMarks().entrySet()) {
            System.out.printf("  - %-10s : %3d\n", entry.getKey(), entry.getValue());
        }
        System.out.println("Project   : " + student.getProjectMark() + " / 10");
        System.out.printf("Average   : %.2f\n", studentService.getAverage(student));
        System.out.println("Grade     : " + studentService.calculateGrade(student));
        System.out.println("----------------------------------------");
    }
}
