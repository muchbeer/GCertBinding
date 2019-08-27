package muchbeer.raum.com.gcertbinding.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import muchbeer.raum.com.gcertbinding.R;
import muchbeer.raum.com.gcertbinding.databinding.CartItemBinding;
import muchbeer.raum.com.gcertbinding.model.CartItem;
import muchbeer.raum.com.gcertbinding.model.CartItemViewModel;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.BindingHolder> {

    private static final String LOG_TAG = CartItemAdapter.class.getSimpleName();

    private List<CartItem> mCartItems = new ArrayList<>();
    private Context mContext;

    public CartItemAdapter(Context context, List<CartItem> cartItems) {
        mCartItems = cartItems;
        mContext = context;
    }


    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.cart_item, parent, false);

        return new CartItemAdapter.BindingHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        CartItem cartItem = mCartItems.get(position);
        CartItemViewModel viewModel = new CartItemViewModel();
        viewModel.setCartItem(cartItem);
        holder.cBinding.setCartItemView(viewModel);
        holder.cBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCartItems.size();
    }

    public void updateCartItems(List<CartItem> cartItems){
        mCartItems.clear();
        mCartItems.addAll(cartItems);
        notifyDataSetChanged();
    }

    public class BindingHolder extends RecyclerView.ViewHolder{

        CartItemBinding cBinding;
        public BindingHolder(@NonNull View itemView) {
            super(itemView);
            cBinding = DataBindingUtil.bind(itemView);
        }
    }
}
