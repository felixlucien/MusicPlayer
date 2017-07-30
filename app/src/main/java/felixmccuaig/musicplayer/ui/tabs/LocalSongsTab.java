package felixmccuaig.musicplayer.ui.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.Song;
import felixmccuaig.musicplayer.backend.ResLoader;
import felixmccuaig.musicplayer.ui.adapters.SongAdapter;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class LocalSongsTab extends Fragment {
    private List<Song> songList;

    public static Fragment instansiate() {
        LocalSongsTab songsTab = new LocalSongsTab();

        return songsTab;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_list_layout, container, false);
        ListView songsView = view.findViewById(R.id.tab_list_view);
        songList = ResLoader.loadSongs(getActivity().getContentResolver());

        songsView.setAdapter(new SongAdapter(songList, inflater, getActivity().getApplicationContext()));

        return view;
    }
}
