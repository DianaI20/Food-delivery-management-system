package DataLayer;
import BusinessLayer.DeliveryServiceBLL.MenuItem;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MenuItemTableModel extends AbstractTableModel {
    private String[] columnNames = {"id","title", "rating", "calories", "protein", "fat","sodium", "price","number of orders"};
    private List<MenuItem> data;

    public MenuItemTableModel() {

        data = new ArrayList<>(25);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
       switch(columnIndex){
           case 0: return Integer.class;
           case 1: return String.class;
           case 8: return Integer.class;
           default: return Float.class;
       }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        MenuItem value = data.get(row);
     switch(col){
         case 0: return value.getId();
         case 1: return  value.getTitle();
         case 2: return value.getRating();
         case 3: return value.getCalories();
         case 4: return value.getProteins();
         case 5: return  value.getFats();
         case 6: return value.getSodium();
         case 7: return value.getPrice();
         case 8: return value.getTimesOrdered();
     }
     return null;
    }
    public void addRow(MenuItem value) {
        int rowCount = getRowCount();
        data.add(value);
        fireTableRowsInserted(rowCount, rowCount);
    }

    public void addRows(MenuItem... value) {
        addRows( Arrays.asList(value));
    }

    public void addRows(List<MenuItem> rows) {
        int rowCount = getRowCount();
        data.addAll(rows);
        fireTableRowsInserted(rowCount, getRowCount() - 1);
    }


}
