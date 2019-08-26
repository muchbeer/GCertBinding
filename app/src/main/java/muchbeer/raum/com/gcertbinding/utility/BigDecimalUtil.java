package muchbeer.raum.com.gcertbinding.utility;


import android.icu.text.DecimalFormat;
import android.util.Log;

import java.math.BigDecimal;

public class BigDecimalUtil {

    private static final String LOG_TAG = BigDecimalUtil.class.getSimpleName() ;
    private static String returnString;

    public static String getValue(BigDecimal value){
        DecimalFormat df = new DecimalFormat("###,###,###.00");
       
     //  String returnString = value.toString();
        //return String.valueOf(df.format(value));
        Double valueDouble = Double.valueOf(df.format(value));
        if(valueDouble >= 0 ) {
            returnString  = valueDouble.toString();
        } else {
            returnString = "100";
        }
        Log.d(LOG_TAG, "The value given is : "+ value);
        Log.d( LOG_TAG, "The real value wanted is: " + returnString );
        //return String.valueOf(df.format(value));
        return returnString;
    }

    public static String getValue2(BigDecimal value){
        DecimalFormat df = new DecimalFormat("###,###,###.00");

        //  String returnString = value.toString();
        //return String.valueOf(df.format(value));
        Double valueDouble = Double.valueOf(df.format(value));
        if(valueDouble >= 0 ) {
            returnString  = valueDouble.toString();
        } else {
            returnString = "100";
        }
        Log.d(LOG_TAG, "The value given Clicked: "+ value);
        Log.d( LOG_TAG, "The real value wanted clicked is: " + returnString );
        //return String.valueOf(df.format(value));
        return returnString;
    }
    /*
        For rating bar (actual rating)
     */
    public static float getFloat(BigDecimal value){
        return value.floatValue();
    }
}
