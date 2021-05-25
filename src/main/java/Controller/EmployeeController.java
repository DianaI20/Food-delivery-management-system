package Controller;

import BusinessLayer.DeliveryServiceBLL.DeliveryService;
import BusinessLayer.DeliveryServiceBLL.Employee;
import PresentationLayer.EmployeeFrame;

public class EmployeeController {
    EmployeeFrame employeeFrame;
    DeliveryService deliveryService;
    Employee employee;

    public EmployeeController(DeliveryService deliveryService, Employee employee) {
        this.deliveryService = deliveryService;
        this.employee = employee;
        employee.getEmployeeFrame().startFrame(employee);
    }

}

