package PresentationLayer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class ReportFrame extends JFrame {


    private JComboBox startHour;
    private JComboBox endHour;
    private Container mainPanel;
    private JButton orderRep = new JButton("Time interval.");
    private JButton clientRep = new JButton("Number of orders & value of the order");
    private JButton productsOrdered = new JButton("Number of times they were ordered");
    private JButton productsDayOrdered = new JButton("Day");
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JTextField quantity;
    private JTextField priceField;
    private JPanel reportPanel;

    private String hours[] = {"00", "01", "02", "03", "04", "5",
            "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23"};
    private String dates[]
            = {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};
    private String months[]
            = {"Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec"};
    private String years[];


    public ReportFrame() throws HeadlessException {
        this.setTitle("Reports Frame");
        this.setSize(1115, 600);
        mainPanel = this.getContentPane();
        mainPanel.setLayout(null);

        JLabel timeInterval = new JLabel("Set time interval: ");
        timeInterval.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        timeInterval.setSize(200, 20);
        timeInterval.setLocation(20, 20);
        mainPanel.add(timeInterval);

        startHour = new JComboBox(hours);
        startHour.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        startHour.setSize(50, 20);
        startHour.setLocation(170, 20);
        mainPanel.add(startHour);

        JLabel to = new JLabel("to");
        to.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        to.setSize(50, 20);
        to.setLocation(240, 20);
        mainPanel.add(to);

        endHour = new JComboBox(hours);
        endHour.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        endHour.setSize(50, 20);
        endHour.setLocation(270, 20);
        mainPanel.add(endHour);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        dateLabel.setSize(100, 20);
        dateLabel.setLocation(20, 60);
        mainPanel.add(dateLabel);

        years = new String[100];
        int j = 0;
        for (int i = 2000; i < 2020; i++) {
            years[j] = Integer.toString(i);
            j++;
        }
        date = new JComboBox(dates);
        date.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(170, 60);
        mainPanel.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(220, 60);
        mainPanel.add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(280, 60);
        mainPanel.add(year);


        JLabel moreThan = new JLabel("Ordered more than: ");
        moreThan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        moreThan.setSize(200, 20);
        moreThan.setLocation(20, 100);
        mainPanel.add(moreThan);

        quantity = new JTextField();
        quantity.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        quantity.setSize(60, 20);
        quantity.setLocation(170, 100);
        mainPanel.add(quantity);


        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        priceLabel.setSize(200, 20);
        priceLabel.setLocation(20, 140);
        mainPanel.add(priceLabel);

        priceField = new JTextField();
        priceField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        priceField.setSize(60, 20);
        priceField.setLocation(170, 140);
        mainPanel.add(priceField);

        orderRep.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        orderRep.setSize(300, 30);
        orderRep.setLocation(20, 180);
        mainPanel.add(orderRep);

        clientRep.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        clientRep.setSize(300, 30);
        clientRep.setLocation(20, 210);
        mainPanel.add(clientRep);

        productsOrdered.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        productsOrdered.setSize(300, 30);
        productsOrdered.setLocation(20, 240);
        mainPanel.add(productsOrdered);


        productsDayOrdered.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        productsDayOrdered.setSize(300, 30);
        productsDayOrdered.setLocation(20, 270);
        mainPanel.add(productsDayOrdered);


        reportPanel = new JPanel();
        reportPanel.setSize(600, 470);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        reportPanel.setBorder(blackline);
        reportPanel.setLocation(400, 20);
        mainPanel.add(reportPanel);


        this.setVisible(true);
    }

    public LocalTime[] getTimeIntervals() {
        LocalTime[] l = new LocalTime[2];
        l[0] =LocalTime.of(Integer.parseInt(startHour.getSelectedItem().toString()), 0);
        l[1] =LocalTime.of( Integer.parseInt(endHour.getSelectedItem().toString()),0);
        return l;
    }

    public LocalDate getDate()throws NumberFormatException {
        return LocalDate.of(Integer.parseInt(year.getSelectedItem().toString()),month.getSelectedIndex() + 1,Integer.parseInt(date.getSelectedItem().toString()));
    }
    public float getPrice() throws NumberFormatException{
        return  Float.parseFloat(priceField.getText());
    }

    public JButton getOrderRep() {
        return orderRep;
    }

    public void setOrderRep(JButton orderRep) {
        this.orderRep = orderRep;
    }

    public JButton getClientRep() {
        return clientRep;
    }

    public void setClientRep(JButton clientRep) {
        this.clientRep = clientRep;
    }

    public JButton getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(JButton productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public JButton getProductsDayOrdered() {
        return productsDayOrdered;
    }

    public void setProductsDayOrdered(JButton productsDayOrdered) {
        this.productsDayOrdered = productsDayOrdered;
    }

    public int getOrderNb() {
        try {
            return Integer.parseInt(quantity.getText());
        }catch(NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Error", "Quantity not added", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    public JPanel getReportPanel() {
        return reportPanel;
    }

}
