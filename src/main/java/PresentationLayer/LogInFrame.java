package PresentationLayer;

import javax.swing.*;
import java.awt.*;

public class LogInFrame extends JFrame {

    JButton logInButton = new JButton("Log in");
    JButton registerButton = new JButton("Register");
    JTextField usernameField = new JTextField(15);
    JPasswordField passwordField = new JPasswordField(15);
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JPanel mainPanel = new JPanel();

    public LogInFrame() throws HeadlessException {
        this.setTitle("LOGIN FRAME");
        this.setSize(335,315);

        mainPanel.setLayout(null);
        mainPanel.setSize(335,315);

        usernameLabel.setBounds(50,30,80,20);
        usernameField.setBounds(130,30,150,20);
        passwordLabel.setBounds(50,60,80,20);
        passwordField.setBounds(130,60,150,20);

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);

        logInButton.setBounds(130,90,100,25);
        mainPanel.add(logInButton);
        JLabel acc = new JLabel("Don't have an account? Register now!");
        acc.setBounds(60,200,350,25);
        mainPanel.add(acc);
        registerButton.setBounds(100,230,120,25);
        mainPanel.add(registerButton);
        mainPanel.setVisible(true);
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public String getUsername(){
        return usernameField.getText();
    }
    public String getPassword(){
        return String.valueOf(passwordField.getPassword());
    }

    public JButton getLogInButton() {
        return logInButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
