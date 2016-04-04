package net.clementhoang.fotag;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import net.clementhoang.fotag.views.IView;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ImageModel implements Serializable {
    public transient ArrayList<IView> observers;
    public int userRating;
    public int id;
    public String url;
    public transient Bitmap bitmap;
    public transient Context context;
    public transient Model model;

    public ImageModel(Integer id, Context context, Model m) {
        this.id = id;
        this.userRating = 0;
        this.observers = new ArrayList<>();
        this.context = context;
        this.model = m;
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), id);
    }

    public ImageModel(String url, Context context, Model m) {
        this.url = url;
        this.userRating = 0;
        this.observers = new ArrayList<>();
        this.context = context;
        this.model = m;
        new DownloadImageTask(this).execute(url);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageModel imageModel;
        public DownloadImageTask(ImageModel imageModel) {
            this.imageModel = imageModel;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bm = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                bm = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());

                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        model.uploadedImages.remove(imageModel);
                        model.notifyViews(Action.RemoveImage);
                    }
                });
            }
            return bm;
        }

        protected void onPostExecute(Bitmap result) {
            this.imageModel.bitmap = result;
            this.imageModel.notifyViews(Action.AddImage);
            model.notifyViews(Action.AddImage);
        }
    }

    public void addObserver(IView view) {
        this.observers.add(view);
    }

    public void setRating(int rating) {
        this.userRating = rating;
        this.notifyViews(Action.ChangeRating);
        this.model.notifyViews(Action.ChangeRating);
    }

    public void notifyViews(Action a) {
        for (IView view : this.observers) {
            view.updateView(a);
        }
    }
}
