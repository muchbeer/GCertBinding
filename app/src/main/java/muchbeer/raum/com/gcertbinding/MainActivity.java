package muchbeer.raum.com.gcertbinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import muchbeer.raum.com.gcertbinding.databinding.ActivityMainBinding;
import muchbeer.raum.com.gcertbinding.fragments.ChooseQuantityDialog;
import muchbeer.raum.com.gcertbinding.fragments.MainFragment;
import muchbeer.raum.com.gcertbinding.fragments.ViewCartFragment;
import muchbeer.raum.com.gcertbinding.fragments.ViewProductFragment;
import muchbeer.raum.com.gcertbinding.model.CartItem;
import muchbeer.raum.com.gcertbinding.model.CartViewModel;
import muchbeer.raum.com.gcertbinding.model.ProductEntity;
import muchbeer.raum.com.gcertbinding.utility.PreferenceKeys;
import muchbeer.raum.com.gcertbinding.utility.Products;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName() ;
    ActivityMainBinding mBinding;

    //vars
    private boolean mClickToExit = false;
    private Runnable mCheckoutRunnable;
    private Handler mCheckoutHandler;
    private int mCheckoutTimer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.cart.setOnTouchListener(new CartTouchListener());

        mBinding.proceedToCheckout.setOnClickListener(mCheckOutListener);


        getShoppingCart();
        init();
    }

    private void init() {

        MainFragment productFragment = new MainFragment();
        FragmentTransaction getTranformation = getSupportFragmentManager().beginTransaction();
        getTranformation.replace(R.id.main_container, productFragment, getString(R.string.fragment_main) );
        getTranformation.commit();
    }

    public View.OnClickListener mCheckOutListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            checkout();
        }
    };

    @Override
    public void inflateViewProductFragment(ProductEntity product) {
        Log.d(LOG_TAG, "inflateViewProductFragment: called.");

        ViewProductFragment fragment = new ViewProductFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.intent_product), product);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment, getString(R.string.fragment_view_product));
        transaction.addToBackStack(getString(R.string.fragment_view_product));
        transaction.commit();
    }

    @Override
    public void addToCart(ProductEntity product, int quantity) {
        Log.d(LOG_TAG, "addToCart: adding "+ quantity + " " + product.getTitle() + "to cart.");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        //add the new products serial number (if it hasn't already been added)
        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());
        serialNumbers.add(String.valueOf(product.getSerial_number()));
        editor.putStringSet(PreferenceKeys.shopping_cart, serialNumbers);

        editor.commit();

        //add the quantity
        int currentQuantity = preferences.getInt(String.valueOf(product.getSerial_number()), 0);

        //commit the updated quantity
        editor.putInt(String.valueOf(product.getSerial_number()), (currentQuantity + quantity));
        editor.commit();

        //reset the quantity in ViewProductFragment
        setQuantity(1);

        //update the bindings
        getShoppingCart();

        // notify the user
        Toast.makeText(this, "added to cart", Toast.LENGTH_SHORT).show();
    }

    public static class CartTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                view.setBackgroundColor(view.getContext().getResources().getColor(R.color.blue4));
                view.performClick();

                IMainActivity iMainActivity = (IMainActivity)view.getContext();
                iMainActivity.inflateViewCartFragment();
            }
            else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                view.setBackgroundColor(view.getContext().getResources().getColor(R.color.blue6));
            }

            return true;
        }
    }

    private void getShoppingCart(){
        Log.d(LOG_TAG, "getShoppingCart: getting shopping cart.");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());

        mBinding.setNumCartItems(serialNumbers.size());
        // Retrieve the quantities of each item from the cart
        Products products = new Products();
        List<CartItem> cartItems = new ArrayList<>();
        for(String serialNumber : serialNumbers){
            int quantity = preferences.getInt(serialNumber, 0);

            cartItems.add(new CartItem(products.PRODUCT_MAP.get(serialNumber), quantity));
       }

        CartViewModel viewModel = new CartViewModel();
        viewModel.setCart(cartItems);
        try{
            viewModel.setCartVisible(mBinding.getCartView().isCartVisible());
        }catch (NullPointerException e){
            Log.e(LOG_TAG, "getShoppingCart: NullPointerException: " + e.getMessage() );
        }
        mBinding.setCartView(viewModel);


    }
    @Override
    public void showQuantityDialog() {
        Log.d(LOG_TAG, "showQuantityDialog: showing Quantity Dialog");
        ChooseQuantityDialog chooseQuantityDialog = new ChooseQuantityDialog();
        chooseQuantityDialog.show(getSupportFragmentManager(), getString(R.string.dialog_choose_quantity));
    }

    @Override
    public void setQuantity(int quantity) {
        Log.d(LOG_TAG, "selectQuantity: selected quantity: " + quantity);

        ViewProductFragment fragment = (ViewProductFragment)getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_product));
        if(fragment != null){
            fragment.mVBinding.getProductView().setQuantity(quantity);
           // fragment.Binding.getProductView().setQuantity(quantity);
        }
    }

    @Override
    public void inflateViewCartFragment() {
        ViewCartFragment fragment = (ViewCartFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_cart));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fragment == null){
            fragment = new ViewCartFragment();
            transaction.replace(R.id.main_container, fragment, getString(R.string.fragment_view_cart));
            transaction.addToBackStack(getString(R.string.fragment_view_cart));
            transaction.commit();
        }
    }

    @Override
    public void setCartVisibility(boolean visibility) {
        mBinding.getCartView().setCartVisible(visibility);
    }

    @Override
    public void updateQuantity(ProductEntity product, int quantity) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        //add the quantity
        int currentQuantity = preferences.getInt(String.valueOf(product.getSerial_number()), 0);

        //commit the updated quantity
        editor.putInt(String.valueOf(product.getSerial_number()), (currentQuantity + quantity));
        editor.commit();

        getShoppingCart();
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.remove(String.valueOf(cartItem.getProduct().getSerial_number()));
        editor.commit();

        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());
        if(serialNumbers.size() == 1){
            editor.remove(PreferenceKeys.shopping_cart);
            editor.commit();
        }
        else{
            serialNumbers.remove(String.valueOf(cartItem.getProduct().getSerial_number()));
            editor.putStringSet(PreferenceKeys.shopping_cart, serialNumbers);
            editor.commit();
        }

        getShoppingCart();

        //remove the item from the list in ViewCartFragment
        ViewCartFragment fragment = (ViewCartFragment)getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_cart));
        if(fragment != null){
            fragment.updateCartItems();
        }
    }

    public void checkout(){
        Log.d(LOG_TAG, "checkout: checking out.");

        mBinding.progressBar.setVisibility(View.VISIBLE);

        mCheckoutHandler = new Handler();
        mCheckoutRunnable  = new Runnable() {
            @Override
            public void run() {
                mCheckoutHandler.postDelayed(mCheckoutRunnable, 200);
                mCheckoutTimer += 200;
                if(mCheckoutTimer >= 1600){
                    emptyCart();
                    mBinding.progressBar.setVisibility(View.GONE);
                    mCheckoutHandler.removeCallbacks(mCheckoutRunnable);
                    mCheckoutTimer = 0;
                }
            }
        };
        mCheckoutRunnable.run();
    }

    private void emptyCart(){
        Log.d(LOG_TAG, "emptyCart: emptying cart.");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());
        SharedPreferences.Editor editor = preferences.edit();

        for(String serialNumber : serialNumbers){
            editor.remove(serialNumber);
            editor.commit();
        }

        editor.remove(PreferenceKeys.shopping_cart);
        editor.commit();
        Toast.makeText(this, "thanks for shopping!", Toast.LENGTH_SHORT).show();
        removeViewCartFragment();
        getShoppingCart();
    }

    private void removeViewCartFragment() {
            getSupportFragmentManager().popBackStack();
            ViewCartFragment fragment = (ViewCartFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_cart));
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(fragment != null){
                transaction.remove(fragment);
                transaction.commit();
            }
        }
    }

