package net.clementhoang.fotag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import net.clementhoang.fotag.views.IView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class ImageModel {
    public transient ArrayList<IView> observers;
    public int userRating;
    public int id;
    public String url;
    public transient Bitmap bitmap;
    public transient Context context;

    public ImageModel(Integer id, Context context) {
        this.id = id;
        this.userRating = 0;
        this.observers = new ArrayList<>();
        this.context = context;
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), id);
    }

    public ImageModel(String url, Context context) {
        this.url = url;
        this.context = context;
        try {
            this.bitmap = BitmapFactory.decodeStream((new URL(url)).openStream());
        } catch (Exception e) {
            Log.d("info", "Failed to decode from url: " + url);
        }
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
