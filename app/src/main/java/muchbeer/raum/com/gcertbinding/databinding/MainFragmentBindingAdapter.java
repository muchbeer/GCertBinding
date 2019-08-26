package muchbeer.raum.com.gcertbinding.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import muchbeer.raum.com.gcertbinding.adapters.ShoppingAdapters;
import muchbeer.raum.com.gcertbinding.model.ProductEntity;

public class MainFragmentBindingAdapter {

    private static final int NUM_COLUMNS = 2;

    @BindingAdapter("productsList")
    public static void setProductsList(RecyclerView rView, List<ProductEntity> products) {
        if(products == null) {
            return;
        }

        RecyclerView.LayoutManager layoutManager = rView.getLayoutManager();
        if(layoutManager ==null) {
            rView.setLayoutManager(new GridLayoutManager(rView.getContext(), NUM_COLUMNS));

        }

        ShoppingAdapters onlineAdapter = (ShoppingAdapters) rView.getAdapter();
        if(onlineAdapter == null) {
            onlineAdapter = new ShoppingAdapters(rView.getContext(), products);
            rView.setAdapter(onlineAdapter);
        }

    }

}
