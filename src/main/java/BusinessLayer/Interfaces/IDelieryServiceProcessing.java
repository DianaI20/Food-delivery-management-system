package BusinessLayer.Interfaces;

import BusinessLayer.DeliveryServiceBLL.BaseProduct;
import BusinessLayer.DeliveryServiceBLL.Client;
import BusinessLayer.DeliveryServiceBLL.MenuItem;
import BusinessLayer.DeliveryServiceBLL.Order;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public interface IDelieryServiceProcessing {

    List<MenuItem> importProducts(String fileName) throws IOException;

    int addProduct(MenuItem menuItem);

    int modifyProduct(MenuItem menuItem);

    int deleteProduct(int id);

    int createProduct(List<MenuItem> menuItems);
    List<Order> filterOrdersByTimeInterval(LocalTime[] time);


    void createNewOrder(Order order, List<MenuItem> orderedProducts);

    void register(Client client);


}
