package dojo.supermarket.model;

import java.util.ArrayList;
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

    public void handleOffers(Map<Product, Offer> offers, SupermarketCatalog catalog, ShoppingCart shoppingCart) {
        for (Product p: shoppingCart.productQuantities.keySet()) {
            double quantity = shoppingCart.productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                Discount discount = offer.getDiscount(p, quantity);
                if (discount.getDiscountAmount() != 0)
                    addDiscount(discount);
            }

        }
    }
}
