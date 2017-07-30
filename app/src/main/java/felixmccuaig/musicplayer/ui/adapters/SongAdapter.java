package felixmccuaig.musicplayer.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.Song;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class SongAdapter extends BaseAdapter {
    private List<Song> songList;
    private Song currentSong;
    private LayoutInflater songInflater;

    public SongAdapter(List<Song> songList, LayoutInflater songInflater) {
        this.songList = songList;
        this.songInflater = songInflater;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RelativeLayout songLayout = (RelativeLayout) songInflater.inflate(R.layout.song_layout, null);
        TextView songNameText = songLayout.findViewById(R.id.song_name_text);
        TextView artistText = songLayout.findViewById(R.id.artist_name_text);

        currentSong = songList.get(i);
        songNameText.setText(currentSong.getSongName());
        artistText.setText(currentSong.getArtistName());

        songLayout.setTag(i);
        return songLayout;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public Song getCurrentSong() {
        return currentSong;
    }
}
