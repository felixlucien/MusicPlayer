package felixmccuaig.musicplayer.backend.utils;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

/**
 * Created by felixmccuaig on 31/7/17.
 */

public class StringShortener {

    static int SMALL_SCREEN = 20, NORMAL_SCREEN = 30, LARGE_SCREEN = 40, XLARGE_SCREEN = 50;

    public static String shortenString(String target, Resources resources, int[] stringSizes) {
        if(stringSizes == null) {
            stringSizes = new int[]{
                    SMALL_SCREEN, NORMAL_SCREEN, LARGE_SCREEN, XLARGE_SCREEN
            };
        }

        switch (resources.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL: {
                Log.d("DISPLAY CONFIG", "SCREENLAYOUT_SIZE_SMALL");
                return shortenStringInternal(stringSizes[0], target);
            }

            case Configuration.SCREENLAYOUT_SIZE_NORMAL: {
                Log.d("DISPLAY CONFIG", "SCREENLAYOUT_SIZE_NORMAL");
                return shortenStringInternal(stringSizes[1], target);
            }

            case Configuration.SCREENLAYOUT_SIZE_LARGE: {
                Log.d("DISPLAY CONFIG", "SCREENLAYOUT_SIZE_LARGE");
                return shortenStringInternal(stringSizes[2], target);
            }

            case Configuration.SCREENLAYOUT_SIZE_XLARGE: {
                Log.d("DISPLAY CONFIG", "SCREENLAYOUT_SIZE_XLARGE");
                return shortenStringInternal(stringSizes[3], target);
            }

            default: {
                return "ERROR";
            }
        }
    }

    private static String shortenStringInternal(int stringSize, String target) {
        if(target.length() > stringSize) {
            return target.substring(0, stringSize) + "...";
        } else {
            return target;
        }
    }
}
