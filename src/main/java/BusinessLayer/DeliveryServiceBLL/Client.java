package BusinessLayer.DeliveryServiceBLL;

public class Client extends User  {

    private String name;
    private String address;
    private String phoneNumber;
    private int numberOfOrders;

    public Client(String username, String password,String name, String address, String phoneNumber) {
        this.setUserName(username);
        this.setPassword(password);
        this.setType("client");
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Client() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void ordered(){
        numberOfOrders++;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }
}
