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
            new ManageEmployees(findDepartmentList());

            }
        }
    }

