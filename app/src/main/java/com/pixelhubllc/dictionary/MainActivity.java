package com.pixelhubllc.dictionary;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    TabItem searchTab, favouriteTab, wordsTab, settingTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        tababLayoutInit();

        //another way for implements tablayout with viewpage adapter
//        ViewPager viewPager;
//        TabLayout tabLayout;
//        viewPager=findViewById(R.id.viewpager);
//        tabLayout=findViewById(R.id.tabs);
//        viewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager()));
//        tabLayout.setupWithViewPager(viewPager,true);
    }

    private void tababLayoutInit(){
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchFragment(this),"Search");
        adapter.addFragment(new FavouriteFragment(),"Bookmark");
        adapter.addFragment(new WordsFragment(),"Words");
        adapter.addFragment(new SettingFragment(),"Setting");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //To disable swipe
        viewPager.beginFakeDrag();

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_search_black);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_favourite_black);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_list_black);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_settings_black);
    }

    private void init(){
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        searchTab = findViewById(R.id.search_id);
        favouriteTab = findViewById(R.id.bookmar_id);
        wordsTab = findViewById(R.id.wordlist_id);
        settingTab = findViewById(R.id.setting_id);
    }
}
