package muchbeer.raum.com.gcertbinding.databinding;

import android.content.Context;


import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;

import muchbeer.raum.com.gcertbinding.R;


/**
 * Created by User on 2/7/2018.
 */

public class GlideBindingAdapters {

    private static final String TAG = GlideBindingAdapters.class.getSimpleName();


    //This is used when image is from website link
    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, String imageUrl){

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(view);
    }

    //This is used when image is from drawable resource
    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, int imageUrl){

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter({"requestListener", "imageResource"})
    public static void bindRequestListener(ImageView view, RequestListener listener, int imageResource){
        Log.d(TAG, "bindRequestListener: setting image resource.");

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
















