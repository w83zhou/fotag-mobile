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

public class ThumbnailAdapter extends BaseAdapter {
    private Context mContext;
    public Model model;

    public ThumbnailAdapter(Context c, Model m) {
        this.mContext = c;
        this.model = m;
    }

    public int getCount() {
        return this.model.uploadedImages.size();
    }

    public Object getItem(int position) {
        return this.model.uploadedImages.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("info", "position: " + position);
        if (convertView == null) {
            Log.d("info", "making new thumbnail");
            ThumbnailView thumbnailView = new ThumbnailView(this.mContext, this.model, parent);
            ImageView iv = (ImageView) thumbnailView.backingView.findViewById(R.id.image_view);
            iv.setImageBitmap(model.uploadedImages.get(position).bitmap);

            return thumbnailView.backingView;
        } else {
            Log.d("info", "returning convertView");
            ImageView iv = (ImageView) convertView.findViewById(R.id.image_view);
            iv.setImageBitmap(model.uploadedImages.get(position).bitmap);
            return convertView;
        }
    }
}
