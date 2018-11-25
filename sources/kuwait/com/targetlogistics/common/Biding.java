package kuwait.com.targetlogistics.common;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import kuwait.com.targetlogistics.model.Label;

public class Biding {
    @BindingAdapter({"bind:imgRes"})
    public static void setImage(ImageView view, @DrawableRes int res) {
        Glide.with(view).load(Integer.valueOf(res)).into(view);
    }

    @BindingAdapter({"bind:imageUrl", "bind:pro"})
    public static void loadImage(ImageView view, String imageUrl, final ProgressBar mProgressBar) {
        mProgressBar.setVisibility(0);
        Glide.with(view).load(imageUrl).listener(new RequestListener<Drawable>() {
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                mProgressBar.setVisibility(8);
                return false;
            }

            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                mProgressBar.setVisibility(8);
                return false;
            }
        }).into(view);
    }

    @BindingAdapter({"bind:label"})
    public static void loadLabel(TextView view, String labelId) {
        view.setText(Label.getLabel(labelId));
    }

    @BindingAdapter({"bind:label", "bind:value"})
    public static void loadLabel(TextView view, String labelId, String value) {
        view.setText(String.format("%s %s", new Object[]{Label.getLabel(labelId), value}));
    }

    @BindingAdapter({"bind:label"})
    public static void loadLabel(EditText view, String labelId) {
        view.setHint(Label.getLabel(labelId));
    }

    @BindingAdapter({"bind:label"})
    public static void loadLabel(Button view, String labelId) {
        view.setText(Label.getLabel(labelId));
    }

    @BindingAdapter({"bind:setAmount"})
    public static void setAmount(TextView view, double amount) {
        view.setText(Utils.getFormattedAmount(view.getContext(), amount));
    }
}
