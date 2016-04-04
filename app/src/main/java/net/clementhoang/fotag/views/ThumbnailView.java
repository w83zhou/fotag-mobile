package net.clementhoang.fotag.views;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import net.clementhoang.fotag.Action;
import net.clementhoang.fotag.ImageModel;
import net.clementhoang.fotag.R;

public class ThumbnailView extends LinearLayout implements IView {

    public ImageModel imageModel;
    public View backingView;
    public RatingBar ratingBar;

    public ThumbnailView(Context c, ImageModel m, View backingView) {
        super(c);

        this.imageModel = m;
        this.backingView = backingView;

        this.backingView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ThumbnailView.this.backingView.getContext(), R.style.FullScreenDialog);
                dialog.setContentView(R.layout.full_screen);
                ImageView iv = (ImageView) dialog.findViewById(R.id.image_view);
                iv.setImageBitmap(imageModel.bitmap);
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                dialog.setCancelable(true);
                dialog.show();
            }
        });
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
