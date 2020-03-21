package com.pixelhubllc.dictionary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.pixelhubllc.dictionary.adapter.BookmarkAdapter;
import com.pixelhubllc.dictionary.adapter.HistoryAdapter;
import com.pixelhubllc.dictionary.database.DatabaseAccess;
import com.pixelhubllc.dictionary.model.Model;
import com.pixelhubllc.dictionary.swipe.RecyclerViewSwipeHelper;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment {
    private ArrayList<Model> bookmark;
    private BookmarkAdapter bookmarkAdapter;
    DatabaseAccess databaseAccess;
    private RecyclerView bookmarlwordList;

    Context context;
    public  FavouriteFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bookmark,container, false);

        databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        bookmarlwordList = view.findViewById(R.id.bookmark_listview);
        bookmarlwordList.setLayoutManager(new LinearLayoutManager(context));

        bookmark = databaseAccess.getBookmark();
        if (bookmark != null && bookmark.size() !=0){
            bookmarkAdapter = new BookmarkAdapter(getActivity(), bookmark);
            bookmarlwordList.setAdapter(bookmarkAdapter);
            bookmarkAdapter.notifyDataSetChanged();
        }

//        ItemTouchHelper.Callback callback=new RecyclerViewSwipeHelper(bookmarkAdapter);
//        ItemTouchHelper helper=new ItemTouchHelper(callback);
//        helper.attachToRecyclerView(bookmarlwordList);

        return view;
    }
}
