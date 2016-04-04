package net.clementhoang.fotag.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.clementhoang.fotag.Action;
import net.clementhoang.fotag.Model;
import net.clementhoang.fotag.R;

public class ThumbnailView extends LinearLayout implements IView {

    public Model model;
    public View backingView;

    public ThumbnailView(Context c, Model m, ViewGroup parent) {
        super(c);

        this.model = m;
        this.backingView = LayoutInflater.from(c).inflate(R.layout.thumbnail_view, parent, false);

        //        ImageView imageView;
//        if (convertView == null) {
//            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(this.model.uploadedImages.get(position).id);
//        this.imageViews.add(imageView);
//        return imageView;

        this.model.addObserver(this);
    }

    public void updateView(Action a) {
        Log.d("info", "ThumbnailView updated");
    }
}
