package com.pixelhubllc.dictionary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pixelhubllc.dictionary.database.DatabaseAccess;
import com.pixelhubllc.dictionary.model.Model;

import java.util.ArrayList;
import java.util.List;

public class WordsFragment extends Fragment {
    ArrayList<Model> getwords;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_words,container, false);

        ListView wordIndexList =(ListView) view.findViewById(R.id.wordlistid);

        MainActivity a = new MainActivity();
        getwords = a.words;
//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
//        databaseAccess.open();
//        List<String> words = databaseAccess.getWords();
//        databaseAccess.close();

        ArrayAdapter<Model> adapter = new ArrayAdapter<Model>(getActivity(), android.R.layout.simple_list_item_1,getwords);
        wordIndexList.setAdapter(adapter);

        return view;
    }
}
