package BusinessLayer.DeliveryServiceBLL;

import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;
import java.util.List;

public abstract class MenuItem implements Serializable {

    public static int count = 0 ;
    public static int count2 = 0 ;
    private int id;
    private String title;
    private float rating;
    private float calories;
    private float proteins;
    private float fats;
    private float sodium;
    private float price;
    private int numberOfOrders;

    public MenuItem() {
        count++;
        this.id = count;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float computePrice(){
        return this.price;
    }

    @Override
    public String toString() {
        return   title  +
                ",  price=" + price;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public void wasOrdered(){
        numberOfOrders++;
    }
    public int getTimesOrdered(){
        return numberOfOrders;
    }
    public void resetCount(){
        this.count = 0;
    }
}
