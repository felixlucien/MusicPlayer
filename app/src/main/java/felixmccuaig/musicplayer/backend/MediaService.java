package felixmccuaig.musicplayer.backend;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Felix McCuaig on 3/08/2017.
 */

public class MediaService extends Service {
    public static final String TAG = "Media Service";

   // private Activity currentActivity;

    private MediaPlayer mediaPlayer;
    private MediaControllerOld mediaController;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        mediaController = intent.getExtras().getParcelable("MEDIA_CONTROLLER");

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //MediaControllerOld mediaController = new MediaControllerOld(activity);
        mediaPlayer = new MediaPlayer();




        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
