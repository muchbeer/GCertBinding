package muchbeer.raum.com.gcertbinding.utility;

import java.math.BigDecimal;
import java.util.HashMap;

import muchbeer.raum.com.gcertbinding.model.ProductEntity;


public class Prices {

    private static final HashMap<String, BigDecimal> PRICES;
    static
    {
        PRICES = new HashMap<String, BigDecimal>();
        Products products = new Products();
        for(ProductEntity product : products.PRODUCTS){
            PRICES.put(String.valueOf(product.getSerial_number()), product.getPrice());
        }
    }



    public static HashMap<String, BigDecimal> getPrices(){
        return  PRICES;
    }
}
