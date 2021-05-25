package PresentationLayer;

import BusinessLayer.DeliveryServiceBLL.CompositeProduct;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ProductFrame extends JFrame {

    JLabel idLabel = new JLabel("Id");
    JLabel title = new JLabel("Title");
    JLabel caloriesLabel = new JLabel("Calories");
    JLabel proteinLabel = new JLabel("Protein");
    JLabel fatLabel = new JLabel("Fat");
    JLabel sodiumLabel = new JLabel("Sodium");
    JLabel price = new JLabel("Price");
    JTextField idField = new JTextField();
    JTextField titleField = new JTextField();
    JTextField caloriesField = new JTextField();
    JTextField proteinField = new JTextField();
    JTextField fatField = new JTextField();
    JTextField sodiumField = new JTextField();
    JTextField priceField = new JTextField();
    JButton modifyProduct = new JButton("Modify product");
    JButton addProduct = new JButton("Add product");
    JButton deleteProduct = new JButton("Delete product");
    JButton viewProducts = new JButton("View products");
    JButton addCompositeProd = new JButton("Compose product");
    Container mainPanel;
    JPanel viewPanel = new JPanel();

    public ProductFrame() throws HeadlessException {

        this.setTitle("Product management");
        mainPanel = this.getContentPane();
        mainPanel.setLayout(null);
        mainPanel.setSize(920, 521);

        idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        idLabel.setLocation(20, 20);
        idLabel.setSize(20, 20);
        mainPanel.add(idLabel);

        idField.setLocation(90, 20);
        idField.setSize(105, 20);
        mainPanel.add(idField);

        title.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        title.setLocation(20, 50);
        title.setSize(30, 20);
        mainPanel.add(title);

        titleField.setLocation(90, 50);
        titleField.setSize(105, 20);
        mainPanel.add(titleField);

        caloriesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        caloriesLabel.setLocation(20, 80);
        caloriesLabel.setSize(60, 20);
        mainPanel.add(caloriesLabel);

        caloriesField.setLocation(90, 80);
        caloriesField.setSize(105, 20);
        mainPanel.add(caloriesField);


        proteinLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        proteinLabel.setLocation(20, 110);
        proteinLabel.setSize(60, 20);
        mainPanel.add(proteinLabel);

        proteinField.setLocation(90, 110);
        proteinField.setSize(105, 20);
        mainPanel.add(proteinField);


        fatLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        fatLabel.setLocation(20, 140);
        fatLabel.setSize(60, 20);
        mainPanel.add(fatLabel);

        fatField.setLocation(90, 140);
        fatField.setSize(105, 20);
        mainPanel.add(fatField);

        sodiumLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        sodiumLabel.setLocation(20, 170);
        sodiumLabel.setSize(60, 20);
        mainPanel.add(sodiumLabel);

        sodiumField.setLocation(90, 170);
        sodiumField.setSize(105, 20);
        mainPanel.add(sodiumField);

        price.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        price.setLocation(20, 200);
        price.setSize(60, 20);
        mainPanel.add(price);

        priceField.setLocation(90, 200);
        priceField.setSize(105, 20);
        mainPanel.add(priceField);

        addProduct.setLocation(20, 280);
        addProduct.setSize(150, 30);
        mainPanel.add(addProduct);

        addCompositeProd.setLocation(20, 320);
        addCompositeProd.setSize(150, 30);
        mainPanel.add(addCompositeProd);

        modifyProduct.setLocation(20, 360);
        modifyProduct.setSize(150, 30);
        mainPanel.add(modifyProduct);


        deleteProduct.setLocation(20, 400);
        deleteProduct.setSize(150, 30);
        mainPanel.add(deleteProduct);

        viewProducts.setLocation(20, 440);
        viewProducts.setSize(150, 30);
        mainPanel.add(viewProducts);

        viewPanel.setSize(600, 470);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        viewPanel.setBorder(blackline);
        viewPanel.setLocation(300, 0);
        mainPanel.add(viewPanel);

        mainPanel.setVisible(true);
        this.setVisible(true);
        this.setSize(900, 521);

    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getCaloriesField() {
        return caloriesField;
    }

    public JTextField getProteinField() {
        return proteinField;
    }

    public JTextField getFatField() {
        return fatField;
    }

    public JTextField getSodiumField() {
        return sodiumField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JButton getModifyProduct() {
        return modifyProduct;
    }

    public JButton getAddProduct() {
        return addProduct;
    }

    public JButton getDeleteProduct() {
        return deleteProduct;
    }

    public JButton getViewProducts() {
        return viewProducts;
    }

    public JButton getAddCompositeProd() {
        return addCompositeProd;
    }

    public Container getMainPanel() {
        return mainPanel;
    }

    public JPanel getViewPanel() {
        return viewPanel;
    }

    public List<String> getTextFields() {

        List<String> fields = new ArrayList<>();
        fields.add(getIdField().getText());
        fields.add(getTitleField().getText());
        fields.add(getCaloriesField().getText());
        fields.add(getProteinField().getText());
        fields.add(getFatField().getText());
        fields.add(getSodiumField().getText());
        fields.add(getPriceField().getText());

        return fields;
    }

    public void refreshFields(){
        titleField.setText("");
        fatField.setText("");
        proteinField.setText("");
        idField.setText("");
        caloriesField.setText("");
        sodiumField.setText("");
        priceField.setText("");
    }


}
