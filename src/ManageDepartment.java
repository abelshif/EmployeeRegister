import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Created by Salah Abdinoor
 * 12/3/2020
 * 4:36 PM
 * AOD2Employee
 * Copyright: MIT
 */
public class ManageDepartment extends JFrame implements ActionListener {

    DAO dao = new DAO();

    Department surgery = new Department("Surgery", dao.surgeryList.size(), "third floor - first ward ");
    Department anaesthetics = new Department("Anaesthetics", dao.anaestheticsList.size(), "third floor - second ward");
    Department cardiology = new Department("Cardiology", dao.cardiologyList.size(), "second floor - first ward");
    Department criticalCare = new Department("Critical Care", dao.criticalCareList.size(), "first floor - fifth ward");

    private final String surgeryCase = "Surgery";
    private final String anaestheticsCase = "Anaesthetics";
    private final String cardiologyCase = "Cardiology";
    private final String criticalCareCase = "Critical Care";

    JPanel panel = new JPanel();
    JLabel label = new JLabel("List of Departments");
    JButton searchDepartment = new JButton("Search Department");
    final JComboBox<String> listOfDepartments = new JComboBox<String>();

    ManageDepartment() {
        setLayout(null);
        add(panel);
        panel.setLayout(null);


        panel.add(label);
        label.setBounds(100, 70, 250, 30);

        panel.add(listOfDepartments);
        listOfDepartments.setBounds(90, 110, 150, 30);



        panel.add(searchDepartment);
        searchDepartment.setBounds(90, 170, 150, 30);
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setBounds(10,10, 315, 345);


        listOfDepartments();

        searchDepartment.addActionListener(this);

        setSize(350, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void listOfDepartments() {
        listOfDepartments.addItem(surgery.getName());
        listOfDepartments.addItem(anaesthetics.getName());
        listOfDepartments.addItem(cardiology.getName());
        listOfDepartments.addItem(criticalCare.getName());

    }

    public List<Employee> findDepartmentList() {

        switch (listOfDepartments.getSelectedItem().toString()) {
            case surgeryCase -> { return dao.surgeryList; }
            case anaestheticsCase -> {  return dao.anaestheticsList; }
            case cardiologyCase -> { return dao.cardiologyList; }
            case criticalCareCase -> {return dao.criticalCareList; }
        }



        return null;

    }

    public static void main(String[] args) {

        new ManageDepartment();
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchDepartment) {

            dispose();
            ManageEmployees m = new ManageEmployees(findDepartmentList());

            }
        }
    }

