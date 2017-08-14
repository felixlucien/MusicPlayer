package felixmccuaig.musicplayer.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import felixmccuaig.musicplayer.MainActivity;
import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.ui.adapters.ViewPagerAdapter;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class UiHandler {

    private MediaController mediaController;

    public UiHandler(MainActivity activity) {

        List<Button> buttonSmall = new ArrayList<>();
        buttonSmall.add((Button) activity.findViewById(R.id.play_pause_button));
        buttonSmall.add((Button) activity.findViewById(R.id.next_song_button));
        buttonSmall.add((Button) activity.findViewById(R.id.last_song_button));

        List<Button> buttonLarge = new ArrayList<>();
        buttonLarge.add((Button) activity.findViewById(R.id.play_pause_button_expanded));
        buttonLarge.add((Button) activity.findViewById(R.id.next_song_button_expanded));
        buttonLarge.add((Button) activity.findViewById(R.id.next_song_button_expanded));


        List<List<Button>> buttons = new ArrayList<>();
        buttons.add(buttonSmall);
        buttons.add(buttonLarge);

        List<TextView> textViewSmall = new ArrayList<>();
        textViewSmall.add((TextView) activity.findViewById(R.id.song_name_view));
        textViewSmall.add((TextView) activity.findViewById(R.id.artist_name_text));

        List<List<TextView>> textViews = new ArrayList<>();
        textViews.add(textViewSmall);

        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add((ImageView) activity.findViewById(R.id.album_art_icon));
        imageViews.add((ImageView) activity.findViewById(R.id.album_image_large));

        SeekBar songProgression = (SeekBar) activity.findViewById(R.id.song_progression_expanded);

        mediaController = new MediaController(buttons, textViews, imageViews, songProgression, activity);

        ViewPager viewPager = (ViewPager) activity.findViewById(R.id.tab_fragments_view);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        //pagerAdapter.addFrag(LocalSongsTab.instansiate(mediaController), "Songs");
        //pagerAdapter.addFrag(LocalAlbumsTab.instansiate(mediaController), "Albums");
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }


    public void setMediaPlayer() {

    }
}
