package DataLayer;

import BusinessLayer.DeliveryServiceBLL.Client;
import BusinessLayer.DeliveryServiceBLL.MenuItem;
import BusinessLayer.DeliveryServiceBLL.Order;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Bill {

    private static int index;

    public void printBill(Order order, Client client, List<MenuItem> menuItems){
        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            FileWriter myWriter = new FileWriter("BillNo" + index + ".txt");
            myWriter.write("Order id: " + order.getOrderId() + "\n");
            myWriter.write("Placed at: " + dtf.format(order.getDate()) + "\n");
            myWriter.write("Client details:\n" );
            myWriter.write("Name:" + client.getName() + "\n");
            myWriter.write("Address:" + client.getAddress() + "\n");
            myWriter.write("Phone number: " + client.getPhoneNumber() + "\n");
            myWriter.write("Products ordered:\n");
            for(MenuItem m : menuItems){
                myWriter.write( m+ "\n");
            }
            myWriter.write("Total: " + order.getTotalPrice() + "\n");
            myWriter.close();
            index++;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in printing the receipt.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
