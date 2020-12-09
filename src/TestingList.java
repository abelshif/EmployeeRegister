import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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

    private String cardiologyFile = "Lists\\CardiologyEmployees";
    private String surgeryFile = "Lists\\SurgeryEmployees";
    private String criticalCareFile = "Lists\\CriticalCareEmployees";

    private String firstName;
    private String lastName;
    private String gender;
    private String birthDate;
    private double salary;
    private String department;
    //private Department department?;
    private String phoneNumber;
    private String specialization;
    private String[] departments = {"Kardiologi", "Kirurgi", "Akuten"};

    public TestingList() throws IOException {
        while (true){
            int input = JOptionPane.showOptionDialog(null, "Vilken avdelning vill kolla på?", "Sjukhus", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, departments, departments[0]);
            if (input == 0) {
                ReadFromList(cardiologyFile, cardiologyList);
            }
            else if (input == 1) {
                ReadFromList(surgeryFile, surgeryList);
            }
            else if (input == 2) {
                ReadFromList(criticalCareFile, criticalCareList);
            }
            else
                System.exit(0);

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
        s = new Scanner(new FileReader(filePath));
        try {
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

                list.add(new Employee(firstName, lastName, gender, birthDate, department, phoneNumber, salary, specialization));
            }
            s.close();




            System.out.println("----------------------------------------------");


        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        System.out.println(filePath);
        String[] userOptions = {"Kolla avdelning", "Lägg till anställd", "Ta bort anställd", "Flytta anställd"};
        int input = JOptionPane.showOptionDialog(null, "Vad vill du göra?", "Management system", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, userOptions, userOptions[0]);
        if (input == 0){
            for (Employee e : list
            ) {
                e.printInfo();
            }
        }
        else if (input == 1){
            AddNewEmployee(filePath, list);
        }
        else if (input == 2){
            RemoveEmployee(filePath, list);
        }
        else if (input == 3){
            RemoveEmployee(filePath, list);
        }

    }

    public void RemoveEmployee(String filePath, List<Employee> list) throws IOException {
        System.out.println("test");
        File tempFile = new File("Lists\\tempEmployeeList");
        File newFile = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(tempFile));
        String name = JOptionPane.showInputDialog("Vad heter den anställda du vill ta bort eller flytta?");
        String employeeInfo = "";



            for (Employee e:list
            ) {
                System.out.println(e.writeInfo());
                if (e.getFirstName().equalsIgnoreCase(name)) {
                    System.out.println("Test hittades!");
                    employeeInfo = e.writeInfo();
                }
            }

                    String currentLine;

        try {

            while ((currentLine = reader.readLine()) != null){
                System.out.println(employeeInfo);
                if (!currentLine.trim().equals(employeeInfo)){
                    writer2.write(currentLine+"\n");
                    writer2.flush();
                }
                else if (currentLine.trim().equals(employeeInfo)){
                    System.out.println(currentLine + "... togs bort");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer2.close();
        reader.close();

        newFile.delete();
        tempFile.renameTo(new File(filePath));

        int choice = JOptionPane.showConfirmDialog(null, "Vill du flytta den anställda till en annan avdelning?");
        if (choice == 0) {
            choice = JOptionPane.showOptionDialog(null, "Till vilken avdelning?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, departments, departments[0]);
            if (choice == 0){
                SwapDepartment(employeeInfo, cardiologyFile);
            }
            else if (choice == 1){
                SwapDepartment(employeeInfo, surgeryFile);
            }
            else if (choice == 2){
                SwapDepartment(employeeInfo, criticalCareFile);
            }
        }


    }

    private void SwapDepartment(String employeeInfo, String filePath) throws IOException {
        writer = new FileWriter(filePath, true);

        try {
            writer.write("\n"+employeeInfo);
        }catch (IOException e){
            e.printStackTrace();
        }
        writer.close();
    }


    public static void main(String[] args) throws IOException {
        TestingList t = new TestingList();
    }
}
