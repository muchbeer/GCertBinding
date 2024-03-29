package muchbeer.raum.com.gcertbinding.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import muchbeer.raum.com.gcertbinding.IMainActivity;
import muchbeer.raum.com.gcertbinding.databinding.DialogChooseQuantityBinding;

public class ChooseQuantityDialog extends DialogFragment {

    private static final String LOG_TAG = ChooseQuantityDialog.class.getSimpleName() ;
    //data binding
    DialogChooseQuantityBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding=DialogChooseQuantityBinding.inflate(inflater);
        mBinding.setIMainActivity((IMainActivity) getActivity());
        mBinding.listView.setOnItemClickListener(mOnItemClickListener);
        mBinding.closeDialog.setOnClickListener(mCloseDialogListener);

        return mBinding.getRoot();
    }

    public AdapterView.OnItemClickListener mOnItemClickListener = (new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.d(LOG_TAG, "onItemSelected: selected: " + adapterView.getItemAtPosition(i));
            mBinding.getIMainActivity().setQuantity(Integer.parseInt((String)adapterView.getItemAtPosition(i)));
            getDialog().dismiss();
        }
    });

    public View.OnClickListener mCloseDialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    };
}
