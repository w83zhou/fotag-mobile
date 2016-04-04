package net.clementhoang.fotag;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.clementhoang.fotag.views.ThumbnailView;

import java.util.ArrayList;

public class ThumbnailAdapter extends BaseAdapter {
    private Context mContext;
    public Model model;
    public ArrayList<ThumbnailView> thumbnailViews;
    public ArrayList<ImageModel> filteredImages;

    public ThumbnailAdapter(Context c, Model m) {
        this.mContext = c;
        this.model = m;
        this.thumbnailViews = new ArrayList<>();
        refreshImagesToDisplay();
    }

    public void refreshImagesToDisplay() {
        this.filteredImages = new ArrayList<>();
        if (model.currentFilter > 0) {
            for (ImageModel imageModel : this.model.uploadedImages) {
                if (imageModel.userRating >= model.currentFilter) {
                    this.filteredImages.add(imageModel);
                }
            }
        } else {
            this.filteredImages = this.model.uploadedImages;
        }
    }

    public int getCount() {
        return this.filteredImages.size();
    }

    public Object getItem(int position) {
        return this.filteredImages.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("info", "position: " + position);
        if (convertView == null) {
            Log.d("info", "making new thumbnail");
            View thumbnail = LayoutInflater.from(this.mContext).inflate(R.layout.thumbnail_view, parent, false);
            ThumbnailView tv = new ThumbnailView(this.mContext, this.filteredImages.get(position), thumbnail);
            tv.updateView(Action.Refresh);

            this.thumbnailViews.add(tv);

            return tv.backingView;
        } else {
            Log.d("info", "returning convertView");
            ThumbnailView tv = getThumbnailViewForBackingView(convertView);
            tv.imageModel = filteredImages.get(position);
            tv.updateView(Action.Refresh);

            return convertView;
        }
    }

    private ThumbnailView getThumbnailViewForBackingView(View v) {
        for (ThumbnailView tv : this.thumbnailViews) {
            if (tv.backingView == v) {
                return tv;
            }
        }

        return null;
    }
}
