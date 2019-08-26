package muchbeer.raum.com.gcertbinding.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import muchbeer.raum.com.gcertbinding.IMainActivity;
import muchbeer.raum.com.gcertbinding.R;
import muchbeer.raum.com.gcertbinding.databinding.FragmentViewProductBinding;
import muchbeer.raum.com.gcertbinding.model.ProductEntity;
import muchbeer.raum.com.gcertbinding.model.ProductViewModel;

public class ViewProductFragment extends Fragment {

    private static final String LOG_TAG = ViewProductFragment.class.getSimpleName();
    //vars
    private ProductEntity mProduct;

    // Data binding
  public FragmentViewProductBinding mVBinding;
 //   FragmentViewProductModelBinding mVBinding;
  //  FragmentViewShopBinding mSBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mProduct = bundle.getParcelable(getString(R.string.intent_product));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     //  mBinding = FragmentViewProductBinding.inflate(inflater);
        mVBinding = FragmentViewProductBinding.inflate(inflater);
        mVBinding.setIMainActivity((IMainActivity)getActivity());
//mSBinding = FragmentViewShopBinding.inflate(inflater);
        Log.d(LOG_TAG, "ViewProductFragment Called ");
               ProductViewModel viewProductModel = new ProductViewModel();
               viewProductModel.setProduct(mProduct);
               viewProductModel.setQuantity(1);
              // mSBinding.setProductView(viewProductModel);


        mVBinding.setProductView(viewProductModel);

        //  mVBinding.setQty(1);
      //  mBinding.setProduct(mProduct);

      //  mBinding.setQty(1);

     //   viewProductModel.setProduct(mProduct);
      //  viewProductModel.setQuantity(1);

     //   mBinding.setProductView(viewProductModel);

        return mVBinding.getRoot();
    }
}
