import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Salah Abdinoor
 * 12/3/2020
 * 4:36 PM
 * AOD2Employee
 * Copyright: MIT
 */
public class ManageDepartment extends JFrame implements ActionListener {

    Department surgery = new Department("Surgery", 25, "third floor - first ward ");
    Department anaesthetics = new Department("Anaesthetics", 15, "third floor - second ward");
    Department cardiology = new Department("Cardiology", 10, "second floor - first ward");
    Department criticalCare = new Department("Critical care", 30, "first floor - fifth ward");

    private final String surgeryCase = "Surgery";
    private final String anaestheticsCase = "Anaesthetics";
    private final String cardiologyCase = "Cardiology";
    private final String criticalCareCase = "Critical care";


    JPanel panel = new JPanel();
    JLabel label = new JLabel("List of Departments");
    JButton searchDepartment = new JButton("Search Department");
    final JComboBox<String> listOfDepartments = new JComboBox<String>();

    ManageDepartment() {

        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0, 255, 51));
        panel.add(Box.createRigidArea(new Dimension(100, 80)));
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        panel.add(label);

        panel.add(listOfDepartments);

        panel.add(Box.createRigidArea(new Dimension(100, 15)));

        panel.add(searchDepartment);

        panel.add(Box.createRigidArea(new Dimension(100, 170)));

        // Listan med avdelnigar
        listOfDepartments();

        searchDepartment.addActionListener(this);

        setSize(350, 500);
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

    public static void main(String[] args) {

        new ManageDepartment();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchDepartment) {

            switch (listOfDepartments.getSelectedItem().toString()) {
                case surgeryCase -> { System.out.println("Name: " + surgery.getName() + "\nLocation: " + surgery.getLocation() + "\nNumber of employees: " + surgery.getNumberOfEmployees() + "\n"); }
                case anaestheticsCase -> { System.out.println("Name: " + anaesthetics.getName() + "\nLocation: " + anaesthetics.getLocation() + "\nNumber of employees: " + anaesthetics.getNumberOfEmployees() + "\n"); }
                case cardiologyCase -> { System.out.println("Name: " + cardiology.getName() + "\nLocation: " + cardiology.getLocation() + "\nNumber of employees: " + cardiology.getNumberOfEmployees() + "\n"); }
                case criticalCareCase -> { System.out.println("Name: " + criticalCare.getName() + "\nLocation: " + criticalCare.getLocation() + "\nNumber of employees: " + criticalCare.getNumberOfEmployees() + "\n"); }
            }

            }
        }
    }

