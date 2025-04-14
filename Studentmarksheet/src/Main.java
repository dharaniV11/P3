import java.util.*;

class StudentReportCardGenerator {
    private static Map<Integer, Student> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Studentservice studentService = new Studentservice(students, scanner);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n====== Student Report Card Generator 1======");
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
        } else if (option == 2)
        {
            String grade = "";
            boolean validGrade = false;

            // Keep asking for valid grade input
            while (!validGrade) {
                System.out.print("Enter grade to filter (A/B/C/D/F): ");
                grade = scanner.nextLine().trim().toUpperCase();

                // Check if the input is valid
                if (grade.equals("A") || grade.equals("B") || grade.equals("C") || grade.equals("D") || grade.equals("F")) {
                    validGrade = true;  // Exit the loop if the grade is valid
                } else {
                    System.out.println("Invalid grade input. Please enter a valid grade (A, B, C, D, F).");
                }
            }

            // Filter students by the selected grade
            for (Student s : students.values()) {
                if (s.calculateGrade().equals(grade)) {
                    toDisplay.add(s);
                }
            }
        }
        else
        {
            System.out.println("Invalid option.");
            return;
        }

        if (toDisplay.isEmpty()) {
            System.out.println("No students found for the given criteria.");
        } else {
            for (Student s : toDisplay) {
                printReportCard(s);
            }
        }
    }

    private static void printReportCard(Student student) {
        System.out.println("\n----------------------------------------");
        System.out.println("Name      : " + student.getName());
        System.out.println("Roll No.  : " + student.getRollNumber());
        System.out.println("Subjects  :");
        for (Map.Entry<String, Integer> entry : student.getSubjectMarks().entrySet()) {
            System.out.printf("  - %-10s : %3d\n", entry.getKey(), entry.getValue());
        }
        System.out.printf("Average   : %.2f\n", student.getAverage());
        System.out.println("Grade     : " + student.calculateGrade());
        System.out.println("----------------------------------------");
    }
}
