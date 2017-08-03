package felixmccuaig.musicplayer.backend;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by Felix McCuaig on 3/08/2017.
 */

public class MediaService extends Service {
    public static final String TAG = "Media Service";

   // private Activity currentActivity;

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //MediaController mediaController = new MediaController(activity);
        mediaPlayer = new MediaPlayer();


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
