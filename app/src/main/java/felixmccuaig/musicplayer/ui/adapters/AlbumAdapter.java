package felixmccuaig.musicplayer.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.backend.datastructs.Album;

/**
 * Created by Felix McCuaig on 1/08/2017.
 */

public class AlbumAdapter extends BaseAdapter {
    private List<Album> albumList;
    private Album currentAlbum;
    private LayoutInflater albumInflater;
    private Context c;

    public AlbumAdapter(List<Album> albumList, LayoutInflater albumInflater, Context c) {
        this.albumList = albumList;
        this.albumInflater = albumInflater;
        this.c = c;
    }

    @Override
    public int getCount() {
        return albumList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        RelativeLayout albumLayout = (RelativeLayout) albumInflater.inflate(R.layout.album_layout, null);
        TextView albumName = albumLayout.findViewById(R.id.album_name);
        TextView artistName = albumLayout.findViewById(R.id.album_artist);
        ImageView albumArt = albumLayout.findViewById(R.id.album_image_large);

        currentAlbum = albumList.get(i);

        albumName.setText(currentAlbum.getAlbumName());
        artistName.setText(currentAlbum.getArtistName());
        Picasso.with(c).load(currentAlbum.getAlbumArtLocation()).resize(400, 400).into(albumArt);

        if(albumArt.getDrawable() == null) {
            Picasso.with(c).load(R.drawable.music_icon_stock).resize(400, 400).into(albumArt);
        }

        albumLayout.setTag(i);


        return albumLayout;
    }
}
