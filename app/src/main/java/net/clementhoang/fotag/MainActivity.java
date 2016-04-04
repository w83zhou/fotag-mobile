package net.clementhoang.fotag;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import net.clementhoang.fotag.views.ActionBar;
import net.clementhoang.fotag.views.IView;
import net.clementhoang.fotag.views.ThumbnailView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IView {

    public Model model;
    public GridView galleryView;
    public ActionBar actionBar;
    public ArrayList<ThumbnailView> thumbnailViews;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("info", "change orientation to portrait");
            galleryView.setNumColumns(1);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("info", "change orientation to landscape");
            galleryView.setNumColumns(-1); //auto_fit
        } else {
            Log.d("info", "Weird orientation");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("info", "onCreate");

        if (savedInstanceState != null) {
            this.model = (Model) savedInstanceState.getSerializable("model");
            this.model.context = this;
        } else {
            this.model = new Model(this);
        }

        this.model.addObserver(this);
        thumbnailViews = new ArrayList<>();

        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_search);
        fab.setOnClickListener(new View.OnClickListener() { // url loader
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "pop up dialog", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Log.d("info", "onPostCreate");

        this.galleryView = (GridView) findViewById(R.id.gridview);

        galleryView.setAdapter(new ThumbnailAdapter(this, this.model));
        galleryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Snackbar.make(v, "clicked" + id, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        this.actionBar = new ActionBar(this, this.model);
    }

    public void updateView(Action a) {
        Log.d("info", "MainActivity updated");
        switch(a) {
            case AddImage:
            case RemoveImage:
            case Refresh:
            case SetFilter:
                ((ThumbnailAdapter)this.galleryView.getAdapter()).refreshImagesToDisplay();
                ((ThumbnailAdapter)this.galleryView.getAdapter()).notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("info", "destroyed");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.clear();
        // Save the user's current game state
        savedInstanceState.putSerializable("model", this.model);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
}
