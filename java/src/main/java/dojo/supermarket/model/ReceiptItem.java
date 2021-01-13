package dojo.supermarket.model;

import java.util.Objects;

public class ReceiptItem {
    private final Product product;
    //private final double price;
    //private double totalPrice;
    private final double quantity;

    public ReceiptItem(Product p, double quantity) {
        this.product = p;
        this.quantity = quantity;
        //this.price = price;
        //this.totalPrice = totalPrice;
    }

    /*public double getPrice() {
        return this.product.getPrice();
    }*/

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    /*public double getTotalPrice() {
        return totalPrice;
    }*/

    public double getTotalPriceWithoutDiscountCalculated() {
        return product.getPrice() * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItem that = (ReceiptItem) o;
        return Double.compare(that.product.getPrice(), product.getPrice()) == 0 &&
                Double.compare(that.getTotalPriceWithoutDiscountCalculated(), getTotalPriceWithoutDiscountCalculated()) == 0 &&
                Double.compare(that.quantity, quantity) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(product, product.getPrice(), getTotalPriceWithoutDiscountCalculated(), quantity);
    }


}
