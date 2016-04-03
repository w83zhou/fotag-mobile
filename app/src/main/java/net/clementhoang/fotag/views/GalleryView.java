package net.clementhoang.fotag.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import net.clementhoang.fotag.Action;
import net.clementhoang.fotag.Model;
import net.clementhoang.fotag.R;
import net.clementhoang.fotag.ThumbnailAdapter;

public class GalleryView extends GridView implements IView {

    public Model model;

    public GalleryView(Context context, Model model) {
        super(context);

        Log.d("info", "GalleryView constructed");
        View.inflate(context, R.layout.gallery_view, null);

        this.model = model;
        this.model.addObserver(this);
    }

    public void updateView(Action a) {
        Log.d("info", "GalleryView updated");
        ((ThumbnailAdapter)this.getAdapter()).notifyDataSetChanged();
    }
}
