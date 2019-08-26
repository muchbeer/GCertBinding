package muchbeer.raum.com.gcertbinding.model;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import muchbeer.raum.com.gcertbinding.R;
//import muchbeer.raum.com.gcertbinding.BR;


public class ProductViewModel extends BaseObservable {

    private static final String LOG_TAG = ProductViewModel.class.getSimpleName();
    public ProductEntity productEntity;
    public int quantity;
    public boolean imageVisibility = false;
@Bindable
    public boolean getImageVisibility() {
        return imageVisibility;
    }

    public void setImageVisible(boolean imageVisible) {
        this.imageVisibility = imageVisible;

        notifyPropertyChanged(muchbeer.raum.com.gcertbinding.BR.imageVisibility);
    }

    @Bindable
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(muchbeer.raum.com.gcertbinding.BR.quantity);
    }

    @Bindable
    public ProductEntity getProduct() {
        return productEntity;
    }

    public void setProduct(ProductEntity product) {
        this.productEntity = product;

        //If BR is giving hard ship next time please go file then
        // click to Invalidate cache
        notifyPropertyChanged(muchbeer.raum.com.gcertbinding.BR.product);
    }

    public RequestListener getCustomRequestListener(){
        return new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                setImageVisible(true);
                return false;
            }
        };
    }

    @BindingAdapter({"requestListener", "imageResource"})
    public static void bindRequestListener(ImageView view, RequestListener listener, int imageResource){
        Log.d(LOG_TAG, "bindRequestListener: setting image resource.");

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageResource)
                .listener(listener)
                .into(view);
    }

}
