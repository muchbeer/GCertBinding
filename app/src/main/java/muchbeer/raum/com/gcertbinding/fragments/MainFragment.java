package muchbeer.raum.com.gcertbinding.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import muchbeer.raum.com.gcertbinding.R;
import muchbeer.raum.com.gcertbinding.adapters.ShoppingAdapters;
import muchbeer.raum.com.gcertbinding.databinding.FragmentMainBinding;
import muchbeer.raum.com.gcertbinding.model.ProductEntity;
import muchbeer.raum.com.gcertbinding.utility.Products;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private static final String LOG_TAG = Fragment.class.getSimpleName();
    public MainFragment() {
        // Required empty public constructor
    }

FragmentMainBinding mFBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFBinding = FragmentMainBinding.inflate(inflater);
        mFBinding.swipeRefreshLayout.setOnRefreshListener(this);
   //     return inflater.inflate(R.layout.fragment_main, container, false);
setupProductList();
        return mFBinding.getRoot();
    }

    private void setupProductList() {
        Products products = new Products();
        List<ProductEntity>  listOfProducts = new ArrayList<ProductEntity>();
        listOfProducts.addAll(Arrays.asList(products.PRODUCTS));
        mFBinding.setProducts(listOfProducts);
    }
    @Override
    public void onRefresh() {
        Products products = new Products();
        List<ProductEntity> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(products.PRODUCTS));
        ((ShoppingAdapters)mFBinding.recyclerView.getAdapter()).refreshList(productList);
        OnItemLoadComplete();
    }

    private void OnItemLoadComplete() {
        (mFBinding.recyclerView.getAdapter()).notifyDataSetChanged();
        mFBinding.swipeRefreshLayout.setRefreshing(false);
    }
}
