package com.pixelhubllc.dictionary;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.pixelhubllc.dictionary.adapter.ViewPageAdapter;
import com.pixelhubllc.dictionary.database.DatabaseAccess;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private SwipeDisableViewPager viewPager;
    public static List<String> words;
    DatabaseAccess databaseAccess;
    ViewPageAdapter adapter;

    TabItem searchTab, favouriteTab, wordsTab, settingTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        tababLayoutInit();

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        words = databaseAccess.getWords();
        databaseAccess.close();


        //another way for implements tablayout with viewpage adapter
//        ViewPager viewPager;
//        TabLayout tabLayout;
//        viewPager=findViewById(R.id.viewpager);
//        tabLayout=findViewById(R.id.tabs);
//        viewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager()));
//        tabLayout.setupWithViewPager(viewPager,true);
    }

    private void tababLayoutInit(){
        adapter= new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchFragment(this),getString(R.string.search));
        adapter.addFragment(new FavouriteFragment(),getString(R.string.bookmark));
        adapter.addFragment(new WordsFragment(),getString(R.string.words));
        adapter.addFragment(new SettingFragment(),getString(R.string.setting));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //To disable swipe i created a custom class
        viewPager.setSwipeable(false);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_search_black);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_favourite_black);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_list_black);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_settings_black);
    }

    private void init(){
        tabLayout = findViewById(R.id.tablayout);
        viewPager =(SwipeDisableViewPager) findViewById(R.id.viewpager);
        searchTab = findViewById(R.id.search_id);
        favouriteTab = findViewById(R.id.bookmar_id);
        wordsTab = findViewById(R.id.wordlist_id);
        settingTab = findViewById(R.id.setting_id);
    }
}
