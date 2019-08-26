package muchbeer.raum.com.gcertbinding.utility;

import java.math.BigDecimal;
import java.util.HashMap;

import muchbeer.raum.com.gcertbinding.R;
import muchbeer.raum.com.gcertbinding.model.ProductEntity;

public class Products {

    public ProductEntity[] PRODUCTS = {RED_LAMP, YELLOW_LAMP, BLUE_MUG, WHITE_MUG, RED_MUG, BLACK_HAT, BLUE_HAT, WHITE_HAT, ORANGE_HAT,
            WHITE_SHIRT_MALE, WHITE_SHIRT_FEMALE, BLACK_SHIRT_FEMALE, BLACK_SHIRT_MALE, GREY_FIDGET_SPINNER, GREEN_FIDGET_SPINNER, ICELAND_PICTURE
            , ICEY_COAST_PICTURE, HAVASU_FALLS_PICTURE, FRANCE_MOUNTAINS_PICTURE, GREEN_HILLS_PICTURE};


    public HashMap<String, ProductEntity> PRODUCT_MAP = new HashMap<>();

    public Products() {
        for(ProductEntity product : PRODUCTS){
            PRODUCT_MAP.put(String.valueOf(product.getSerial_number()), product);
        }

    }

    public static final ProductEntity RED_LAMP = new ProductEntity("Red Lamp", "Red colored lamp, perfect for lighting up a room " +
            "and matching any red furniture.", R.drawable.red_lamp, new BigDecimal(10.99), new BigDecimal(9.50), 161,
            new BigDecimal(4.5), 1515611);

    public static final ProductEntity YELLOW_LAMP = new ProductEntity("Yellow Lamp", "Yellow colored lamp, perfect for lighting up a room " +
            "and matching any Yellow furniture.", R.drawable.yellow_lamp, new BigDecimal(11.99), new BigDecimal(12.4), 6,
            new BigDecimal(5), 7725277);

    public static final ProductEntity BLUE_MUG = new ProductEntity("Blue Coffee Mug", "Blue Coffee Mug for drinking coffee. 100% ceramic.",
            R.drawable.blue_mug, new BigDecimal(5.99), new BigDecimal(3.5), 66, new BigDecimal(3.5), 2141515);

    public static final ProductEntity WHITE_MUG = new ProductEntity("White Coffee Mug", "White Coffee Mug for drinking coffee. 100% ceramic.",
            R.drawable.white_mug, new BigDecimal(6.99), new BigDecimal(35.77), 7, new BigDecimal(4), 9704833);
    public static final ProductEntity RED_MUG = new ProductEntity("Red Coffee Mug Red", "Red Coffee Mug for drinking coffee. 100% ceramic.",
            R.drawable.red_mug, new BigDecimal(8.99), new BigDecimal(35.77), 157, new BigDecimal(4.5), 9377376);

    public static final ProductEntity BLACK_HAT = new ProductEntity("Black Baseball Hat", "Black Baseball Hat made of 100% authentic " +
            "baseball hat material.",
            R.drawable.black_hat, new BigDecimal(20.99), new BigDecimal(35.77), 121, new BigDecimal(3.5), 6626622);

    public static final ProductEntity BLUE_HAT = new ProductEntity("Blue Baseball Hat", "Blue Baseball Hat made of 100% authentic " +
            "baseball hat material.",
            R.drawable.blue_hat, new BigDecimal(22.99), new BigDecimal(35.77), 67, new BigDecimal(4.5), 7837367);

    public static final ProductEntity WHITE_HAT = new ProductEntity("White Baseball Hat", "White Baseball Hat made of 100% authentic " +
            "baseball hat material.",
            R.drawable.white_hat, new BigDecimal(18.99), new BigDecimal(15.99), 88, new BigDecimal(2.5), 7695085);

