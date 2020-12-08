import javax.swing.*;
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
    String filePath;
    Scanner s;
    List<Employee> surgeryList = new ArrayList<>();
    List<Employee> criticalCareList = new ArrayList<>();
    List<Employee> cardiologyList = new ArrayList<>();
    String data;
    FileWriter writer;

    private String firstName;
    private String lastName;
    private String gender;
    private String birthDate;
    private double salary;
    private String department;
    //private Department department?;
    private String phoneNumber;
    private String specialization;
    private String userChoice;


    public TestingList() throws IOException {
        while (true){
            String input = JOptionPane.showInputDialog("Vilken avdelning?" + "\n1: Kardiologi\n2: Operation\n3: Akuten");
            if (input == null)
                break;
            else if (input.equalsIgnoreCase("1")) {
                ReadFromList("Lists\\CardiologyEmployees", cardiologyList);
            }
            else if (input.equalsIgnoreCase("2")) {
                ReadFromList("Lists\\SurgeryEmployees", surgeryList);
            }
            else if (input.equalsIgnoreCase("3")) {
                ReadFromList("Lists\\CriticalCareEmployees", criticalCareList);
            }
            else
                System.out.println("Försök igen");

        }
    }

    private void AddNewEmployee(String filePath, List<Employee> list) throws IOException {
        writer = new FileWriter(filePath, true);

        firstName = JOptionPane.showInputDialog("Förnamn?");
        lastName = JOptionPane.showInputDialog("Efternamn?");
        gender = JOptionPane.showInputDialog("Kön");
        birthDate = JOptionPane.showInputDialog("Födelsedag (xxxx-xx-xx)?");
        department = JOptionPane.showInputDialog("Avdelning?");
        phoneNumber = JOptionPane.showInputDialog("Telefonnummer?");
        String inputDialog = JOptionPane.showInputDialog("Lön?");
        salary = Double.parseDouble(inputDialog);
        specialization = JOptionPane.showInputDialog("Övrigt?");
        System.out.println("Färdig");
        Employee employee = new Employee(firstName, lastName, gender, birthDate, department, phoneNumber, salary, specialization);
        writer.write("\n"+employee.writeInfo());

        writer.close();


    }

    public void ReadFromList(String filePath,List<Employee> list) throws IOException, ArrayIndexOutOfBoundsException{
        System.out.println(filePath);
        int input = JOptionPane.showConfirmDialog(null, "Vill du lägga till ny anställd?");
        if (input == 0){
            AddNewEmployee(filePath, list);
        }
        else {
            s = new Scanner(new FileReader(filePath));
            try {
                while (s.hasNextLine()) {
                    data = s.nextLine();
                    System.out.println(data);
                    String[] temp = data.split(",");
                    firstName = temp[0];
                    lastName = temp[1];
                    gender = temp[2];
                    birthDate = temp[3];
                    department = temp[4];
                    phoneNumber = temp[5];
                    salary = Double.parseDouble(temp[6]);
                    specialization = temp[7];

                    list.add(new Employee(firstName, lastName, gender, birthDate, department, phoneNumber, salary, specialization));
                }
                s.close();

                for (Employee e : list
                ) {
                    e.printInfo();
                }


                System.out.println("----------------------------------------------");


                //WriteToList(list, filePath);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

    }

    public void WriteToList(List<Employee> list, String s) throws IOException {
         writer = new FileWriter(s, false);

        for (Employee e:list
             ) {
            if (e.getFirstName().equalsIgnoreCase("axel")){
                e.setSalary(80000);
            }
            System.out.println(e.writeInfo());
            writer.write(e.writeInfo()+"\n");
        }
        System.out.println("----------------------------------------------");
        writer.close();
    }



    public static void main(String[] args) throws IOException {
        TestingList t = new TestingList();
    }
}
