package LoginSystem;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    JFrame mainFrame;
    JPanel homePagePanel;

    public HomePage (){

        mainFrame = new JFrame();
        homePagePanel = new JPanel();

        homePagePanel.setLayout(null);
        homePagePanel.setVisible(true);
        homePagePanel.setBackground(Color.WHITE);

        add(homePagePanel);
        setTitle("Home page");
        setPreferredSize(new Dimension(450, 450));
        setBackground(Color.BLUE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
