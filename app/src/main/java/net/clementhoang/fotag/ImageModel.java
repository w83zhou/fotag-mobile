package net.clementhoang.fotag;

import net.clementhoang.fotag.views.IView;

import java.util.ArrayList;
import java.util.Date;

public class ImageModel {
    public transient ArrayList<IView> observers;
    public Date uploadDate;
    public int userRating;

    public ImageModel() {
        this.userRating = -1;
        this.uploadDate = new Date();
        this.observers = new ArrayList<>();
    }

    public void addObserver(IView view) {
        this.observers.add(view);
    }

    public void setRating(int rating) {
        this.userRating = rating;
        this.notifyViews(Action.ChangeRating);
    }

    public void notifyViews(Action a) {
        for (IView view : this.observers) {
            view.updateView(a);
        }
    }
}