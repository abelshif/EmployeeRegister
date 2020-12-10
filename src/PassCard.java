import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PassCard extends JFrame {

    public PassCard(JTable table, DefaultTableModel tabelmodel) {
        super("Employee Id Card");

        setLayout(null);
        JPanel employeePanel=new JPanel();
        employeePanel.setLayout(null);

        JLabel face = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new File("src/Face.png"));
            ImageIcon icon = new ImageIcon(img);
            face = new JLabel(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        face.setBounds(15,15,130,140);
        add(face);

        JLabel employeeNameLabel= new JLabel("Full Name");
        JLabel employeeName= new JLabel(tabelmodel.getValueAt(table.getSelectedRow(), 0).toString() + " " +
                tabelmodel.getValueAt(table.getSelectedRow(), 1).toString());
        JLabel birthDateLabel= new JLabel("Date of Birth");
        JLabel birthDate= new JLabel(tabelmodel.getValueAt(table.getSelectedRow(), 3).toString());
        JLabel genderLabel= new JLabel("Gender");
        JLabel gender= new JLabel(tabelmodel.getValueAt(table.getSelectedRow(), 2).toString());
        JLabel departmentLabel= new JLabel("Department");
        JLabel department= new JLabel(tabelmodel.getValueAt(table.getSelectedRow(), 6).toString());
        JLabel roleLabel= new JLabel("Gender");
        JLabel role= new JLabel(tabelmodel.getValueAt(table.getSelectedRow(), 7).toString());
        employeeNameLabel.setBounds(150,10, 150, 50);
        employeeName.setBounds(230,10, 150, 50);
        employeePanel.add(employeeNameLabel);
        employeePanel.add(employeeName);
        birthDateLabel.setBounds(150,30, 100, 50);
        birthDate.setBounds(230,30, 100, 50);
        employeePanel.add(birthDateLabel);
        employeePanel.add(birthDate);

        employeePanel.add(genderLabel);
        employeePanel.add(gender);
        genderLabel.setBounds(150,50, 100, 50);
        gender.setBounds(230,50, 100, 50);
        employeePanel.add(departmentLabel);
        employeePanel.add(department);
        departmentLabel.setBounds(150,70, 100, 50);
        department.setBounds(230,70, 100, 50);
        employeePanel.add(roleLabel);
        employeePanel.add(role);
        roleLabel.setBounds(150,90, 100, 50);
        role.setBounds(230,90, 100, 50);

        employeePanel.setBorder(BorderFactory.createEtchedBorder());
        employeePanel.setBounds(10,10, 380, 190);
        add(employeePanel);
        setSize(420, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }






}
