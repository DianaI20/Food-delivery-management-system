package PresentationLayer;

import BusinessLayer.DeliveryServiceBLL.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JFrame {
    private Container container;
    private JLabel title;
    private JLabel name;
    private JTextField nameTextField;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberField;
    private JLabel userName;
    private JTextField userNameTextField;
    private JLabel password;
    private JTextField passWordField;
    private JLabel addressLabel;
    private JTextArea addressField;
    private JButton submitButton;


    public RegisterForm() {

        this.setTitle("Registration form");
        this.setBounds(300, 90, 550, 590);
        setResizable(true);
        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Registration Form");
        title.setFont(new Font("Times New Roman ", Font.PLAIN, 30));
        title.setSize(300, 40);
        title.setLocation(150, 30);
        container.add(title);

        userName = new JLabel("Username");
        userName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        userName.setSize(100, 20);
        userName.setLocation(100, 100);
        container.add(userName);

        userNameTextField = new JTextField();
        userNameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        userNameTextField.setSize(190, 20);
        userNameTextField.setLocation(200, 100);
        container.add(userNameTextField);

        password = new JLabel("Password");
        password.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 150);
        container.add(password);

        passWordField = new JTextField();
        passWordField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passWordField.setSize(150, 20);
        passWordField.setLocation(200, 150);
        container.add(passWordField);


        name = new JLabel("Name");
        name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 200);
        container.add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameTextField.setSize(190, 20);
        nameTextField.setLocation(200, 200);
        container.add(nameTextField);

        phoneNumberLabel = new JLabel("Mobile");
        phoneNumberLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        phoneNumberLabel.setSize(100, 20);
        phoneNumberLabel.setLocation(100, 250);
        container.add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        phoneNumberField.setSize(150, 20);
        phoneNumberField.setLocation(200, 250);
        container.add(phoneNumberField);

        addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addressLabel.setSize(100, 20);
        addressLabel.setLocation(100, 300);
        container.add(addressLabel);

        addressField = new JTextArea();
        addressField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        addressField.setSize(200, 75);
        addressField.setLocation(200, 300);
        addressField.setLineWrap(true);
        container.add(addressField);


        submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        submitButton.setSize(130, 30);
        submitButton.setLocation(180, 400);
        container.add(submitButton);

        setVisible(true);
    }
    public Client getNewClient(){
        return new Client(userNameTextField.getText(), passWordField.getText(),nameTextField.getText(), addressField.getText(), phoneNumberField.getText());
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void refreshGUI(){
        nameTextField.setText("");
        passWordField.setText("");
        userNameTextField.setText("");
        phoneNumberField.setText("");
        addressField.setText("");
    }
}
