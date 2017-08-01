package felixmccuaig.musicplayer.ui.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.backend.datastructs.Song;
import felixmccuaig.musicplayer.backend.MediaController;
import felixmccuaig.musicplayer.backend.utils.ResLoader;
import felixmccuaig.musicplayer.ui.adapters.SongAdapter;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class LocalSongsTab extends Fragment {
    private List<Song> songList;
    private MediaController mediaController;

    public static Fragment instansiate(MediaController mediaController) {
        LocalSongsTab songsTab = new LocalSongsTab();
        songsTab.mediaController = mediaController;
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
        mediaController.setSongs(songList);
        songsView.setAdapter(new SongAdapter(songList, inflater, getActivity().getApplicationContext()));
        songsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mediaController.updateSong(songList.get(i), i);
            }
        });
        return view;
    }
}
