package muchbeer.raum.com.gcertbinding;

import muchbeer.raum.com.gcertbinding.model.CartItem;
import muchbeer.raum.com.gcertbinding.model.ProductEntity;

public interface IMainActivity {

    void inflateViewProductFragment(ProductEntity product);

    void addToCart(ProductEntity product, int quantity);

    void showQuantityDialog();

    public void setQuantity(int quantity);

    void inflateViewCartFragment();

    void setCartVisibility(boolean visibility);

    void updateQuantity(ProductEntity product, int quantity);

    void removeCartItem(CartItem cartItem);
}
