package OldProject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class EmployeeMain {

    public static void main(String[] args) {

        OldDoctor doctor = null;
        OldNurse nurse = null;
        boolean bool = true;

        while (bool) {
            System.out.println("Select Task:");
            System.out.println("1: Create an Employee and save in file");
            System.out.println("2: List all Employees");
            System.out.println("3. Exit");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Select 'D' for doctor or 'N' for nurse");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("D")) {
                        doctor = new OldDoctor();
                        RegisterNewEmployee(doctor);
                    } else if (response.equalsIgnoreCase("N")) {
                        nurse = new OldNurse();
                        RegisterNewEmployee(nurse);
                    }
                    break;

                case "2":
                    System.out.println("==============");
                    System.out.println("Employees list");
                    System.out.println("==============");
                    readFromFile();
                    break;

                case "3":
                    System.out.println("Exiting Program");
                    bool = false;
                    break;

                default:
                    System.out.println("wrong input");
                    break;
            }
        }

    }

    private static void RegisterNewEmployee(OldEmployee employee) {
        try {

            Scanner scanner = new Scanner(System.in);
            //receive input
            System.out.println("Enter First Name");
            employee.setFirstName(scanner.nextLine());
            System.out.println("Enter Last Name");
            employee.setLastName(scanner.nextLine());
            System.out.println("Enter gender");
            employee.setGender(scanner.nextLine());
            System.out.println("Enter Birth date");
            employee.setBirthDate(scanner.nextLine());
            System.out.println("Enter Department");
            employee.setDepartment(scanner.nextLine());
            System.out.println("Enter Salary");
            employee.setSalary(scanner.nextDouble());

            String empId = employee.generateId();
            writeTofile(empId,employee);//write to file

            //validation of inputs
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input");
        } catch (IOException e) {
            System.out.println("The file couldn't be saved");
        } catch (Exception ex) {
            System.out.println("Unable to register employee.");
        } finally {
            System.out.println("New employee registered\n");
        }

    }

    //Method ----- write on the file ------
    private static void writeTofile(String employeeId, OldEmployee employee) throws IOException {
        PrintWriter printWriter = new PrintWriter( new FileWriter("EmployeeRegister.txt", true));
        printWriter.println(employeeId + "\t\t" + employee.getFirstName() + "\t\t" + employee.getLastName() +
                "\t\t" + employee.getBirthDate() + "\t\t" + employee.getDepartment() + "\t\t" +
                employee.getSalary());
        System.out.println("Employee saved to file\n");
        printWriter.close();

    }

    //Method -------read from the file path --------
    private static void readFromFile() {
        try {
            List<String> listOfEmployees = new ArrayList<>();
            Scanner read = new Scanner(new FileReader("EmployeeRegister.txt"));
            while (read.hasNext()) {
                listOfEmployees.add(read.nextLine());
            }
            System.out.println("\nID" + "\t\t" + "First Name" + "\t" + "Last Name" + "\t" + "Birth date" + "\t" +
                    "Department" + "\t" + "Salary");
            System.out.println("---------------------------------------------------------------------------- \n");

            for (String emp : listOfEmployees) {
                System.out.println(emp);
            }

        } catch (IOException ex) {
            System.out.println("Error reading from file");
            System.out.println(ex.getMessage());
        }
    }

}