package DataLayer;

import BusinessLayer.DeliveryServiceBLL.BaseProduct;
import BusinessLayer.DeliveryServiceBLL.DeliveryService;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class Serializator<T> {

    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    String fileName;
    public Serializator(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Function to serialize an object of type deliveryService
     * @param object The deliveryService object
     * @throws IOException
     */
    public void writeInFile(DeliveryService object) throws IOException {
        outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        outputStream.writeObject(object);
        outputStream.close();
    }

    /**
     * Function to deserialize an object of type deliveryService
     * @return An object of type deliveryService after it was read from the file.
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public DeliveryService readFromFile() throws IOException, ClassNotFoundException {
        inputStream = new ObjectInputStream(new FileInputStream(fileName));
        return ((DeliveryService) inputStream.readObject());

    }

}
