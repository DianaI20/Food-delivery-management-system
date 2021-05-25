package PresentationLayer;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame{

    Container mainPanel = new Container();
    JButton manageProducts = new JButton("Manage products");
    JButton generateRepo = new JButton("Generate reports");
    JButton importCSV = new JButton("Import CSV file");

    public AdminFrame() throws HeadlessException {
       this.setTitle("Administrator");
        mainPanel = this.getContentPane();
        mainPanel.setSize(350 ,355);
        mainPanel.setLayout(null);

        JLabel welcome = new JLabel( "Welcome!");
        welcome.setFont(new Font("Times New Roman", Font.BOLD, 25));
        welcome.setSize(150,34);
        welcome.setLocation(120,20);
        mainPanel.add(welcome);

        manageProducts.setSize(200,40);
        manageProducts.setLocation(68,100);
        this.add(manageProducts);

        importCSV.setSize(200,40);
        importCSV.setLocation(68,150);
        this.add(importCSV);

        generateRepo.setSize(200,40);
        generateRepo.setLocation(68,200);
        this.add(generateRepo);

        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.setSize(350,355);
        this.setResizable(false);
    }

    public JButton getManageProducts() {
        return manageProducts;
    }

    public JButton getGenerateRepo() {
        return generateRepo;
    }

    public JButton getImportCSV() {
        return importCSV;
    }


}
