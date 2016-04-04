package net.clementhoang.fotag;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import net.clementhoang.fotag.views.IView;

public class MainActivity extends AppCompatActivity implements IView {

    public Model model;
    public GridView galleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("info", "onCreate");

        if (savedInstanceState != null) {
            this.model = (Model) savedInstanceState.getSerializable("model");
        } else {
            this.model = new Model();
        }

        this.model.addObserver(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    }

    public void updateView(Action a) {
        Log.d("info", "MainActivity updated");
        ((ThumbnailAdapter)this.galleryView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("info", "destroyed");
        ((ThumbnailAdapter)this.galleryView.getAdapter()).unbindAll();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.clear();
        // Save the user's current game state
        savedInstanceState.putSerializable("model", this.model);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.load) {
            Log.d("info", "load action");
            this.model.loadDefaults();
            Snackbar.make(findViewById(android.R.id.content), "Loaded images", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            return true;
        } else if (id == R.id.clear) {
            Log.d("info", "clear action");
            this.model.clearAll();
            Snackbar.make(findViewById(android.R.id.content), "Cleared images", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
