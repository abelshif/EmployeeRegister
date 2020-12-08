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
    Scanner s = new Scanner(new File("Lists\\CriticalCareEmployees"));
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
            if (e.getFirstName().equalsIgnoreCase("axel")){
                e.setSalary(80000);
            }
            e.printInfo();
        }

        surgeryList.add(new Employee("Oscar","Johansson","Man","19939393","Surgery","0765420340",38888,"Fobollsproffs"));

        WriteToList();
    }

    public void WriteToList() throws IOException {
        FileWriter writer = new FileWriter("Lists\\SurgeryEmployees");

        for (Employee e:surgeryList
             ) {
            System.out.println(e.writeInfo());
            writer.write(e.writeInfo()+"\n");
        }
        writer.close();


    }


    public static void main(String[] args) throws IOException {
        TestingList t = new TestingList();
    }
}
