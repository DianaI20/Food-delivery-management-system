package Controller;

import BusinessLayer.DeliveryServiceBLL.Client;
import BusinessLayer.DeliveryServiceBLL.DeliveryService;
import BusinessLayer.DeliveryServiceBLL.MenuItem;
import BusinessLayer.DeliveryServiceBLL.Order;
import DataLayer.Bill;
import DataLayer.MenuItemTableModel;
import PresentationLayer.ClientFrame;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.stream.Collectors.toList;

public class ClientController {

    DeliveryService deliveryService;
    ClientFrame clientFrame;
    Client client;
    JTable table;
    JTable cartTable;
    MenuItemTableModel menuItemTableModel;
    List<MenuItem> itemsOrdered = new ArrayList<>();
    AtomicReference<Float> orderPrice = new AtomicReference<>((float) 0);
    JTextField priceField = new JTextField();


    public ClientController(DeliveryService deliveryService, Client client) {
        this.deliveryService = deliveryService;
        this.client = client;
    }

    public void startClientController(){
        clientFrame = new ClientFrame();
        addSearchActionListener();
        addCartActions();
    }

    private void addSearchActionListener(){
        clientFrame.getSearchButton().addActionListener(e->{
            List<MenuItem> list =   searchProductsByKeywords(clientFrame.getSearchedString());
            menuItemTableModel = new MenuItemTableModel();
            menuItemTableModel.addRows(list);
            table = new JTable(menuItemTableModel);
            JScrollPane tablePanel = new JScrollPane(table);
            clientFrame.getProductPanel().removeAll();
            clientFrame.getProductPanel().add(tablePanel);
            clientFrame.getProductPanel().revalidate();
            clientFrame.getProductPanel().repaint();
        });
    }

    List<MenuItem> searchProductsByKeywords(String textToSearchInto){

        List<MenuItem> searchedItems = new ArrayList<>();

        searchedItems = deliveryService.getMenuItems().stream().filter(t-> t.getTitle().toLowerCase().contains(textToSearchInto.toLowerCase())).collect(toList());

        return searchedItems;
    }

    public void addCartActions(){
        priceField.setBackground(null);
        clientFrame.getAddToCart().addActionListener(e->{
            MenuItem menuItem = (deliveryService.getMenuItems().stream().filter(t-> t.getId() == (Integer)menuItemTableModel.getValueAt(table.getSelectedRow(),0)).collect(toList()).get(0));
            itemsOrdered.add(menuItem);
            orderPrice.updateAndGet(v -> new Float((float) (v + menuItem.getPrice())));
            repaintPanel();
        });

        clientFrame.getDeleteProduct().addActionListener(g->{
            MenuItem menuItem = itemsOrdered.stream().filter(t-> t.getTitle() == cartTable.getValueAt(cartTable.getSelectedRow(),0)).collect(toList()).get(0);
            itemsOrdered.remove(menuItem);
            orderPrice.updateAndGet(v -> new Float((float) (v - menuItem.getPrice())));
            repaintPanel();
        });

        addFinishOrderAction();

    }

    public void addFinishOrderAction(){
        clientFrame.getFinishOrder().addActionListener(f->{
            client.ordered();
            Order order = new Order(client.getUserName());
            order.setTotalPrice(Float.parseFloat(String.valueOf(orderPrice)));
            List<MenuItem> orderedItems = new ArrayList<>();
            orderedItems.addAll(itemsOrdered);

            deliveryService.createNewOrder(order, orderedItems);
            StringBuilder notification = new StringBuilder("New order! ");
            notification.append("Placed at" + order.getOnlyDate() +" " +  order.getTime().getHour() + ":" + order.getTime().getMinute());
            deliveryService.notifyObservers(notification.toString());

            Bill b = new Bill();
            b.printBill(order,client,itemsOrdered);
            itemsOrdered.removeAll(itemsOrdered);
            orderPrice.set(0f);
            repaintPanel();
        });
    }

    public  JTable buildCartTable(){
        String[] columns = {"Name", "Price"};
        Object[][]dataValues = new Object[50][50];
        for(int i = 0; i <itemsOrdered.size(); i++){
            dataValues[i][0] = itemsOrdered.get(i).getTitle();
            dataValues[i][1] = itemsOrdered.get(i).getPrice();
        }
        JTable cart = new JTable(dataValues, columns );
        return cart;
    }

    public void repaintPanel(){
        cartTable = buildCartTable();
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setSize(200,600);
        priceField.setText("");
        priceField.setText("Total to pay:   " + orderPrice.toString());
        clientFrame.getCartPanel().removeAll();
        clientFrame.getCartPanel().add(scrollPane);
        clientFrame.getCartPanel().add(priceField, JPanel.BOTTOM_ALIGNMENT);
        clientFrame.getCartPanel().revalidate();
        clientFrame.getCartPanel().repaint();
    }

}
