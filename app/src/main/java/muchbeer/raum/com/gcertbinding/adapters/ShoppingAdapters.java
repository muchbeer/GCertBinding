package muchbeer.raum.com.gcertbinding.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.List;

import muchbeer.raum.com.gcertbinding.IMainActivity;
import muchbeer.raum.com.gcertbinding.R;
import muchbeer.raum.com.gcertbinding.databinding.ProductItemBinding;
import muchbeer.raum.com.gcertbinding.model.ProductEntity;

public class ShoppingAdapters extends RecyclerView.Adapter<ShoppingAdapters.ShoppingViewHolder> {

    private static final String TAG = "ProductsAdapter";

    private List<ProductEntity> mProducts = new ArrayList<>();
    private Context mContext;

    public ShoppingAdapters(Context context, List<ProductEntity> products) {
        mProducts = products;
        mContext = context;
    }

    public void refreshList(List<ProductEntity> products){
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ShoppingAdapters.ShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.product_item, parent, false);

        return new ShoppingViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingAdapters.ShoppingViewHolder holder, int position) {
        ProductEntity product = mProducts.get(position);
        holder.binding.setProduct(product);
        holder.binding.setTestUrl("put the link of the image");
        holder.binding.setIMainActivity((IMainActivity)mContext);
        holder.binding.setVariable(BR.product, product);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ShoppingViewHolder extends RecyclerView.ViewHolder {

        //        ViewDataBinding binding;
        ProductItemBinding binding;
        public ShoppingViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
