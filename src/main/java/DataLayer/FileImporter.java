package DataLayer;

import BusinessLayer.DeliveryServiceBLL.BaseProduct;
import BusinessLayer.DeliveryServiceBLL.MenuItem;
import com.opencsv.bean.CsvToBeanBuilder;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class FileImporter {

    public static int index;

    public List<MenuItem> importProducts(String fileName) throws IOException {
        List<MenuItem> menuItems = new ArrayList<>();
        File importedFile = new File(fileName);
        InputStream inputStream = new FileInputStream(importedFile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
        menuItems = bufferedReader.lines().skip(1).map(line->{
                                            String[] p = line.split(",");
                                                MenuItem item = new BaseProduct();
                                                item.setTitle(p[0]);
                                                item.setRating(Float.parseFloat(p[1]));
                                                item.setCalories(Float.parseFloat(p[2]));
                                                item.setProteins(Float.parseFloat(p[3]));
                                                item.setFats(Float.parseFloat(p[4]));
                                                item.setSodium(Float.parseFloat(p[5]));
                                                item.setPrice(Float.parseFloat(p[6]));
            return item;
        }).collect(Collectors.toList());
        menuItems = menuItems.stream().collect(collectingAndThen(toCollection(()-> new TreeSet<>(Comparator.comparing(MenuItem::getTitle))),
        ArrayList::new));

        menuItems.sort(Comparator.comparing(MenuItem::getTitle));

        return menuItems;
    }
}
