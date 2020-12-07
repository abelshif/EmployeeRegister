import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManageEmployees  {
    private JFrame frame = new JFrame("Employee Register");
    private JLabel label1,label2,label3,label4,label5,label6,label7,label8;
   private JTextField textField;
    private JButton button1,button2,button3,button4;
    //private Dimension dimension;
    private JTable table;
    private Object[][]employeeInfo={{"Meron","","","","","","",""},{"Christian","","","","","","",""}};
    private String[] columnnames={"Name","Sur Name","Gender","Birth date","Tel.no","Salary","Department","Role"};
    private JScrollPane scrollPane;
    private DefaultTableModel tabelmodel;


    public ManageEmployees(){
        frame.setLayout(null);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocationRelativeTo(null);
        table= new JTable();
       // table.setBounds(30, 40, 200, 300);
        tabelmodel=new DefaultTableModel(employeeInfo,columnnames);
        table.setModel(tabelmodel);

        scrollPane = new JScrollPane(table);
        scrollPane.setSize(700,270);
        scrollPane.setLocation(50,50);
        frame.add(scrollPane);


        button1= new JButton("Add");
        button1.setSize(100,30);
        button1.setLocation(50,370);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame= new EditWindow();

            }
        });
        frame.add(button1);


        button2= new JButton("Delete");
        button2.setSize(100,30);
        button2.setLocation(250,370);
        //button2.addActionListener(new ());
        frame.add(button2);

        button3= new JButton("Uppdate");
        button3.setSize(100,30);
        button3.setLocation(450,370);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame uppdateFrame=new EditWindow();
            }
        });
        frame.add(button3);




        label1 = new JLabel("Search");
        label1.setSize(200,30);
        label1.setLocation(550,8);
        frame.add(label1);

        textField= new JTextField(10);
        textField.setSize(150,20);
        textField.setLocation(600,15);
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                String textToSearch = textField.getText();
                Object[][] filteredEmployeeInfo = getFilteredEmployeeInfo(textToSearch, employeeInfo);
                tabelmodel=new DefaultTableModel(filteredEmployeeInfo,columnnames);
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



}

    private Object[][] getFilteredEmployeeInfo(String textToSearch, Object[][] employeeInfo) {
        if(textToSearch.isEmpty()) {
            return employeeInfo;
        }
        Object[][] filteredEmployeeInfo = {{"Christian","","","","","","",""}};
        return filteredEmployeeInfo;
    }

    public static void main(String[] args) {
       new ManageEmployees();

    }

    public class EditWindow extends JFrame {
        public EditWindow() throws HeadlessException {
            JLabel addName= new JLabel("Namn");
            JLabel addSurName=new JLabel("Sur Name");
            JLabel addGender=new JLabel("Gender");
            JLabel addBirthDate=new JLabel("Birth date");
            JLabel addTelNo=new JLabel("Tel.no");
            JLabel addSalary=new JLabel("Salary");
            JLabel addDepartment=new JLabel("Department");
            JLabel addRole=new JLabel("Role");

            JButton buttonSave,buttonCancel;
            addName.setSize(80,30);
            addSurName.setSize(80,30);
            addGender.setSize(80,30);
            addBirthDate.setSize(80,30);
            addTelNo.setSize(80,30);
            addSalary.setSize(80,30);
            addDepartment.setSize(80,30);
            addRole.setSize(80,30);
            addName.setLocation(10,40);
            addSurName.setLocation(10,80);
            addGender.setLocation(10,120);
            addBirthDate.setLocation(10,160);
            addTelNo.setLocation(10,200);
            addSalary.setLocation(10,240);
            addDepartment.setLocation(10,280);
            addRole.setLocation(10,320);

            JTextField namnField= new JTextField(50);
            JTextField surNameField= new JTextField(50);
            JTextField genderField= new JTextField(50);
            JTextField birthDateField= new JTextField(50);
            JTextField telNoField= new JTextField(50);
            JTextField salaryField= new JTextField(50);
            JTextField departmentField= new JTextField(50);
            JTextField roleField= new JTextField(50);





            namnField.setSize(200,30);
            surNameField.setSize(200,30);
            genderField.setSize(200,30);
            birthDateField.setSize(200,30);
            telNoField.setSize(200,30);
            salaryField.setSize(200,30);
            departmentField.setSize(200,30);
            roleField.setSize(200,30);
            namnField.setLocation(80,50);
            surNameField.setLocation(80,90);
            genderField.setLocation(80,130);
            birthDateField.setLocation(80,170);
            telNoField.setLocation(80,210);
            salaryField.setLocation(80,250);
            departmentField.setLocation(80,290);
            roleField.setLocation(80,330);
            getContentPane().setBackground(new Color(136, 0, 255));
            setLayout(null);
            setSize(350,500);



            //addFrame.setDefaultCloseOperation(addFrame.EXIT_ON_CLOSE);
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
            buttonSave= new JButton("Save");
            buttonCancel=new JButton("Cancel");
            buttonSave.setSize(80,30);
            buttonSave.setLocation(40,400);
            buttonCancel.setSize(80,30);
            buttonCancel.setLocation(200,400);
            add(buttonSave);
            add(buttonCancel);

        }
    }
}