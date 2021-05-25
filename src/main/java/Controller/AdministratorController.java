package Controller;

import BusinessLayer.DeliveryServiceBLL.*;
import BusinessLayer.DeliveryServiceBLL.MenuItem;
import DataLayer.MenuItemTableModel;
import PresentationLayer.AdminFrame;
import PresentationLayer.ImportFileFrame;
import PresentationLayer.ProductFrame;
import PresentationLayer.ReportFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AdministratorController {

    AdminFrame adminFrame;
    ProductFrame productFrame;
    ReportFrame reportFrame;
    DeliveryService deliveryService;

    public AdministratorController(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }

    public void startAdminController(){
        adminFrame = new AdminFrame();
        addImportBtnAction();
        addManageProductAction();
        addReportsBtnAction();
    }

    private void addManageProductAction(){
        adminFrame.getManageProducts().addActionListener(e->{
            productFrame = new ProductFrame();
            addViewProductsAction();
            addProductAction();
            addModifyAndDeleteProductAction();
            addReportsBtnAction();
        });
    }

    private void addProductAction(){
        productFrame.getAddProduct().addActionListener(e->{
            try {
                List<String> fields = productFrame.getTextFields();
                MenuItem menuItem = new BaseProduct(fields.get(1), Float.parseFloat(fields.get(2)), Float.parseFloat(fields.get(3)), Float.parseFloat(fields.get(4)), Float.parseFloat(fields.get(5)), Float.parseFloat(fields.get(6)));
                deliveryService.addProduct(menuItem);
            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(null,"Error", "Error in arguments :(", JOptionPane.ERROR_MESSAGE);
            }
            productFrame.refreshFields();
        });
        productFrame.getAddCompositeProd().addActionListener(e->{
            List<String> fields = productFrame.getTextFields();
            deliveryService.addProduct(getCompositeProd(fields.get(0)));
            productFrame.refreshFields();
        });
    }

    private void addModifyAndDeleteProductAction(){
        productFrame.getModifyProduct().addActionListener(e->{
            List<String > fields = productFrame.getTextFields();
            MenuItem m = deliveryService.findItemById(Integer.parseInt(fields.get(0)));
            for(int index = 1; index <fields.size(); index++){
                try{
                if(!fields.get(index).equals("")){
                    switch (index){
                        case 1: m.setTitle(fields.get(index)); break;
                        default : m.setCalories(Float.parseFloat(fields.get(index)));
                    }
                }}
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid input.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            deliveryService.modifyProduct(m);
            productFrame.refreshFields();
        });
        addDeleteButtonAction();

    }

    private void addDeleteButtonAction(){
        productFrame.getDeleteProduct().addActionListener(e->{
            List<String > fields = productFrame.getTextFields();
            try {
                deliveryService.deleteProduct(Integer.parseInt(fields.get(0)));
                productFrame.refreshFields();
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Invalid id", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void addViewProductsAction(){
        productFrame.getViewProducts().addActionListener(e->{
            MenuItemTableModel menuItemTableModel = new MenuItemTableModel();
            List<MenuItem> m  = deliveryService.getMenuItems();
            menuItemTableModel.addRows(m);

            JScrollPane tablePanel = new JScrollPane(new JTable(menuItemTableModel));
            productFrame.getViewPanel().removeAll();
            productFrame.getViewPanel().add(tablePanel);
            productFrame.getViewPanel().revalidate();
            productFrame.getViewPanel().repaint();
        });

    }

    private void addImportBtnAction(){

        adminFrame.getImportCSV().addActionListener(e->{
            ImportFileFrame importFrame = new ImportFileFrame();
            importFrame.getImportBtn().addActionListener(f->{
                String filePath = importFrame.getCsvPath().getText();
                try {
                    deliveryService.importProducts(filePath);
                    JOptionPane.showMessageDialog(null, "The file " + filePath +" was imported successfully.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "An error occurred while importing the file.\n Please check the file's path.", "SUCCESS", JOptionPane.ERROR_MESSAGE);
                }
                importFrame.getCsvPath().setText("");
            });
        });
    }

    private void addReportsBtnAction(){

        adminFrame.getGenerateRepo().addActionListener(e->{
            reportFrame = new ReportFrame();
            reportFrame.getOrderRep().addActionListener(f->{
            JScrollPane tablePanel = new JScrollPane(generateOrdersTable(deliveryService.filterOrdersByTimeInterval(reportFrame.getTimeIntervals())));
            refreshReportFrame(tablePanel);
            });

            reportFrame.getClientRep().addActionListener(g->{
            JScrollPane tablePanel = new JScrollPane(generateClientsTable(deliveryService.generateClientsReport(reportFrame.getOrderNb(), reportFrame.getPrice())));
            refreshReportFrame(tablePanel);
            });
            reportFrame.getProductsDayOrdered().addActionListener(h->{
            MenuItemTableModel menuItemTableModel = new MenuItemTableModel();
            List<MenuItem > m  = deliveryService.filterByDateAndNumberOfOrders(reportFrame.getDate(),reportFrame.getOrderNb());
            menuItemTableModel.addRows(m);
            JScrollPane tablePanel = new JScrollPane(new JTable(menuItemTableModel));
            refreshReportFrame(tablePanel);
            });

            reportFrame.getProductsOrdered().addActionListener(i->{
            MenuItemTableModel menuItemTableModel = new MenuItemTableModel();
            List<MenuItem> m  = deliveryService.filterProductsByOrderedNumber(reportFrame.getOrderNb());
            menuItemTableModel.addRows(m);
            JScrollPane tablePanel = new JScrollPane(new JTable(menuItemTableModel));
            refreshReportFrame(tablePanel);
            });
        });

    }

    private JTable generateOrdersTable(List<Order> orders){
        String[] columns = {"OrderId", "ClientId", "Total", "Date"};
        Object[][]dataValues = new Object[100][100];
        for(int i = 0; i <orders.size(); i++){
            dataValues[i][0] = orders.get(i).getOrderId();
            dataValues[i][1] = orders.get(i).getClientId();
            dataValues[i][2] = orders.get(i).getTotalPrice();
            dataValues[i][3] = orders.get(i).getOnlyDate();
        }
        JTable ordersTable = new JTable(dataValues, columns);

        ordersTable.setSize(300,500);
        return ordersTable;
    }

    private JTable generateClientsTable(List<Client> clients){
        String[] columns = {"Username", "Name", "Number of orders"};
        Object[][]dataValues = new Object[100][100];
        for(int i = 0; i <clients.size(); i++){
            dataValues[i][0] = clients.get(i).getUserName();
            dataValues[i][1] = clients.get(i).getName();
            dataValues[i][2] = clients.get(i).getNumberOfOrders();
        }
        return new JTable(dataValues, columns);
    }

    public CompositeProduct getCompositeProd(String field){
        String s[]= field.split(" ");
        List<MenuItem> list = new ArrayList<>();
        for(int i =0 ;i < s.length;i++){
            try {
            Integer id = Integer.parseInt(s[i]);

                list.add(deliveryService.getMenuItems().stream().filter(l -> l.getId() == id).collect(toList()).get(0));
            }catch(Exception f){
                JOptionPane.showMessageDialog(null, "Invalid id", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return new CompositeProduct(list);
    }

    private void refreshReportFrame(JScrollPane tablePanel){
        reportFrame.getReportPanel().removeAll();
        reportFrame.getReportPanel().add(tablePanel);
        reportFrame.getReportPanel().revalidate();
        reportFrame.getReportPanel().repaint();
    }
}
