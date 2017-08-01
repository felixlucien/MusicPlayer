package felixmccuaig.musicplayer.ui.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.backend.MediaController;
import felixmccuaig.musicplayer.backend.datastructs.Album;
import felixmccuaig.musicplayer.backend.utils.ResLoader;
import felixmccuaig.musicplayer.ui.adapters.AlbumAdapter;
import felixmccuaig.musicplayer.ui.adapters.SongAdapter;

/**
 * Created by Felix McCuaig on 1/08/2017.
 */

public class LocalAlbumsTab extends Fragment {
    List<Album> albums;


    public static Fragment instansiate(MediaController mediaController){
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
        View view = inflater.inflate(R.layout.album_layout, container, false);
        GridView albumView = view.findViewById(R.id.grid_view);
        albums = ResLoader.loadAlbums(getActivity().getContentResolver());

        AlbumAdapter albumAdapter = new AlbumAdapter(albums, inflater, getContext());

        Log.d("ADAPTER TEXT", albumAdapter.getCount() + "");

        albumView.setAdapter(albumAdapter);


        return albumView;
    }
}
