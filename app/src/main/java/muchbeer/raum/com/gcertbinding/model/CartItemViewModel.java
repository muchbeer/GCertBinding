package muchbeer.raum.com.gcertbinding.model;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import muchbeer.raum.com.gcertbinding.IMainActivity;

public class CartItemViewModel extends BaseObservable {

    private static final String LOG_TAG =CartItemViewModel.class.getSimpleName();

    private CartItem cartItem;

    @Bindable
    public CartItem getCartItem(){
        return cartItem;
    }

    public void setCartItem(CartItem cartItem){
        Log.d(LOG_TAG, "setQuantity: updating cart");
        this.cartItem = cartItem;
        notifyPropertyChanged(BR.cartItem);
    }

    public void increaseQuantity(Context context){
        CartItem cartItem = getCartItem();
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        setCartItem(cartItem);
        IMainActivity iMainActivity = (IMainActivity) context;
        iMainActivity.updateQuantity(cartItem.getProduct(), 1);
    }

    public void decreaseQuantity(Context context){
        CartItem cartItem = getCartItem();
        IMainActivity iMainActivity = (IMainActivity) context;
        if(cartItem.getQuantity() > 1){
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            setCartItem(cartItem);
            iMainActivity.updateQuantity(cartItem.getProduct(), -1);
        }
        else if(cartItem.getQuantity() == 1){
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            setCartItem(cartItem);
            iMainActivity.removeCartItem(cartItem);
        }
    }

    public String getQuantityString(CartItem cartItem){
        return ("Qty: " + String.valueOf(cartItem.getQuantity()));
    }
}
