package PresentationLayer;

import BusinessLayer.DeliveryServiceBLL.User;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class EmployeeFrame extends JFrame {

    Container mainPanel;
    JLabel name = new JLabel("Name:");
    User user;
    JPanel notificationPanel;

    public EmployeeFrame() throws HeadlessException {

    }
    public void startFrame(User user){
        this.user = user;
        this.setTitle("Employee");
        this.setSize(500,470);
        notificationPanel = new JPanel();
        mainPanel = this.getContentPane();
        mainPanel.setSize(350 ,355);
        mainPanel.setLayout(null);

        name.setSize(60,40);
        name.setLocation(10,0);
        this.add(name);

        notificationPanel.setSize(400,300);
        notificationPanel.setLocation(20,100);
        notificationPanel.setVisible(true);
        Border blackline = (BorderFactory.createLineBorder(Color.black));
        notificationPanel.setBorder(blackline);
        mainPanel.add(notificationPanel);

        JLabel userName = new JLabel(user.getUserName());
        userName.setSize(200,40);
        userName.setLocation(60,0);
        this.add(userName);
        this.setVisible(true);
    }
    public void updatePanel( List<String> notifications){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(0,1));
        for(String notif : notifications){
            JTextField textField = new JTextField();
            textField.setText(notif);
            newPanel.add(textField);
        }
        notificationPanel.removeAll();
        notificationPanel.add(newPanel);
        notificationPanel.revalidate();
        notificationPanel.repaint();
    }
    public void setUser(User user) {
        this.user = user;
    }
}
