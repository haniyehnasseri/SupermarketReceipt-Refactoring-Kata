package dojo.supermarket.model;

import java.util.Objects;

public class ShoppingItem {
    private final Product product;
    private final double quantity;

    public ShoppingItem(Product p, double quantity) {
        this.product = p;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }
    public double getQuantity() {
        return quantity;
    }


    public double getTotalPriceWithoutDiscountCalculated() {
        return product.getPrice() * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingItem that = (ShoppingItem) o;
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
