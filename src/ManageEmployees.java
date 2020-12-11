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


    /*public class EditWindow extends JFrame {
        public EditWindow(String department) throws HeadlessException {

            JLabel addName = new JLabel("Name");
            JLabel addSurName = new JLabel("Surname");
            JLabel addGender = new JLabel("Gender");
            JLabel addBirthDate = new JLabel("Birth date");
            JLabel addTelNo = new JLabel("Tel.no");
            JLabel addSalary = new JLabel("Salary");
            JLabel addDepartment = new JLabel("Department");
            JLabel addRole = new JLabel("Role");
            JLabel addID = new JLabel("userID");

            JButton buttonSave, buttonCancel;

            addName.setSize(80, 30);
            addSurName.setSize(80, 30);
            addGender.setSize(80, 30);
            addBirthDate.setSize(80, 30);
            addTelNo.setSize(80, 30);
            addSalary.setSize(80, 30);
            addDepartment.setSize(80, 30);
            addRole.setSize(80, 30);
            addID.setBounds(10,340, 80,30);
            addName.setLocation(10, 20);
            addSurName.setLocation(10, 60);
            addGender.setLocation(10, 100);
            addBirthDate.setLocation(10, 140);
            addTelNo.setLocation(10, 180);
            addSalary.setLocation(10, 220);
            addDepartment.setLocation(10, 260);
            addRole.setLocation(10, 300);

            JTextField namnField = new JTextField(50);

            JTextField surNameField = new JTextField(50);
            JTextField userIDField = new JTextField();
            JComboBox<String> genderField = new JComboBox<>();
            genderField.addItem("Male");
            genderField.addItem("Female");
            genderField.addItem("Unspecified");
            JTextField birthDateField = new JTextField(50);
            JTextField telNoField = new JTextField(50);
            JTextField salaryField = new JTextField(50);
            JComboBox<String>departmentField= new JComboBox<>();
            departmentField.addItem(department);
            //JTextField departmentField = new JTextField(50);
            //JTextField roleField = new JTextField(50);
            JComboBox<String> roleField= new JComboBox<>();
            roleField.addItem("Doctor");
            roleField.addItem("Nurse");
            userIDField.setEditable(false);

            namnField.addActionListener(e1 -> {
                String namn = namnField.getText();
                String iD = e.generateID(namn);

                System.out.println(iD);
                userIDField.setText(iD);
            });


            namnField.setSize(200, 30);
            surNameField.setSize(200, 30);
            genderField.setSize(200, 30);
            birthDateField.setSize(200, 30);
            telNoField.setSize(200, 30);
            salaryField.setSize(200, 30);
            departmentField.setSize(200, 30);
            roleField.setSize(200, 30);
            namnField.setLocation(80, 20);
            surNameField.setLocation(80, 60);
            genderField.setLocation(80, 100);
            birthDateField.setLocation(80, 140);
            telNoField.setLocation(80, 180);
            salaryField.setLocation(80, 220);
            departmentField.setLocation(80, 260);
            roleField.setLocation(80, 300);
            userIDField.setBounds(80, 340, 200, 30);
            //getContentPane().setBackground(new Color(136, 0, 255));
            setLayout(null);
            setSize(350, 500);

            add(addName);
            add(addSurName);
            add(addGender);
            add(addBirthDate);
            add(addTelNo);
            add(addSalary);
            add(addDepartment);
            add(addRole);
            add(addID);
            add(namnField);
            add(surNameField);
            add(genderField);
            add(birthDateField);
            add(telNoField);
            add(salaryField);
            add(departmentField);
            add(roleField);
            add(userIDField);

            setVisible(true);
            buttonSave = new JButton("Save");
            buttonCancel = new JButton("Cancel");
            buttonSave.setSize(80, 30);
            buttonSave.setLocation(40, 400);
            buttonCancel.setSize(80, 30);
            buttonCancel.setLocation(200, 400);
            add(buttonSave);
            add(buttonCancel);

            buttonCancel.addActionListener(e -> dispose());

            // Fixme: Ser inte bra ut såhär, Gör rent i klassen generellt
            buttonSave.addActionListener(e -> {
                if (e.getSource() == buttonSave) {

                    try {
                        saveButton(namnField.getText(), surNameField.getText(), genderField.getSelectedItem().toString(),
                                birthDateField.getText(), telNoField.getText(),
                                salaryField.getText(), departmentField.getSelectedItem().toString(), roleField.getSelectedItem().toString(), userIDField.getText());
                    } catch (IOException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }


                }
            });
        }

        /**
         * This method adds Employees to the DAO List<Employee>, Depending on the chosen department.
         * TODO: Get the output connected to the textfile.
         *
         * @param nameFieldText
         * @param surNameFieldText
         * @param genderFieldText
         * @param birthDateFieldText
         * @param telNoFieldText
         * @param salaryFieldText
         * @param departmentFieldText
         * @param roleFieldText
         * @return
         */


   /* public Employee saveButton(String nameFieldText, String surNameFieldText, String genderFieldText, String birthDateFieldText, String telNoFieldText, String salaryFieldText, String departmentFieldText, String roleFieldText, String userID) throws IOException {


            Employee addedEmployee = new Employee(nameFieldText, surNameFieldText,genderFieldText ,birthDateFieldText, departmentFieldText, telNoFieldText ,Double.parseDouble(salaryFieldText), roleFieldText, userID);

            DAO dao = new DAO();

            updateEmployeeWindow(addedEmployee, dao, departmentFieldText);

            dispose();
            return addedEmployee;
        }

        /**
         * This method updates the Employee window.
         *
         * @param addedEmployee
         * @param dao
         * @param department
         */
   /*     public void updateEmployeeWindow(Employee addedEmployee, DAO dao, String department) throws IOException {

            String info = "";


            // Depending on the chosen department for the employee, he/she will be added to said department list.
            switch (department) {
                case cardiology -> {
                    dao.cardiologyList.add(addedEmployee);
                    info = addedEmployee.writeInfo();
                    addEmployeeToTextFile(cardiologyFile, info);
                    new ManageEmployees(dao.cardiologyList,cardiologyFile, department, authority);
                }
                case anaesthetics -> {
                    dao.anaestheticsList.add(addedEmployee);
                    info = addedEmployee.writeInfo();
                    addEmployeeToTextFile(anaestheticsFile, info);
                    new ManageEmployees(dao.anaestheticsList,anaestheticsFile, department, authority);
                }
                case surgery -> {
                    dao.surgeryList.add(addedEmployee);
                    info = addedEmployee.writeInfo();
                    addEmployeeToTextFile(surgeryFile, info);
                    new ManageEmployees(dao.surgeryList,surgeryFile, department, authority);
                }
                case criticalCare -> {
                    dao.criticalCareList.add(addedEmployee);
                    info = addedEmployee.writeInfo();
                    addEmployeeToTextFile(criticalCareFile, info);
                    new ManageEmployees(dao.criticalCareList,criticalCareFile, department, authority);
                }
            }


            frame.dispose();
            frame.revalidate();
            frame.repaint();
        }


        private void addEmployeeToTextFile(String filePath, String EmployeeInfo) throws IOException {
            writer = new FileWriter(filePath, true);
            s = new Scanner(new FileReader(filePath));
            String data = "";

            try {
                while (s.hasNext()) {
                    data = s.nextLine();
                    if (data.trim().isEmpty()) {
                        writer.write(data+"\n");
                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }

            try{
                writer.write(EmployeeInfo+"\n");
            }catch (IOException exception){
                exception.printStackTrace();
            }

            writer.close();
            s.close();

        }


    }

    */

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