package felixmccuaig.musicplayer.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.backend.datastructs.Song;
import felixmccuaig.musicplayer.backend.utils.StringShortener;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class SongAdapter extends BaseAdapter {
    private List<Song> songList;
    private Song currentSong;
    private LayoutInflater songInflater;
    private Context c;

    public SongAdapter(List<Song> songList, LayoutInflater songInflater, Context c) {
        this.songList = songList;
        this.songInflater = songInflater;
        this.c = c;
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
        ImageView albumArt = songLayout.findViewById(R.id.song_album_art);

        currentSong = songList.get(i);

        songNameText.setText(StringShortener.shortenString(currentSong.getSongName(), c.getResources(), new int[]{20, 40, 50, 60}));
        artistText.setText(StringShortener.shortenString(currentSong.getArtistName(), c.getResources(), new int[]{20, 40, 50, 60}));

        Picasso.with(c).load(currentSong.getSongAlbumArtLocation()).resize(100, 100).into(albumArt);

        if(albumArt.getDrawable() == null) {
            albumArt.setImageResource(R.mipmap.ic_launcher);
        }

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
