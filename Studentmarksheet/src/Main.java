import java.util.*;
import java.util.logging.*;

public class Main {
    private static Logger logger = Logger.getLogger(String.valueOf(Main.class));
    private static Map<Integer, Student> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService = new StudentService(students, scanner);

    public static void main(String[] args) {
        configureLogger();
        while (true) {
            logger.info("\n\u001B[97m====== Student Report Card Generator ======");
            logger.info("1. Add Student");
            logger.info("2. View Report Cards (All / By Grade)");
            logger.info("3. Exit");
            logger.info("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    studentService.addStudent();
                    break;
                case 2:
                    viewReportCards();
                    break;
                case 3:
                    logger.info("Exiting... Thank you!");
                    return;
                default:
                    logger.warning("Invalid choice. Try again.");
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
                logger.warning("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // flush
            }
        }
    }

    private static void viewReportCards() {
        if (students.isEmpty()) {
            logger.info("No students to show.");
            return;
        }

        logger.info("\n1. View All Students\n2. Filter by Grade");
        logger.info("Choose option: ");
        int option = getIntInput();

        List<Student> toDisplay = new ArrayList<>();

        if (option == 1) {
            toDisplay.addAll(students.values());
        } else if (option == 2) {
            String grade = getGradeFilter();
            for (Student s : students.values()) {
                if (s.getGrade().equals(grade)) {
                    toDisplay.add(s);
                }
            }
        }
        else
            {
            logger.warning("invalid input, try again");
            viewReportCards();
            return;
        }

        if (toDisplay.isEmpty()) {
            logger.info("No students found for the given criteria.");
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
            logger.info("Enter grade to filter (A/B/C/D/F): ");
            grade = scanner.nextLine().trim().toUpperCase();
        if (grade.matches("[ABCDF]")) {
                break;
            }
            logger.warning("Invalid input. Try again.");
        }
        return grade;
    }

    private static void printReportCard(Student student) {
        logger.info("\nREPORT CARD: " + student.getName().toUpperCase());
        logger.info("----------------------------------------");
        logger.info("Name      : " + student.getName().toUpperCase());
        logger.info("Roll No.  : " + student.getRollNumber());
        logger.info("Subjects  :");
        for (Map.Entry<String, Integer> entry : student.getSubjectMarks().entrySet()) {
            logger.info(String.format("  - %-10s : %3d", entry.getKey(), entry.getValue()));
        }
        logger.info("Project   : " + student.getProjectMark() + " / 10");
        logger.info(String.format("Average   : %.2f", student.getAverage()));
        logger.info("Grade     : " + student.getGrade());
        logger.info("----------------------------------------");
    }

    private static void configureLogger() {
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();

        for (Handler handler : handlers) {
            handler.setFormatter(new SimpleFormatter() {
                private static final String FORMAT = "%s%n";

                @Override
                public synchronized String format(LogRecord record) {
                    return String.format(FORMAT, record.getMessage());
                }
            });
        }
    }

}
