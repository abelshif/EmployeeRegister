import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List.*;


public class Login extends JFrame implements ActionListener {

    JPanel loginPanel = new JPanel();


    DAO dao = new DAO();

    JLabel welcomeLabel = new JLabel("Welcome to Employee Register System!");
    JLabel userLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel loginLabel = new JLabel("Login as:");

    JTextField userNameText = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    JButton loginButton = new JButton("LOGIN");
    String loginName[] = {"HR", "Head Of Department"};
    JComboBox loginComboBox = new JComboBox(loginName);


    public Login() throws FileNotFoundException {
        setLayout(null);
        loginPanel.setLayout(null);
        loginPanel.setVisible(true);


        welcomeLabel.setBounds(100, 25, 250, 30);
        userLabel.setBounds(100, 90, 100, 30);
        passwordLabel.setBounds(100, 140, 100, 30);
        loginLabel.setBounds(100, 190, 100, 30);

        userNameText.setBounds(170, 90, 150, 30);
        passwordField.setBounds(170, 140, 150, 30);
        loginComboBox.setBounds(170, 190, 150, 30);

        loginButton.setBounds(170, 250, 150, 30);
        loginButton.addActionListener(this);

        loginPanel.add(welcomeLabel);
        loginPanel.add(userLabel);
        loginPanel.add(passwordLabel);
        loginPanel.add(userNameText);
        loginPanel.add(passwordField);
        loginPanel.add(loginLabel);
        loginPanel.add(loginComboBox);
        loginPanel.add(loginButton);

        loginPanel.setBorder(BorderFactory.createEtchedBorder());
        loginPanel.setBounds(10,10, 415, 345);
        add(loginPanel);
        setTitle("Login page");
        setPreferredSize(new Dimension(450, 450));
        setBackground(Color.BLUE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (userNameText.getText().equalsIgnoreCase("hr") && passwordField.getText().equalsIgnoreCase("hr") &&
                loginComboBox.getSelectedItem().toString().equals("HR")) {
            dispose();
            try {
                ManageDepartment MD = new ManageDepartment();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        else if (userNameText.getText().equalsIgnoreCase("surgery") && passwordField.getText().equalsIgnoreCase("surgery") &&
                loginComboBox.getSelectedItem().toString().equals("Head Of Department")){
            dispose();
            new ManageEmployees(dao.surgeryList);
        }
        else if (userNameText.getText().equalsIgnoreCase("anaesthetics") && passwordField.getText().equalsIgnoreCase("anaesthetics") &&
                loginComboBox.getSelectedItem().toString().equals("Head Of Department")){
            dispose();
            new ManageEmployees(dao.anaestheticsList);
        }
        else if (userNameText.getText().equalsIgnoreCase("cardiology") && passwordField.getText().equalsIgnoreCase("cardiology") &&
                loginComboBox.getSelectedItem().toString().equals("Head Of Department")){
            dispose();
            new ManageEmployees(dao.cardiologyList);
        }
        else if (userNameText.getText().equalsIgnoreCase("criticalcare") && passwordField.getText().equalsIgnoreCase("criticalcare") &&
                loginComboBox.getSelectedItem().toString().equals("Head Of Department")){
            dispose();
            new ManageEmployees(dao.criticalCareList);
        }
        else
            JOptionPane.showMessageDialog(this, "Felaktlig login information");

    }

    public static void main(String[] args) throws FileNotFoundException {
        new Login();
    }

}
