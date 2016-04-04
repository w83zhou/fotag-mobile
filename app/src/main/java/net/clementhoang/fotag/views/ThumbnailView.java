package net.clementhoang.fotag.views;

import android.content.Context;
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
//        Log.d("info", "ThumbnailView constructed");
        this.backingView = LayoutInflater.from(c).inflate(R.layout.thumbnail_view, parent, false);

        this.model.addObserver(this);
    }

    public void updateView(Action a) {
        Log.d("info", "ThumbnailView updated");
    }
}
