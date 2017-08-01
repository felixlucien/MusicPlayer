package felixmccuaig.musicplayer.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import felixmccuaig.musicplayer.MainActivity;
import felixmccuaig.musicplayer.R;
import felixmccuaig.musicplayer.backend.MediaController;
import felixmccuaig.musicplayer.ui.adapters.ViewPagerAdapter;
import felixmccuaig.musicplayer.ui.tabs.LocalAlbumsTab;
import felixmccuaig.musicplayer.ui.tabs.LocalSongsTab;

/**
 * Created by felixmccuaig on 30/7/17.
 */

public class UiHandler {

    public UiHandler(MainActivity activity) {
        MediaController mediaController = new MediaController(activity);

        ViewPager viewPager = (ViewPager) activity.findViewById(R.id.tab_fragments_view);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        pagerAdapter.addFrag(LocalSongsTab.instansiate(mediaController), "Songs");
        pagerAdapter.addFrag(LocalAlbumsTab.instansiate(mediaController), "Albums");
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}
