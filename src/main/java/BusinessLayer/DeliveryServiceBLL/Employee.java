package BusinessLayer.DeliveryServiceBLL;

import PresentationLayer.EmployeeFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Employee extends User implements Observer  {

    private List<String> notifications;
    private EmployeeFrame employeeFrame;

    @Override
    public void update(Observable o, Object arg) {
        notifications.add((String) arg);
        if(employeeFrame != null){
            employeeFrame.updatePanel(notifications);
        }

    }
    public List<String> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }

    public Employee() {

    }

    public EmployeeFrame getEmployeeFrame() {
        return employeeFrame;
    }

    public Employee(String username, String password) {
        employeeFrame = new EmployeeFrame();
        notifications = new ArrayList<>();
        this.setType("employee");
        this.setUserName(username);
        this.setPassword(password);
    }

    public void setEmployeeFrame(EmployeeFrame employeeFrame) {
        this.employeeFrame = employeeFrame;
    }
}
