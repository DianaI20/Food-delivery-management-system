package Controller;

import BusinessLayer.DeliveryServiceBLL.Client;
import BusinessLayer.DeliveryServiceBLL.DeliveryService;
import BusinessLayer.DeliveryServiceBLL.Employee;
import BusinessLayer.DeliveryServiceBLL.User;
import DataLayer.Serializator;
import PresentationLayer.EmployeeFrame;
import PresentationLayer.LogInFrame;
import PresentationLayer.RegisterForm;
import org.w3c.dom.ls.LSOutput;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {

    DeliveryService deliveryService;
    AdministratorController administratorController;
    ClientController clientController;
    LogInFrame logInFrame;
    EmployeeController employeeController;
    RegisterForm registerForm;
    Serializator serializator;
    String fileName = "DeliveryService.txt";

    public MainController() {

        serializator = new Serializator(fileName);
        if(new File(fileName).length() == 0){
            System.out.println("Here");
            deliveryService = new DeliveryService();
        }else{
            try {
                deliveryService =  serializator.readFromFile();
                if(deliveryService == null){
                    deliveryService = new DeliveryService();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("CLASSNOTF");
                e.printStackTrace();
            }
        }

    }

    public void start() {
        administratorController = new AdministratorController(deliveryService);
        logInFrame = new LogInFrame();
        logInFrame.getLogInButton().addActionListener(e -> {

        String userName = logInFrame.getUsername();
        String pass = logInFrame.getPassword();

            try {
                chooseFrame(userName,pass);

            }catch(IndexOutOfBoundsException exception){
                JOptionPane.showMessageDialog(null,"Invalid username/password","Error",JOptionPane.ERROR_MESSAGE);
            }
            registerNewClient();
        });


        logInFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                if (JOptionPane.showConfirmDialog(logInFrame, "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    try {
                        serializator.writeInFile(deliveryService);
                        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    logInFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    /**
     * Function for opening a new frame based on the username and password sent as parameters
     * @param userName The username of the specific tyoe of user
     * @param pass The password
     */
    public void chooseFrame(String userName, String pass) throws IndexOutOfBoundsException{
        User  user = deliveryService.getUsers().stream().filter(q -> q.getUserName().equals(userName) && q.getPassword().equals(pass)).collect(Collectors.toList()).get(0);

        if (user != null) {
            switch (user.getType()){
                case "admin":
                    administratorController.startAdminController();
                    break;
                case "client":
                    clientController = new ClientController(deliveryService,deliveryService.getClientById(userName));
                    clientController.startClientController();
                    break;
                case "employee":
                    employeeController = new EmployeeController(deliveryService, deliveryService.getEmployee(user.getUserName()));

                    break;

            }
        }
    }

    /**
     * Function to register a new client
     */
    public void registerNewClient(){
        logInFrame.getRegisterButton().addActionListener(e->{
            registerForm = new RegisterForm();
            registerForm.getSubmitButton().addActionListener(f->{
                deliveryService.addClient(registerForm.getNewClient());
                registerForm.refreshGUI();
                JOptionPane.showMessageDialog(null,"Registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            });
        });
    }

}
