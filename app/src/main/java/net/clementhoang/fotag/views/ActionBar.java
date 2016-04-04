package net.clementhoang.fotag.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import net.clementhoang.fotag.Action;
import net.clementhoang.fotag.Model;
import net.clementhoang.fotag.R;

import java.util.ArrayList;

public class ActionBar implements IView {

    public Model model;
    public Context context;
    public View backingView;
    public ArrayList<ImageButton> starButtons;
    public Drawable emptyStar, filledStar;

    private class StarListener implements View.OnClickListener {
        int pos;
        public StarListener (int position) {
            pos = position;
        }

        @Override
        public void onClick(View v) {
            Log.d("info", "clicked star: " + pos);
            model.setCurrentFilter(pos+1);
        }
    }

    public ActionBar(Context c, Model m) {
        this.model = m;
        this.context = c;
        this.model.addObserver(this);

        this.emptyStar = ContextCompat.getDrawable(context, R.drawable.empty_star);
        this.filledStar = ContextCompat.getDrawable(context, R.drawable.filled_star);

        this.backingView = ((Activity) c).findViewById(R.id.action_bar);
        this.starButtons = new ArrayList<>();
        this.starButtons.add((ImageButton) this.backingView.findViewById(R.id.star0));
        this.starButtons.add((ImageButton) this.backingView.findViewById(R.id.star1));
        this.starButtons.add((ImageButton) this.backingView.findViewById(R.id.star2));
        this.starButtons.add((ImageButton) this.backingView.findViewById(R.id.star3));
        this.starButtons.add((ImageButton) this.backingView.findViewById(R.id.star4));

        for (int i = 0; i < 5; ++i) {
            this.starButtons.get(i).setOnClickListener(new StarListener(i));
            this.starButtons.get(i).setTag(i);
        }
    }

    public void updateView(Action a) {
        Log.d("info", "Action bar updated");
        switch(a) {
            case SetFilter:
                for (int i=0; i<this.model.currentFilter; ++i) {
                    this.starButtons.get(i).setImageDrawable(filledStar);
                }
                for (int i=this.model.currentFilter; i<5; ++i) {
                    this.starButtons.get(i).setImageDrawable(emptyStar);
                }

                break;
        }
    }
}
