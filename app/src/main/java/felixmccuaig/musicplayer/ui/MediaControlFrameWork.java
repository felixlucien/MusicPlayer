package felixmccuaig.musicplayer.ui;

import java.util.List;

import felixmccuaig.musicplayer.backend.datastructs.Song;

/**
 * Created by Felix McCuaig on 4/08/2017.
 */

public interface MediaControlFrameWork {

    void onPlayButtonPress();

    void onNextSongButtonPress();

    void onLastSongButtonPress();

    void setSongs(List<Song> songs);

    void updateSong(Song song);

    void playSong();

    void pauseSong();

    void stopSong();
}