    public static final ProductEntity ORANGE_HAT = new ProductEntity("Orange Baseball Hat", "Orange Baseball Hat made of 100% authentic " +
            "baseball hat material.",
            R.drawable.orange_hat, new BigDecimal(23.99), new BigDecimal(35.77), 23, new BigDecimal(4), 9084728);

    public static final ProductEntity WHITE_SHIRT_FEMALE = new ProductEntity("White Shirt", "White T-Shirt made of 100% cotton. Made for " +
            "females.", R.drawable.white_shirt_female, new BigDecimal(25.99), new BigDecimal(35.77), 98, new BigDecimal(5)
            , 7265405);

    public static final ProductEntity WHITE_SHIRT_MALE = new ProductEntity("White Shirt", "White T-Shirt made of 100% cotton. Made for " +
            "males.", R.drawable.white_shirt_male, new BigDecimal(26.99), new BigDecimal(35.77), 11, new BigDecimal(3)
            , 9575721);

    public static final ProductEntity BLACK_SHIRT_FEMALE = new ProductEntity("Black Shirt", "Black T-Shirt made of 100% cotton. Made for " +
            "females.", R.drawable.black_shirt_female, new BigDecimal(25.99), new BigDecimal(35.77), 51, new BigDecimal(4.5)
            , 5776336);

    public static final ProductEntity BLACK_SHIRT_MALE = new ProductEntity("Black Shirt", "Black T-Shirt made of 100% cotton. Made for " +
            "males.", R.drawable.black_shirt_male, new BigDecimal(26.99), new BigDecimal(35.77), 616, new BigDecimal(5)
            , 1408483);

    public static final ProductEntity GREY_FIDGET_SPINNER = new ProductEntity("Grey Fidget Spinner", "Grey Fidget Spinner. High quality" +
            " bearing for long spin time. Light and portable.", R.drawable.fidget_spinner_grey, new BigDecimal(100), new BigDecimal(59.99)
            , 37, new BigDecimal(4.5), 8830303);

    public static final ProductEntity GREEN_FIDGET_SPINNER = new ProductEntity("Green Fidget Spinner", "Green Fidget Spinner. High quality" +
            " bearing for long spin time. Light and portable.", R.drawable.fidget_spinner_green, new BigDecimal(100), new BigDecimal(35.77)
            , 3, new BigDecimal(4), 9082727);

    public static final ProductEntity ICELAND_PICTURE = new ProductEntity("Picture of Water in Iceland", "Beautiful picture of Iceland and its " +
            "cold waters.", R.drawable.foggy_iceland, new BigDecimal(189.50), new BigDecimal(100), 43,
            new BigDecimal(4.8), 6638393);

    public static final ProductEntity FRANCE_MOUNTAINS_PICTURE = new ProductEntity("Picture of the Mountains in France", "Here is an incredible picture" +
            " of the mountains in France.", R.drawable.france_mtn, new BigDecimal(356), new BigDecimal(315), 22,
            new BigDecimal(3.2), 8093475);

    public static final ProductEntity GREEN_HILLS_PICTURE = new ProductEntity("Picture of green hills in GreenLand", "A calming image of a sunset in " +
            "Greenland.", R.drawable.green_hills, new BigDecimal(99), new BigDecimal(50), 79,
            new BigDecimal(4.1), 1485032);

    public static final ProductEntity HAVASU_FALLS_PICTURE = new ProductEntity("A Very Famous Picture of Havasu Falls", "Check out this famous picture " +
            "of Havasu Falls.", R.drawable.havasu_falls, new BigDecimal(76), new BigDecimal(35.77), 81,
            new BigDecimal(4.9), 8041414);

    public static final ProductEntity ICEY_COAST_PICTURE = new ProductEntity("An Image of the Icy Coast of Iceland", "Looking at this picture practically " +
            "makes you shiver! But it makes me appreciate warm weather.", R.drawable.icedfglrjioz, new BigDecimal(120), new BigDecimal(35.77), 37,
            new BigDecimal(3.3), 1145614);

}
