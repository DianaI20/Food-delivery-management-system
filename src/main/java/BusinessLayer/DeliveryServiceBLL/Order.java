package BusinessLayer.DeliveryServiceBLL;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Order implements Serializable {

    private int orderId;
    private static int count = 0;
    private String clientId;
    private LocalDateTime date;
    private float totalPrice;

    public Order(String clientId) {
        count++;
        this.date = LocalDateTime.now();
        this.orderId = count;
        this.clientId = clientId;
    }

    public Order() {

    }
    @Override
    public int hashCode() {
        return  (orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clientId='" + clientId + '\'' +
                ", date=" + date +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOnlyDate() {
        return LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
    }
    public LocalDateTime getDate(){
        return  date;
    }

    public LocalTime getTime(){
        return LocalTime.of(date.getHour(),date.getSecond());
    }


}
