import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    String loginName[] = {"HR", "Surgery", "Anaesthetics", "Cardiology", "Critical care"};
    JComboBox loginComboBox = new JComboBox(loginName);


    public Login() {
        setLayout(null);
        loginPanel.setLayout(null);
        loginPanel.setVisible(true);
        //loginPanel.setBackground(Color.cyan);

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
        setPreferredSize(new Dimension(450, 400));
        setBackground(Color.BLUE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (userNameText.getText().equalsIgnoreCase("hr") && passwordField.getText().equals("hr") &&
                loginComboBox.getSelectedItem().toString().equals("HR")) {
            dispose();
            ManageDepartment MD = new ManageDepartment();
        }
        else if (userNameText.getText().equals("surgery") && passwordField.getText().equals("surgery") &&
                loginComboBox.getSelectedItem().toString().equals("Surgery")){
           dispose();
            ManageEmployees ME = new ManageEmployees(dao.surgeryList);
        }
        else if (userNameText.getText().equals("anaesthetics") && passwordField.getText().equals("anaesthetics") &&
                loginComboBox.getSelectedItem().toString().equals("Anaesthetics")){
          dispose();
            ManageEmployees ME = new ManageEmployees(dao.anaestheticsList);
        }
        else if (userNameText.getText().equals("cardiology") && passwordField.getText().equals("cardiology") &&
                loginComboBox.getSelectedItem().toString().equals("Cardiology")){
          dispose();
            ManageEmployees ME = new ManageEmployees(dao.cardiologyList);
        }
        else if (userNameText.getText().equals("criticalcare") && passwordField.getText().equals("criticalcare") &&
                loginComboBox.getSelectedItem().toString().equals("Critical care")){
            dispose();
            ManageEmployees ME = new ManageEmployees(dao.criticalCareList);
        }
        else
            JOptionPane.showMessageDialog(this, "Felaktlig login information");

    }


    public static void main(String[] args) {
        new Login();
    }

}
