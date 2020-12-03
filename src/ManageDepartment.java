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

    Department surgery = new Department("Surgery", 25, "third floor - first ");
    Department anaesthetics = new Department("Anaesthetics", 15, "third floor - second ward");
    Department cardiology = new Department("Cardiology", 10, "second floor - first ward");
    Department criticalCare = new Department("Critical care", 30, "first floor - fifth ward");


    JPanel panel = new JPanel();
    JLabel user = new JLabel("List of Departments");
    JButton searchDepartment = new JButton("Search Department");
    JComboBox listOfDepartments = new JComboBox();

    ManageDepartment(){

        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0, 255, 51));
        panel.add(Box.createRigidArea(new Dimension(100, 80)));
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        panel.add(user);

        panel.add(listOfDepartments);

        panel.add(Box.createRigidArea(new Dimension(100, 15)));

        panel.add(searchDepartment);

        panel.add(Box.createRigidArea(new Dimension(100, 170)));

        listOfDepartments.addItem(surgery.getName());
        listOfDepartments.addItem(anaesthetics.getName());
        listOfDepartments.addItem(cardiology.getName());
        listOfDepartments.addItem(criticalCare.getName());
        listOfDepartments.getAutoscrolls();

        searchDepartment.addActionListener(this);

        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        new ManageDepartment();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchDepartment){
            System.out.println(listOfDepartments.getSelectedItem());
        }
    }
}
