package dojo.supermarket.model;

public class Offer {
    SpecialOfferType offerType;
    double argument;

    public Offer(SpecialOfferType offerType,double argument) {
        this.offerType = offerType;
        this.argument = argument;
    }


    public Discount getTwoForAmountDiscount(Product product,double quantity){
        double unitPrice = product.getPrice();
        int quantityAsInt = (int) quantity;
        int proportion = quantityAsInt / 2;
        Discount discount = new Discount(product,"no discount", 0.0);
        if (quantityAsInt >= 2){
            double pricePerUnit = argument * proportion;
            double theTotal = (quantityAsInt % 2) * unitPrice;
            double total = pricePerUnit + theTotal;
            double discountAmount = unitPrice * quantity - total;
            discount = new Discount(product, "2 for " + argument, -discountAmount);

        }
        return discount;
    }

    public Discount getThreeForTwo(Product product,double quantity){
        double unitPrice = product.getPrice();
        int quantityAsInt = (int) quantity;
        int proportion = quantityAsInt / 3;
        Discount discount = new Discount(product,"no discount", 0.0);
        if (quantityAsInt > 2) {
            double discountAmount = quantity * unitPrice - ((proportion * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            discount = new Discount(product, "3 for 2", -discountAmount);
        }
        return discount;
    }

    public Discount getFiveForAmountProduct(Product product,double quantity){
        double unitPrice = product.getPrice();
        int quantityAsInt = (int) quantity;
        int proportion = quantityAsInt / 5;
        Discount discount = new Discount(product,"no discount", 0.0);
        if (quantityAsInt >= 5) {
            double discountAmount = unitPrice * quantity - (argument * proportion + quantityAsInt % 5 * unitPrice);
            discount = new Discount(product,  "5 for " + argument, -discountAmount);
        }
        return discount;
    }

    public Discount TenPercentDiscount(Product product,double quantity){
        double unitPrice = product.getPrice();
        int quantityAsInt = (int) quantity;
        int proportion = quantityAsInt / 5;
        Discount discount = new Discount(product,"no discount", 0.0);
        double discountAmount = quantity * unitPrice * argument / 100.0;
        discount = new Discount(product, argument + "% off", -discountAmount );
        return discount;
    }

    public Discount getDiscount(Product product,double quantity) {
       switch (offerType) {
            case FiveForAmount:
                return getFiveForAmountProduct(product, quantity);
            case ThreeForTwo:
                return getThreeForTwo(product, quantity);
            case TwoForAmount:
                return getTwoForAmountDiscount(product, quantity);
            case TenPercentDiscount:
                return TenPercentDiscount(product, quantity);
        }
        return new Discount(product, "no discount", 0.0);

  }

}
