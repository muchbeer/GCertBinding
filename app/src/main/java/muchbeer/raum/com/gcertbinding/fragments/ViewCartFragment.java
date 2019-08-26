package muchbeer.raum.com.gcertbinding.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import muchbeer.raum.com.gcertbinding.IMainActivity;
import muchbeer.raum.com.gcertbinding.databinding.FragmentViewCartBinding;
import muchbeer.raum.com.gcertbinding.databinding.FragmentViewProductBinding;

public class ViewCartFragment extends Fragment {

    private static final String TAG = "ViewCartFragment";

    //data binding
    FragmentViewCartBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = FragmentViewCartBinding.inflate(inflater);
        mBinding.setIMainActivity((IMainActivity)getActivity());
        mBinding.getIMainActivity().setCartVisibility(true);

        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        mBinding.getIMainActivity().setCartVisibility(false);
        super.onDestroy();
    }
}
