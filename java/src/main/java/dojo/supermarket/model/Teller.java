package dojo.supermarket.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private Map<Product, Offer> offers = new HashMap<>();


    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        this.offers.put(product, new Offer(offerType,argument));
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ShoppingItem> items = theCart.getItems();
        for (ShoppingItem item: items) {
            Product product = item.getProduct();
            double quantity = item.getQuantity();
            receipt.addProduct(product, quantity);
        }
        receipt.handleOffers(this.offers, theCart);

        return receipt;
    }

}
