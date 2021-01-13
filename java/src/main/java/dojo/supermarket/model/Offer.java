package dojo.supermarket.model;

public class Offer {
    SpecialOfferType offerType;
    //private final Product product;
    double argument;

    public Offer(SpecialOfferType offerType, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        //this.product = product;
    }

    public int getQuantityWithOffers(){

        switch (offerType){
            case FiveForAmount:
                return 5;
            case ThreeForTwo:
                return 3;
            case TwoForAmount:
                return 2;
            default:
                return 1;
        }
    }

    public Discount getDiscount(Product p, double quantity) {
        //double unitPrice = catalog.getUnitPrice(p);
        double unitPrice = p.getPrice();
        int quantityAsInt = (int) quantity;
        int quantityWithOffers = getQuantityWithOffers();
        int proportion = quantityAsInt / quantityWithOffers;

        Discount discount = new Discount(p,"no discount", 0.0);
        double discountAmount = 0.0;
        if (offerType == SpecialOfferType.TwoForAmount && (quantityAsInt >= 2)){

                double pricePerUnit = argument * proportion;
                double theTotal = (quantityAsInt % 2) * unitPrice;
                double total = pricePerUnit + theTotal;
                discountAmount = unitPrice * quantity - total;
                discount = new Discount(p, "2 for " + argument, -discountAmount);

        }
        if (offerType == SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
            discountAmount = quantity * unitPrice - ((proportion * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            discount = new Discount(p, "3 for 2", -discountAmount);
        }
        if (offerType == SpecialOfferType.TenPercentDiscount) {
            discountAmount = quantity * unitPrice * argument / 100.0;
            discount = new Discount(p, argument + "% off", -discountAmount );
        }
        if (offerType == SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
            discountAmount = unitPrice * quantity - (argument * proportion + quantityAsInt % 5 * unitPrice);
            discount = new Discount(p, quantityWithOffers + " for " + argument, -discountAmount);
        }
        return discount;
    }

    /*Product getProduct() {
        return this.product;
    }*/

}
