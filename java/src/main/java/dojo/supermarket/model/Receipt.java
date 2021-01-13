package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receipt {
    private List<ReceiptItem> items = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();

    public Double getTotalPrice() {
        double total = 0.0;
        // stream
        for (ReceiptItem item : this.items) {
            total += item.getTotalPriceWithoutDiscountCalculated();
        }
        for (Discount discount : this.discounts) {
            total += discount.getDiscountAmount();
        }
        return total;
    }

    public void addProduct(Product p, double quantity) {
        this.items.add(new ReceiptItem(p, quantity));
    }

    public List<ReceiptItem> getItems() {
        return new ArrayList<>(this.items);
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }



    public Map<Product, Double> getProductQuantities(List<ProductQuantity> items){
        Map<Product, Double> productQuantities = new HashMap<>();
        for(ProductQuantity item: items){
            Product product = item.getProduct();
            double quantity = item.getQuantity();
            if (productQuantities.containsKey(product)) {
                productQuantities.put(product, productQuantities.get(product) + quantity);
            } else {
                productQuantities.put(product, quantity);
            }
        }
        return productQuantities;
    }

    public void handleOffers(Map<Product, Offer> offers, ShoppingCart shoppingCart) {
        Map<Product, Double> productQuantities = getProductQuantities(shoppingCart.getItems());
        for (Product product: productQuantities.keySet()) {
            double quantity = productQuantities.get(product);
            if (offers.containsKey(product)) {
                Offer offer = offers.get(product);
                Discount discount = offer.getDiscount(product, quantity);
                if (discount.getDiscountAmount() != 0)
                    addDiscount(discount);
            }

        }
    }
}
