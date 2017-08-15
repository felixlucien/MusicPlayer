package felixmccuaig.musicplayer.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import felixmccuaig.musicplayer.MainActivity;
import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.backend.MediaService;
import felixmccuaig.musicplayer.ui.adapters.ViewPagerAdapter;
import felixmccuaig.musicplayer.ui.tabs.LocalAlbumsTab;
import felixmccuaig.musicplayer.ui.tabs.LocalSongsTab;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class UiHandler {

    MediaService mediaService;

    Button playButton, nextButton, lastButton;
    TextView songName, artistName;

    public UiHandler(MainActivity activity) {
        playButton = (Button) activity.findViewById(R.id.play_pause_button);
        nextButton = (Button) activity.findViewById(R.id.next_song_button);
        lastButton = (Button) activity.findViewById(R.id.last_song_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaService.onPlayButtonPress();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaService.onNextSongButtonPress();
            }
        });

        lastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaService.onLastSongButtonPress();
            }
        });

        songName = (TextView) activity.findViewById(R.id.song_name_view);
        artistName = (TextView) activity.findViewById(R.id.artist_name_text);

        ViewPager viewPager = (ViewPager) activity.findViewById(R.id.tab_fragments_view);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        pagerAdapter.addFrag(LocalSongsTab.instantiate(activity.mediaService), "Songs");
        pagerAdapter.addFrag(LocalAlbumsTab.instansiate(activity.mediaService), "Albums");
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initMediaController(MainActivity activity) {

        List<Button> buttonLarge = new ArrayList<>();
        buttonLarge.add((Button) activity.findViewById(R.id.play_pause_button_expanded));
        buttonLarge.add((Button) activity.findViewById(R.id.next_song_button_expanded));
        buttonLarge.add((Button) activity.findViewById(R.id.next_song_button_expanded));


        List<TextView> textViewSmall = new ArrayList<>();
        textViewSmall.add((TextView) activity.findViewById(R.id.song_name_view));
        textViewSmall.add((TextView) activity.findViewById(R.id.artist_name_text));



        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add((ImageView) activity.findViewById(R.id.album_art_icon));
        imageViews.add((ImageView) activity.findViewById(R.id.album_image_large));

        SeekBar songProgression = (SeekBar) activity.findViewById(R.id.song_progression_expanded);
    }
}
