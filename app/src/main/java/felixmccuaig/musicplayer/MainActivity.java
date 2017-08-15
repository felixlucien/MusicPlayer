package felixmccuaig.musicplayer;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import felixmccuaig.musicplayer.backend.MediaService;
import felixmccuaig.musicplayer.ui.MediaController;
import felixmccuaig.musicplayer.ui.UiHandler;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    UiHandler uiHandler;
    MediaService mediaService;

    protected ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 1: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(this, MediaService.class);

                    uiHandler = new UiHandler(this);


                    startService(i);

                } else {
                    Log.d("Permission Request", "REQUEST DENIED");
                }
                return;
            }

        }
    }

    private MediaController initMediaController(MainActivity activity) {
        List<Button> buttonSmall = new ArrayList<>();
        buttonSmall.add((Button) activity.findViewById(R.id.play_pause_button));
        buttonSmall.add((Button) activity.findViewById(R.id.next_song_button));
        buttonSmall.add((Button) activity.findViewById(R.id.last_song_button));

        List<Button> buttonLarge = new ArrayList<>();
        buttonLarge.add((Button) activity.findViewById(R.id.play_pause_button_expanded));
        buttonLarge.add((Button) activity.findViewById(R.id.next_song_button_expanded));
        buttonLarge.add((Button) activity.findViewById(R.id.next_song_button_expanded));

        List<List<Button>> buttons = new ArrayList<>();
        buttons.add(buttonSmall);
        buttons.add(buttonLarge);

        List<TextView> textViewSmall = new ArrayList<>();
        textViewSmall.add((TextView) activity.findViewById(R.id.song_name_view));
        textViewSmall.add((TextView) activity.findViewById(R.id.artist_name_text));

        List<List<TextView>> textViews = new ArrayList<>();
        textViews.add(textViewSmall);

        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add((ImageView) activity.findViewById(R.id.album_art_icon));
        imageViews.add((ImageView) activity.findViewById(R.id.album_image_large));

        SeekBar songProgression = (SeekBar) activity.findViewById(R.id.song_progression_expanded);

        return new MediaController(buttons, textViews, imageViews, songProgression, activity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, MediaService.class);
        intent.putExtra();

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
