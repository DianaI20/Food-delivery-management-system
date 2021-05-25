package BusinessLayer.DeliveryServiceBLL;

import com.opencsv.bean.CsvBindByPosition;

import java.awt.*;
import java.io.Serializable;

public class BaseProduct extends MenuItem {

    private String title;
    private float rating;
    private float calories;
    private float proteins;
    private float fats;
    private float sodium;
    private float price;

    public BaseProduct(String title, float calories, float proteins, float fats, float sodium, float price) {
        this.setTitle(title);
        this.setCalories(calories);
        this.setProteins(proteins);
        this.setFats(fats);
        this.setSodium(sodium);
        this.setPrice(price);
    }
    public BaseProduct(MenuItem baseProduct) {
        this.setTitle(baseProduct.getTitle());
        this.setCalories(baseProduct.getCalories());
        this.setProteins(baseProduct.getProteins());
        this.setFats(baseProduct.getFats());
        this.setSodium(baseProduct.getSodium());
        this.setPrice(baseProduct.getPrice());
        this.setRating(baseProduct.getRating());
    }
    public BaseProduct() {
    }

    @Override
    public float computePrice() {
        return super.computePrice();
    }
}
