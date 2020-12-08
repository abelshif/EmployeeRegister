import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List.*;


public class Login extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
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

        loginPanel.setLayout(null);
        loginPanel.setVisible(true);
        loginPanel.setBackground(Color.CYAN);

        welcomeLabel.setBounds(50, 75, 250, 30);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 200, 100, 30);
        loginLabel.setBounds(50, 250, 100, 30);

        userNameText.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 200, 150, 30);
        loginComboBox.setBounds(150, 250, 150, 30);

        loginButton.setBounds(150, 300, 150, 30);
        loginButton.addActionListener(this);

        loginPanel.add(welcomeLabel);
        loginPanel.add(userLabel);
        loginPanel.add(passwordLabel);
        loginPanel.add(userNameText);
        loginPanel.add(passwordField);
        loginPanel.add(loginLabel);
        loginPanel.add(loginComboBox);
        loginPanel.add(loginButton);

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

        if (userNameText.getText().equals("hr") && passwordField.getText().equals("hr") &&
                loginComboBox.getSelectedItem().toString().equals("HR")) {
            frame.dispose();
            ManageDepartment MD = new ManageDepartment();
        }
        else if (userNameText.getText().equals("surgery") && passwordField.getText().equals("surgery") &&
                loginComboBox.getSelectedItem().toString().equals("Surgery")){
            frame.dispose();
            ManageEmployees ME = new ManageEmployees(dao.surgeryList);
        }
        else if (userNameText.getText().equals("anaesthetics") && passwordField.getText().equals("anaesthetics") &&
                loginComboBox.getSelectedItem().toString().equals("Anaesthetics")){
            frame.dispose();
            ManageEmployees ME = new ManageEmployees(dao.anaestheticsList);
        }
        else if (userNameText.getText().equals("cardiology") && passwordField.getText().equals("cardiology") &&
                loginComboBox.getSelectedItem().toString().equals("Cardiology")){
            frame.dispose();
            ManageEmployees ME = new ManageEmployees(dao.cardiologyList);
        }
        else if (userNameText.getText().equals("criticalcare") && passwordField.getText().equals("criticalcare") &&
                loginComboBox.getSelectedItem().toString().equals("Critical care")){
            frame.dispose();
            ManageEmployees ME = new ManageEmployees(dao.criticalCareList);
        }
        else
            JOptionPane.showMessageDialog(frame, "Felaktlig login information");

    }

    public static void main(String[] args) {
        new Login();
    }

}
