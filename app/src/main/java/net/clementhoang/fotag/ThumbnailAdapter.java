package net.clementhoang.fotag;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import net.clementhoang.fotag.views.ThumbnailView;

import java.util.ArrayList;

public class ThumbnailAdapter extends BaseAdapter {
    private Context mContext;
    public Model model;
    public ArrayList<ImageView> imageViews;

    public ThumbnailAdapter(Context c, Model m) {
        this.mContext = c;
        this.model = m;
        this.imageViews = new ArrayList<>();
    }

    public int getCount() {
        return this.model.uploadedImages.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return -1;
    }

    public void unbindAll() {
        for (ImageView iv: this.imageViews) {
            iv.setImageDrawable(null);
        }
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("info", "position: " + position + " , length: " + this.getCount());

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

        if (convertView == null) {
            ThumbnailView thumbnailView = new ThumbnailView(this.mContext, this.model);
            return thumbnailView.backingView;
        } else {
            return convertView;
        }
    }
}
