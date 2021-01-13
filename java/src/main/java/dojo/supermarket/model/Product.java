package dojo.supermarket.model;

import java.util.Objects;

public class Product {
    private final String name;
    private final ProductUnit unit;
    private final double price;



    public Product(String name, ProductUnit unit, double price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }


    public ProductUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                unit == product.unit;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, unit);
    }
}
