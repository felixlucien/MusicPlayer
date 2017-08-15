package felixmccuaig.musicplayer.ui;

import android.app.Activity;
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
import felixmccuaig.musicplayer.ui.tabs.LocalAlbumsTab;
import felixmccuaig.musicplayer.ui.tabs.LocalSongsTab;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class UiHandler {

    private MediaController mediaController;

    public UiHandler(MainActivity activity) {
        ViewPager viewPager = (ViewPager) activity.findViewById(R.id.tab_fragments_view);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        pagerAdapter.addFrag(LocalSongsTab.instansiate(mediaController), "Songs");
        pagerAdapter.addFrag(LocalAlbumsTab.instansiate(mediaController), "Albums");
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setMediaPlayer() {

    }
}
