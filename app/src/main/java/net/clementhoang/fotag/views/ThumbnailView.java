package net.clementhoang.fotag.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import net.clementhoang.fotag.Action;
import net.clementhoang.fotag.ImageModel;
import net.clementhoang.fotag.Model;
import net.clementhoang.fotag.R;

public class ThumbnailView extends LinearLayout implements IView {

    public ImageModel imageModel;
    public View backingView;
    public RatingBar ratingBar;

    public ThumbnailView(Context c, ImageModel m, View backingView) {
        super(c);

        this.imageModel = m;
        this.backingView = backingView;
    }

    public void updateView(Action a) {
        Log.d("info", "ThumbnailView updated");

        this.ratingBar = (RatingBar) this.backingView.findViewById(R.id.rating_bar);
        this.ratingBar.setRating(this.imageModel.userRating);

        this.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                imageModel.setRating((int) rating);
            }
        });

        ImageView iv = (ImageView) this.backingView.findViewById(R.id.image_view);
        iv.setImageBitmap(this.imageModel.bitmap);
    }
}
