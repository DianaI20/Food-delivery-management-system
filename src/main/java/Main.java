
import BusinessLayer.DeliveryServiceBLL.Client;
import BusinessLayer.DeliveryServiceBLL.DeliveryService;
import BusinessLayer.DeliveryServiceBLL.MenuItem;
import BusinessLayer.DeliveryServiceBLL.User;
import Controller.AdministratorController;
import Controller.ClientController;
import Controller.MainController;
import PresentationLayer.*;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, CsvException, ClassNotFoundException {

        MainController mainController = new MainController();
        mainController.start();
    }

}
