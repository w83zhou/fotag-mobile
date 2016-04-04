package net.clementhoang.fotag;

import android.content.Context;

import net.clementhoang.fotag.views.IView;

import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {
    public ArrayList<ImageModel> uploadedImages;
    private transient ArrayList<IView> observers;
    public transient int currentFilter;
    public transient Context context;

    public Model(Context context) {
        this.uploadedImages = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.currentFilter = 0;
        this.context = context;
    }

    public void loadDefaults() {
        this.uploadedImages.add(new ImageModel(R.drawable.preloaded_1, this.context));
        this.uploadedImages.add(new ImageModel(R.drawable.preloaded_2, this.context));
        this.uploadedImages.add(new ImageModel(R.drawable.preloaded_3, this.context));
        this.uploadedImages.add(new ImageModel(R.drawable.preloaded_4, this.context));
        this.uploadedImages.add(new ImageModel(R.drawable.preloaded_5, this.context));
        this.uploadedImages.add(new ImageModel(R.drawable.preloaded_6, this.context));
        this.uploadedImages.add(new ImageModel(R.drawable.preloaded_7, this.context));
        this.uploadedImages.add(new ImageModel(R.drawable.preloaded_8, this.context));
        this.uploadedImages.add(new ImageModel(R.drawable.preloaded_9, this.context));
        this.uploadedImages.add(new ImageModel(R.drawable.preloaded_10, this.context));

        this.notifyViews(Action.AddImage);
    }

    public void clearAll() {
        this.uploadedImages = new ArrayList<>();
        this.notifyViews(Action.RemoveImage);
    }

    public void setCurrentFilter(int i) {
        this.currentFilter = i;
        this.notifyViews(Action.SetFilter);
    }

    public void addImage(ImageModel image) {
        this.uploadedImages.add(image);
        this.notifyViews(Action.AddImage);
    }

    public void addObserver(IView view) {
        this.observers.add(view);
    }

    public void notifyViews(Action a) {
        for (IView view : this.observers) {
            view.updateView(a);
        }
    }
}
