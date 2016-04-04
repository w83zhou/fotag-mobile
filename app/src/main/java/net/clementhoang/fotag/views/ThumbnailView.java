package net.clementhoang.fotag.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import net.clementhoang.fotag.Action;
import net.clementhoang.fotag.Model;
import net.clementhoang.fotag.R;
import net.clementhoang.fotag.ThumbnailAdapter;

public class ThumbnailView extends LinearLayout implements IView {

    public Model model;
    public View backingView;

    public ThumbnailView(Context c, Model m) {
        super(c);

        this.model = m;
        Log.d("info", "ThumbnailView constructed");
        this.backingView = View.inflate(c, R.layout.thumbnail_view, null);

        this.model.addObserver(this);
    }

    public void updateView(Action a) {
        Log.d("info", "ThumbnailView updated");
    }
}
