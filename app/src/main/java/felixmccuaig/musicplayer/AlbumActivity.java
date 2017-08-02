package felixmccuaig.musicplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import felixmccuaig.musicplayer.backend.datastructs.Album;
import felixmccuaig.musicplayer.backend.datastructs.Song;
import felixmccuaig.musicplayer.ui.adapters.SongAdapter;

public class AlbumActivity extends AppCompatActivity {

    Album currentAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentAlbum = getIntent().getExtras().getParcelable("ALBUM");

        LayoutInflater myinflater = getLayoutInflater();

        ListView listView = (ListView) findViewById(R.id.album_activity_songslist);
        ViewGroup myHeader = (ViewGroup) myinflater.inflate(R.layout.header_layout, listView, false);

        ImageView albumArt = myHeader.findViewById(R.id.album_art_album_activity);
        TextView albumText = myHeader.findViewById(R.id.name_album_activity);
        TextView artistText = myHeader.findViewById(R.id.artist_album_activity);

        Picasso.with(this).load(currentAlbum.getAlbumArtLocation()).into(albumArt);
        albumText.setText(currentAlbum.getAlbumName());
        artistText.setText(currentAlbum.getArtistName());

        //Log.d("ALBUM SONGS CHECK", currentAlbum.getSongs().get(0).getSongName());

        listView.setAdapter(new SongAdapter(currentAlbum.getSongs(), getLayoutInflater(), this));

        listView.addHeaderView(myHeader, null, false);



    }

}
