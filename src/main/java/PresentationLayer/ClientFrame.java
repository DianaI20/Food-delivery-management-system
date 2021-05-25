package PresentationLayer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ClientFrame extends JFrame {

    JTextField searchField = new JTextField();
    JButton searchButton = new JButton("Search");
    JButton viewAllProducts = new JButton("View all products");
    JPanel cartPanel = new JPanel();
    JButton finishOrder = new JButton("Finish order");
    JPanel productPanel = new JPanel();
    JButton deleteProduct = new JButton("Delete Product");
    JButton addToCart = new JButton("Add to cart");
    Container mainPanel;

    public ClientFrame() throws HeadlessException {

        this.setTitle("Proceeding order");
        mainPanel = this.getContentPane();
        mainPanel.setLayout(null);
        mainPanel.setSize(1400,800);
        this.setSize(1150,660);

        JLabel search = new JLabel("Search for products...");
        search.setSize(200,20);
        search.setLocation(20,10);
        mainPanel.add(search);

        searchField.setSize(200,20);
        searchField.setLocation(20,30);
        mainPanel.add(searchField);

        searchButton.setSize(100,20);
        searchButton.setLocation(230,30);
        mainPanel.add(searchButton);


        cartPanel.setSize(500,500);
        cartPanel.setLocation(600,60);
        cartPanel.setVisible(true);
        Border blackline = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Added to cart", TitledBorder.CENTER, TitledBorder.ABOVE_TOP);
        cartPanel.setBorder(blackline);

        mainPanel.add(cartPanel);
        addToCart.setSize(120,20);
        addToCart.setLocation(600,580);
        mainPanel.add(addToCart);

        deleteProduct.setSize(120,20);
        deleteProduct.setLocation(750,580);
        mainPanel.add(deleteProduct);

        finishOrder.setSize(120,20);
        finishOrder.setLocation(900,580);
        mainPanel.add(finishOrder);
        Border blackline2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Products", TitledBorder.CENTER, TitledBorder.ABOVE_TOP);
        productPanel.setBorder(blackline2);
        productPanel.setSize(500,500);
        productPanel.setLocation(20,60);
        mainPanel.add(productPanel);

        mainPanel.setVisible(true);
        this.setVisible(true);
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getViewAllProducts() {
        return viewAllProducts;
    }

    public JPanel getCartPanel() {
        return cartPanel;
    }

    public JButton getFinishOrder() {
        return finishOrder;
    }

    public JPanel getProductPanel() {
        return productPanel;
    }

    public JButton getAddToCart() {
        return addToCart;
    }

    public String getSearchedString(){
        return searchField.getText();
    }

    public JButton getDeleteProduct() {
        return deleteProduct;
    }
}
