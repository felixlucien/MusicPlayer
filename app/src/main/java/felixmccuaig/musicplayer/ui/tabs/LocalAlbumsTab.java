package felixmccuaig.musicplayer.ui.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

import felixmccuaig.musicplayer.AlbumActivity;
import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.backend.MediaControllerOld;
import felixmccuaig.musicplayer.backend.datastructs.Album;
import felixmccuaig.musicplayer.backend.datastructs.Song;
import felixmccuaig.musicplayer.backend.utils.ResLoader;
import felixmccuaig.musicplayer.ui.adapters.AlbumAdapter;

/**
 * Created by Felix McCuaig on 1/08/2017.
 */

public class LocalAlbumsTab extends Fragment {
    List<Album> albums;


    public static Fragment instansiate(MediaControllerOld mediaController){
        LocalAlbumsTab albumsTab = new LocalAlbumsTab();

        return albumsTab;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_view_layout, container, false);
        GridView albumView = view.findViewById(R.id.grid_view);
        albums = ResLoader.loadAlbums(getActivity().getContentResolver());

        Log.d("ALBUM VIEW", albumView.getColumnWidth() + "");
        albumView.setAdapter(new AlbumAdapter(albums, inflater, getActivity().getApplicationContext()));

        albumView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), AlbumActivity.class);
                Album currentAlbum = albums.get(i);
                intent.putExtra("ALBUM", currentAlbum);
                List<Song> songsList = currentAlbum.getSongs();
                Song[] songs = new Song[songsList.size()];
                songs = songsList.toArray(songs);
                intent.putExtra("SONGS", songs);
                startActivity(intent);
            }
        });
        return view;
    }
}
