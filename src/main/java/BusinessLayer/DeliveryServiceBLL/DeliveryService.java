package BusinessLayer.DeliveryServiceBLL;

import BusinessLayer.Interfaces.IDelieryServiceProcessing;
import DataLayer.FileImporter;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class DeliveryService extends Observable implements Serializable, IDelieryServiceProcessing {

    private HashMap<Order, List<MenuItem>> orderListMap;
    private List<MenuItem> menuItems; // used for saving all the products provided by the catering company
                                        //  type hashset because hashset does not allow duplicate;
    private List<User> users;
    private List<Client> clients;
    private List<Employee> employees ;

    public DeliveryService() {
        menuItems = new ArrayList<>();
        orderListMap = new HashMap<>();
        users = new ArrayList<>();
        clients = new ArrayList<>();
        employees = new ArrayList<>();
        Collections.addAll(employees, new Employee("Employee","employee"));
        users.addAll(employees);

        for(Employee e: employees){
            addObserver(e);
        }
        addClient(new Client("User","user","FirstUser","Address","123456789"));
        createAdmin("admin", "admin");
        isWellFormed();
    }

    /**
     * Function for invariants of this class
     * The method checks if every id is unique for every list
     * @return
     */
    protected boolean isWellFormed(){

        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<Integer> menuIds = new ArrayList<>();
        ArrayList<String> userIds = new ArrayList<>();
        for(Order order : orderListMap.keySet()){
            if(!idList.contains(order.getOrderId())){
                idList.add(order.getOrderId());
            }else{
                return  false;
            }
        }
        for(MenuItem menuItem : menuItems){
            if(!menuIds.contains(menuItem.getId())){
                menuIds.add(menuItem.getId());
            }else{
                return false;
            }
        }
        for(User user : users){
            if(!userIds.contains(user.getUserName())){
                userIds.add(user.getUserName());
            }else {
                return false;
            }
        }
        return true;
    }
//
    /**
     * Function for importing the products stored in csv file.
     * @pre Checks if the string is null
     * @post Checks if the invariants are stillr respecting the rules.
     * @param file The path of the CSV file
     * @return  A list of items from the menu
     * @throws IOException In case an error occurs while reading the file
     */

    public List<MenuItem> importProducts(String file) throws IOException {

        assert file.isEmpty() == false : "Error in passing file as a parameter0";
        FileImporter fileImporter = new FileImporter();
        List<MenuItem> importedItems = fileImporter.importProducts(file);
        for(MenuItem m : importedItems){
            menuItems.add(m);
        }
        assert isWellFormed() : "Error in importing products.";
        return menuItems;
    }

    /**
     * Function to add a product in the list of the product
     * @pre Checks if the menu item is null
     * @post Checks if the invariants are stillr respecting the rules.
     * @param menuItem A product which will be added to the list of products.
     * @return  The id of the new inserted item.
     */
    public int addProduct(MenuItem menuItem) {
        assert menuItem != null : "Error in adding product";
        menuItems.add(menuItem);
        assert isWellFormed() : "Error in adding products.";
        return menuItem.getId();
    }

    /**
     * Function to modify an entry.
     * @pre Checks if the menu item is null
     * @post Checks if the invariants are still respecting the rules.
     * @param modifiedItem Object with the modified fields
     * @return The id of the edited object.
     */
    public int modifyProduct(MenuItem modifiedItem) {

        assert menuItems != null: "Menu item is null";

        MenuItem item = menuItems.stream().filter(l->  l.getId() == modifiedItem.getId() ).collect(toList()).get(0);
        item.setCalories(modifiedItem.getCalories());
        item.setPrice(modifiedItem.getPrice());
        item.setFats(modifiedItem.getFats());
        item.setProteins(modifiedItem.getProteins());
        item.setTitle(modifiedItem.getTitle());
        item.setSodium(modifiedItem.getSodium());
        assert isWellFormed() : "Error in modifying prod.";
        return modifiedItem.getId();
    }

    /**
     * Function to delete a product
     * @post  Checks if the invariants are still respecting the rules.
     * @param id The id of the product to be deleted
     * @return The id of the element which was deleted.
     */
    public int deleteProduct(int id) {
        MenuItem toBeRemoved = menuItems.stream().filter(t-> t.getId() == id).collect(toList()).get(0);
        menuItems.remove(toBeRemoved);
        assert isWellFormed(): "Error deleting";
        return id;
    }

    /**
     * Function to create a composite product
     * @pre Checks if the parameter menuItem is null
     * @post  Checks if the invariants are still respecting the rules.
     * @param menuItems The list of products which will compose the new product.
     * @return The id of the new composite product.
     */
    public int createProduct(List<MenuItem> menuItems) {
        assert menuItems != null : "Error creating product";
        CompositeProduct compositeProduct = new CompositeProduct(menuItems);
        this.addProduct(compositeProduct);
        assert isWellFormed() :"Error creating product.";
        return compositeProduct.getId();
    }

    /**
     * Function to filter orders regarding a time interval
     * @param time An array representing the starting time and the finishing time
     * @return A list of the orders palced between the starting time and the finishing time
     */
    public List<Order> filterOrdersByTimeInterval( LocalTime[] time) {
        return getOrders().stream().filter(t-> t.getTime().isAfter(time[0]) ).filter(t->t.getTime().isBefore(time[1])).collect(toList());

    }

    /**
     * Function to filter menu items based on the number they were ordered
     * @param numberOfOrders The number of orders
     * @return A list of menu items ordered more than numberOfOrders times
     */
    public List<MenuItem> filterProductsByOrderedNumber(int numberOfOrders){
        List<MenuItem> filteredList = new ArrayList<>();
        filteredList = menuItems.stream().filter(m->m.getTimesOrdered() >= numberOfOrders).collect(toList());
        return  filteredList;
    }

    /**
     * Function to filter the menu items by date and the number they were ordered.
     * @param date The date
     * @param numberOfTimes The number of times the items were ordered
     * @return A list of menu items ordered after the specified date and more than numberOfTimes times.
     */
    public List<MenuItem> filterByDateAndNumberOfOrders(LocalDate date, int numberOfTimes){
        List<MenuItem> filteredList = new ArrayList<>();
        List<MenuItem> result = new ArrayList<>();
        orderListMap.entrySet().stream().filter(entry-> entry.getKey().getOnlyDate().isAfter(date)).forEach(e->e.getValue().stream().filter(k->k.getTimesOrdered() >= numberOfTimes).forEach(k->filteredList.add(k)));
        for(MenuItem m : filteredList){
            if(!result.contains(m)){
                result.add(m);
            }
        }
        return  result;
    }

    /**
     * Function to generate a list of clients.
     * @param count The number of times the client ordered.
     * @param value The value of the order
     * @return A list of clients.
     */
    public List<Client> generateClientsReport(int count, float value){
        List<Client> filteredClients = new ArrayList<>();
        orderListMap.keySet().stream().filter(entry-> entry.getTotalPrice() >= value).forEach(e-> filteredClients.add(getClientById(e.getClientId())));
       return filteredClients.stream().distinct().filter(e-> e.getNumberOfOrders() >= count).collect(toList());
    }

    /**
     * Function to map an order to the corresponding list of products
     *
     * @param order The order to be mapped.
     * @param orderedProducts The list of products ordered.
     */
    public void createNewOrder(Order order, List<MenuItem> orderedProducts) {
        assert order != null && orderedProducts != null:  new JOptionPane("Error", JOptionPane.ERROR_MESSAGE);
        setChanged();
        orderedProducts.forEach(MenuItem::wasOrdered);
        orderListMap.put(order, orderedProducts);
        assert isWellFormed(): "Error";
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void register(Client client) {
        assert client != null;
        addClient(client);
        assert isWellFormed() == false:  new JOptionPane("Error! Username Already taken", JOptionPane.ERROR_MESSAGE);
    }

    /** Function to create administrator
     *
     * @param username The username for the admin
     * @param password The password for the admin
     */
    public void createAdmin(String username, String password){
        users.add( new User( username,password, "admin"));
    }

    public List<User> getUsers() {
        return users;
    }

    /**
     * Function to return a client based on an id passed as parameter
     * @param username The id/username of the client
     * @return The client with the specified username.
     */
    public Client getClientById(String username){
       return clients.stream().filter(client -> client.getUserName().equals(username)).collect(toList()).get(0);
    }

    public MenuItem findItemById(int id){
        return menuItems.stream().filter(m-> m.getId() == id).collect(toList()).get(0);
    }

    /**
     * Function to get an Employee based on id
     * @param username The username of the employee
     * @return An object of type employee with the specified username.
     */

    public Employee getEmployee(String username){
        return employees.stream().filter(employee -> employee.getUserName().equals(username)).collect(toList()).get(0);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Function to add a client in the list of clients.
     * @pre Client is not null
     * @post The invariants are well kept.
     * @param cl The object that needs to be added in the list of clients.
     */
    public void addClient(Client cl){

        assert cl != null : "Null parameter";
        cl.setType("client");
        users.add(cl);
        clients.add(cl);
        assert isWellFormed() == false: "Error in adding client";
    }

    public List<Order> getOrders() {
      return orderListMap.keySet().stream().collect(Collectors.toList());
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
