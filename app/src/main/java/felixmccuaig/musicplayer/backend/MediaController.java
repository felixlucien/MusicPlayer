package felixmccuaig.musicplayer.backend;

import java.util.List;

import felixmccuaig.musicplayer.MainActivity;
import felixmccuaig.musicplayer.Song;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class MediaController {

    private MainActivity mainActivity;
    private List<Song> songs;

    public MediaController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


}
