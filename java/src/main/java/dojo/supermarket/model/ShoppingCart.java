package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ShoppingItem> items = new ArrayList<>();

    List<ShoppingItem> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ShoppingItem(product, quantity));
    }


}
