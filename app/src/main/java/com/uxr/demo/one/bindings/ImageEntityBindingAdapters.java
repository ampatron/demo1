package com.uxr.demo.one.bindings;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uxr.demo.one.R;

/**
 * Created by abigail on 6/3/2016.
 */
public class ImageEntityBindingAdapters {
    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView view, Object imgUrl) {
        //clear image drawable to ensure proper recycling of view
        view.setImageDrawable(null);

        if (imgUrl != null) {
            Glide.with(view.getContext()).load(imgUrl.toString()).into(view);
        }
    }
}
