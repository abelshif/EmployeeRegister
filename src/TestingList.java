import javax.swing.table.TableRowSorter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Axel Jeansson
 * Date: 2020-12-08
 * Time: 11:29
 * Project: AOD2Employee
 * Copyright: MIT
 */
public class TestingList {
    Scanner s = new Scanner(new File("Lists\\SurgeryEmployees"));
    List<Employee> surgeryList = new ArrayList<>();
    String data;

    private String firstName;
    private String lastName;
    private String gender;
    private String birthDate;
    private double salary;
    private String department;
    //private Department department?;
    private String phoneNumber;
    private String specialization;



    public TestingList() throws IOException {
        while (s.hasNextLine()) {
            data = s.nextLine();

            String[] temp = data.split(",");
            firstName = temp[0];
            lastName = temp[1];
            gender = temp[2];
            birthDate = temp[3];
            department = temp[4];
            phoneNumber = temp[5];
            salary = Double.parseDouble(temp[6]);
            specialization = temp[7];

            surgeryList.add(new Employee(firstName, lastName, gender, birthDate, department, phoneNumber, salary, specialization));

        }
        s.close();

        for (Employee e:surgeryList
             ) {
            e.printInfo();
        }

        Employee axel = new Employee("Axel","Jeansson","Man","19940415","Surgery","0707677207",12000,"Raketforskare");
        addEmployee(axel);
        WriteToList();
    }

    public void WriteToList() throws IOException {
        FileWriter writer = new FileWriter("Lists\\SurgeryEmployees");

        for (Employee e:surgeryList
             ) {
            e.printInfo();
        }


    }

    public void addEmployee(Employee axel){
        surgeryList.add(new Employee());
    }


    public static void main(String[] args) throws IOException {
        TestingList t = new TestingList();
    }
}
