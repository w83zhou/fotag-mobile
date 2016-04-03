package net.clementhoang.fotag;

import net.clementhoang.fotag.views.IView;

import java.util.ArrayList;

public class Model {
    public ArrayList<ImageModel> uploadedImages;
    private transient ArrayList<IView> observers;
    public transient int currentFilter;
    public transient boolean isFilterEnabled;

    public Model() {
        this.uploadedImages = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.currentFilter = 1;
        this.isFilterEnabled = false;
    }

    public int getFilteredImagesCount() {
        int count=0;
        if (this.isFilterEnabled) {
            for (ImageModel im:this.uploadedImages) {
                if (im.userRating >= this.currentFilter) {
                    count++;
                }
            }
        } else {
            count = this.uploadedImages.size();
        }

        return count;
    }

    public void setCurrentFilter(int i) {
        this.currentFilter = i;
        this.notifyViews(Action.SetFilter);
    }

    public void toggleFilter() {
        this.isFilterEnabled = !this.isFilterEnabled;
        this.notifyViews(Action.ToggleFilter);
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
