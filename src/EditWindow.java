import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EditWindow extends JFrame{

        public EditWindow(String department) throws HeadlessException {

            JLabel addName = new JLabel("Name");
            JLabel addSurName = new JLabel("Surname");
            JLabel addGender = new JLabel("Gender");
            JLabel addBirthDate = new JLabel("Birth date");
            JLabel addTelNo = new JLabel("Tel.no");
            JLabel addSalary = new JLabel("Salary");
            JLabel addDepartment = new JLabel("Department");
            JLabel addRole = new JLabel("Role");

            JButton buttonSave, buttonCancel;

            addName.setSize(80, 30);
            addSurName.setSize(80, 30);
            addGender.setSize(80, 30);
            addBirthDate.setSize(80, 30);
            addTelNo.setSize(80, 30);
            addSalary.setSize(80, 30);
            addDepartment.setSize(80, 30);
            addRole.setSize(80, 30);
            addName.setLocation(10, 40);
            addSurName.setLocation(10, 80);
            addGender.setLocation(10, 120);
            addBirthDate.setLocation(10, 160);
            addTelNo.setLocation(10, 200);
            addSalary.setLocation(10, 240);
            addDepartment.setLocation(10, 280);
            addRole.setLocation(10, 320);

            JTextField namnField = new JTextField(50);
            JTextField surNameField = new JTextField(50);
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

            namnField.setSize(200, 30);
            surNameField.setSize(200, 30);
            genderField.setSize(200, 30);
            birthDateField.setSize(200, 30);
            telNoField.setSize(200, 30);
            salaryField.setSize(200, 30);
            departmentField.setSize(200, 30);
            roleField.setSize(200, 30);
            namnField.setLocation(80, 50);
            surNameField.setLocation(80, 90);
            genderField.setLocation(80, 130);
            birthDateField.setLocation(80, 170);
            telNoField.setLocation(80, 210);
            salaryField.setLocation(80, 250);
            departmentField.setLocation(80, 290);
            roleField.setLocation(80, 330);
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
            add(namnField);
            add(surNameField);
            add(genderField);
            add(birthDateField);
            add(telNoField);
            add(salaryField);
            add(departmentField);
            add(roleField);

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
                                salaryField.getText(), departmentField.getSelectedItem().toString(), roleField.getSelectedItem().toString());
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
        public Employee saveButton(String nameFieldText, String surNameFieldText, String genderFieldText, String birthDateFieldText, String telNoFieldText, String salaryFieldText, String departmentFieldText, String roleFieldText) throws IOException {

            Employee addedEmployee = new Employee(nameFieldText, surNameFieldText,genderFieldText ,birthDateFieldText, departmentFieldText, telNoFieldText ,Double.parseDouble(salaryFieldText), roleFieldText);

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
        public void updateEmployeeWindow(Employee addedEmployee, DAO dao, String department) throws IOException {

            String info = "";

            // Depending on the chosen department for the employee, he/she will be added to said department list.
            switch (department) {
                case ManageEmployees.cardiology -> {
                    dao.cardiologyList.add(addedEmployee);
                    info = addedEmployee.writeInfo();
                    addEmployeeToTextFile(ManageEmployees.cardiologyFile, info);
                    new ManageEmployees(dao.cardiologyList,ManageEmployees.cardiologyFile, department);
                }
                case ManageEmployees.anaesthetics -> {
                    dao.anaestheticsList.add(addedEmployee);
                    info = addedEmployee.writeInfo();
                    addEmployeeToTextFile(ManageEmployees.anaestheticsFile, info);
                    new ManageEmployees(dao.anaestheticsList,ManageEmployees.anaestheticsFile, department);
                }
                case ManageEmployees.surgery -> {
                    dao.surgeryList.add(addedEmployee);
                    info = addedEmployee.writeInfo();
                    addEmployeeToTextFile(ManageEmployees.surgeryFile, info);
                    new ManageEmployees(dao.surgeryList,ManageEmployees.surgeryFile, department);
                }
                case ManageEmployees.criticalCare -> {
                    dao.criticalCareList.add(addedEmployee);
                    info = addedEmployee.writeInfo();
                    addEmployeeToTextFile(ManageEmployees.criticalCareFile, info);
                    new ManageEmployees(dao.criticalCareList,ManageEmployees.criticalCareFile, department);
                }
            }


            dispose();
            revalidate();
            repaint();
        }

        private void addEmployeeToTextFile(String filePath, String EmployeeInfo) throws IOException {
            FileWriter writer;
            Scanner s;
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

