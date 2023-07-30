import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
class Student {
    private String name;
    private int rollNumber;
    private String grade;
    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }
}

public class taskThree {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int option = getIntInput(scanner, "Enter your choice: ");

            switch (option) {
                case 1:
                    addNewStudent(scanner, sms);
                    break;
                case 2:
                    editStudentInformation(scanner, sms);
                    break;
                case 3:
                    searchStudent(scanner, sms);
                    break;
                case 4:
                    displayAllStudents(sms);
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Add a new student");
        System.out.println("2. Edit an existing student's information");
        System.out.println("3. Search for a student");
        System.out.println("4. Display all students");
        System.out.println("5. Exit");
    }

    private static void addNewStudent(Scanner scanner, StudentManagementSystem sms) {
        System.out.println("\n--- Adding a new student ---");
        String name = getStringInput(scanner, "Enter student's name: ");
        int rollNumber = getIntInput(scanner, "Enter student's roll number: ");
        String grade = getStringInput(scanner, "Enter student's grade: ");

        Student newStudent = new Student(name, rollNumber, grade);
        sms.addStudent(newStudent);
        System.out.println("Student added successfully!");
    }

    private static void editStudentInformation(Scanner scanner, StudentManagementSystem sms) {
        System.out.println("\n--- Editing student information ---");
        int rollNumber = getIntInput(scanner, "Enter student's roll number: ");
        Student student = sms.searchStudent(rollNumber);

        if (student != null) {
            String newName = getStringInput(scanner, "Enter student's new name: ");
            String newGrade = getStringInput(scanner, "Enter student's new grade: ");

            // Update student information
            student.setName(newName);
            student.setGrade(newGrade);
            System.out.println("Student information updated successfully!");
        } else {
            System.out.println("Student with the given roll number not found.");
        }
    }

    private static void searchStudent(Scanner scanner, StudentManagementSystem sms) {
        System.out.println("\n--- Searching for a student ---");
        int rollNumber = getIntInput(scanner, "Enter student's roll number: ");
        Student student = sms.searchStudent(rollNumber);

        if (student != null) {
            System.out.println("Student found:");
            System.out.println(student);
        } else {
            System.out.println("Student with the given roll number not found.");
        }
    }

    private static void displayAllStudents(StudentManagementSystem sms) {
        System.out.println("\n--- All Students ---");
        List<Student> students = sms.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
        return input;
    }
}
