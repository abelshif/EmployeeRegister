import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageEmployees {

    private final JFrame frame = new JFrame("Employee Register");
    private JLabel label1;
    private JTextField textField;
    private JButton addEmployeeButton, RemoveEmployeeButton, UpdateButton, backToMenu, printEmployeeCard;
    private JTable table;
    private final String[] columnNames = {"Firstname", "Surname", "Gender", "Birth date", "Tel.no", "Salary", "Department", "Role"};
    private JScrollPane scrollPane;
    private DefaultTableModel tabelmodel;
    private String[] departments = {"Kardiologi", "Kirurgi", "Akuten", "Anestesi"};
    public static final String cardiologyFile = "Lists\\CardiologyEmployees";
    public static final String surgeryFile = "Lists\\SurgeryEmployees";
    public static final String criticalCareFile = "Lists\\CriticalCareEmployees";
    public static final String anaestheticsFile = "Lists\\AnaestheticsEmployees";
    FileWriter writer;
    Scanner s;

    public static final String cardiology = "Cardiology";
    public static final String anaesthetics = "Anaesthetics";
    public static final String surgery = "Surgery";
    public static final String criticalCare = "Critical care";
    private String authority;


    /**
     * This method takes the List<Employee> and converts it to Object[][]
     * to use it as a parameter for GUI -> DefaultTableMode
     *
     * @param listToConvertFrom
     * @return
     */
    public Object[][] convertListTo2DObjectArray(List<Employee> listToConvertFrom) {

        // List to convert to with the size of the list from parameter
        Object[][] listToConvertTo = new Object[listToConvertFrom.size()][];

        // List to hold objects
        List<Object[][]> listThatHoldsObjects = new ArrayList();

        int counter = listToConvertFrom.size();


        // For each Employee in list, create new 2D-Object[][]
        for (Employee employee : listToConvertFrom) {

            Object[][] objects = new Object[][]{{employee.getFirstName(), employee.getLastName(),
                    employee.getGender(), employee.getBirthDate(), employee.getPhoneNumber(),
                    employee.getSalary(), employee.getDepartment(), employee.getRole()}};

            listThatHoldsObjects.add(objects);

        }

        // for each index add an object to the list
        for (int i = 0; i < listThatHoldsObjects.size(); i++) {

            listToConvertTo[i] = listThatHoldsObjects.get(i)[0];
            counter--;

            // Once the counter hits 0 (The list is complete) return new 2D-Object[][]
            if (counter == 0)
                return listToConvertTo;

        }
        return null;
    }

    public ManageEmployees(List<Employee> employeeList, String filePath, String department,String authority) {
        this.authority = authority;

        Object[][] convertedList = convertListTo2DObjectArray(employeeList);
        System.out.println("Listans storlek: " + employeeList.size());
        System.out.println(filePath);

        frame.setLayout(null);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        table = new JTable();
        tabelmodel = new DefaultTableModel(convertedList, columnNames);
        table.setModel(tabelmodel);

        scrollPane = new JScrollPane(table);
        scrollPane.setSize(700, 270);
        scrollPane.setLocation(50, 50);
        frame.add(scrollPane);

        addEmployeeButton = new JButton("Add");
        addEmployeeButton.setSize(100, 30);
        addEmployeeButton.setLocation(50, 370);
        addEmployeeButton.addActionListener(e -> {
            JFrame addFrame = new EditWindow(department, frame, authority);
        });


        frame.add(addEmployeeButton);

        RemoveEmployeeButton = new JButton("Delete/Move");
        RemoveEmployeeButton.setSize(100, 30);
        RemoveEmployeeButton.setLocation(250, 370);
        RemoveEmployeeButton.setFont(new Font(RemoveEmployeeButton.getFont().getName(), RemoveEmployeeButton.getFont().getStyle(), 10));
        RemoveEmployeeButton.addActionListener(e -> {
            try {
                RemoveEmployee(employeeList,filePath,department);
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

        });
        frame.add(RemoveEmployeeButton);

        UpdateButton = new JButton("Update info");
        UpdateButton.setSize(100, 30);
        UpdateButton.setLocation(450, 370);
        UpdateButton.addActionListener(e -> {
            JFrame updateFrame = new EditWindow(department, frame, authority);
        });
        frame.add(UpdateButton);

        backToMenu = new JButton("Tillbaka");
        backToMenu.setBounds(650, 470, 100 ,30);
        backToMenu.addActionListener(e -> {
            try {
                new ManageDepartment();
                frame.dispose();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        if (authority.equalsIgnoreCase("HR"))
            frame.add(backToMenu);


        printEmployeeCard = new JButton("Print Card");
        printEmployeeCard.setSize(100, 30);
        printEmployeeCard.setLocation(650, 370);
        printEmployeeCard.addActionListener(e -> {

            if(table.getSelectedRow()!=-1){
                new PassCard(table, tabelmodel, employeeList);
            } else {
                JOptionPane.showMessageDialog(frame,"OBS! Please select a row to print the card");
            }

        });

        frame.add(printEmployeeCard);

        label1 = new JLabel("Search");
        label1.setSize(200, 30);
        label1.setLocation(550, 8);
        frame.add(label1);

        textField = new JTextField(10);
        textField.setSize(150, 20);
        textField.setLocation(600, 15);
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                String textToSearch = textField.getText();
                Object[][] filteredEmployeeInfo = filterInfoEmployee(textToSearch, convertedList, employeeList);
                tabelmodel = new DefaultTableModel(filteredEmployeeInfo, columnNames);
                table.setModel(tabelmodel);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        frame.add(textField);
        frame.setVisible(true);

        repaint();

    }

    public void repaint(){

        frame.repaint();
        frame.revalidate();

    }

    /**
     * This method compares the "textToSearch" with the getName();
     * All employees that share the same char sequence from "textToSearch" as their getName(); will be shown.
     *
     * @param textToSearch
     * @param employeeInfo
     * @param employeeList
     * @return Searched Employee
     */
    private Object[][] filterInfoEmployee(String textToSearch, Object[][] employeeInfo, List<Employee> employeeList) {

        // Default
        if (textToSearch.isEmpty()) { return employeeInfo; }

        // When searching
        String nameToCompare;

        for (Employee employee : employeeList) {

            nameToCompare = employee.getFirstName();

            boolean similar = nameToCompare.contains(textToSearch);
            boolean contains = nameToCompare.equalsIgnoreCase(textToSearch);

            if (contains || similar) {

                return new Object[][]{{employee.getFirstName(), employee.getLastName(), employee.getGender(),
                        employee.getBirthDate(), employee.getPhoneNumber(), employee.getSalary(),
                        employee.getDepartment(), employee.getRole()}};
            }
        }
        return null;
    }

    public void RemoveEmployee(List<Employee> list, String filePath, String department) throws IOException {
        String tempDep = "";
        // Depending on the chosen department for the employee, he/she will be removed from said department list.
        File tempFile = new File("Lists\\tempEmployeeList");
        File newFile = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(tempFile));
        String name = JOptionPane.showInputDialog("userID på den anställda du vill ta bort eller flytta?");
        String employeeInfo = "";

        for (Employee e:list
        ) {
            if (e.getUserID().equalsIgnoreCase(name)) {
                System.out.println("Test hittades!");
                employeeInfo = e.writeInfo();
            }
        }

        String currentLine;

        try {

            while ((currentLine = reader.readLine()) != null){
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

        int userChoice = JOptionPane.showConfirmDialog(null,"Vill du flytta personen till en annan avdelning?");
            if (userChoice == 0){
                userChoice = JOptionPane.showOptionDialog(null, "Till vilken avdelning?", "Flytta anställd", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, departments, departments[0]);
                if (userChoice == 0){
                    tempDep = employeeInfo.replace(department, "Cardiology");
                    SwapDepartment(tempDep, cardiologyFile);
                }
                else if (userChoice == 1){
                    tempDep = employeeInfo.replace(department, "Surgery");
                    SwapDepartment(tempDep, surgeryFile);
                }
                else if (userChoice == 2){
                    tempDep = employeeInfo.replace(department, "Critical care");
                    SwapDepartment(tempDep, criticalCareFile );
                }
                else if(userChoice == 3){
                    tempDep = employeeInfo.replace(department, "Anaesthetics");
                    SwapDepartment(tempDep, anaestheticsFile);
                }
                else
                    System.out.println("Anställd borttagen");
            }

        DAO dao = new DAO();

        switch (department) {
            case cardiology -> {
                new ManageEmployees(dao.cardiologyList,cardiologyFile, department,authority);
            }
            case anaesthetics -> {
                new ManageEmployees(dao.anaestheticsList,anaestheticsFile, department,authority);
            }
            case surgery -> {
                new ManageEmployees(dao.surgeryList,surgeryFile, department,authority);
            }
            case criticalCare -> {
                new ManageEmployees(dao.criticalCareList,criticalCareFile, department,authority);
            }
        }



        frame.dispose();
        frame.revalidate();
        frame.repaint();




    }

    private void SwapDepartment(String employeeInfo, String filePath) throws IOException {
        writer = new FileWriter(filePath, true);
        s = new Scanner(new FileReader(filePath));
        String data = "";

        try {
            while (s.hasNext()) {
                data = s.nextLine();
                if (data.trim().isEmpty()) {
                    writer.write(data);
                    writer.write("\n");
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        try {
            writer.write(employeeInfo+"\n");
        }catch (IOException e){
            e.printStackTrace();
        }
        writer.close();
        s.close();
    }


}