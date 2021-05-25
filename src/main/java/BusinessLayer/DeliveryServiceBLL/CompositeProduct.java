package BusinessLayer.DeliveryServiceBLL;


import java.awt.*;
import java.util.List;

public class CompositeProduct extends MenuItem {

    private List<MenuItem> menuItems;

    public CompositeProduct(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.setTitle();
        this.computePrice();
        this.computeNutrients();

    }

    public CompositeProduct() {

    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public float computePrice() {
        float price = 0;
        for (MenuItem menuItem : menuItems) {
            price += menuItem.computePrice();
        }
        this.setPrice(price);
        return price;
    }

    public void computeNutrients() {
        float calories = 0;
        float proteins = 0;
        float fats = 0;
        float sodium = 0;

        for (MenuItem menuItem : menuItems) {
            calories += menuItem.getCalories();
            fats += menuItem.getFats();
            proteins += menuItem.getProteins();
            sodium += menuItem.getSodium();
        }
        this.setCalories(calories);
        this.setProteins(proteins);
        this.setFats(fats);
        this.setSodium(sodium);
        this.setRating(0);
    }

    public void setTitle(){
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        for( i = 0; i < menuItems.size() -1 ; i++){
            stringBuilder.append(menuItems.get(i).getTitle()+" and ");
        }
        stringBuilder.append(menuItems.get(i).getTitle());
    this.setTitle(stringBuilder.toString());
    }

}
