package com.pixelhubllc.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import static com.pixelhubllc.dictionary.MainActivity.words;

public class WordsFragment extends Fragment {
    ArrayList<Model> getwords =new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_words,container, false);

        ListView wordIndexList =(ListView) view.findViewById(R.id.wordlistid);

        getwords = words;
        Log.d("TAG_ERR", "onCreate: " +getwords.size());
        ArrayList<String> word=new ArrayList<>();
        for (Model model:getwords){
            word.add(model.getEn_words());
        }
        //getwords.get(position).getId();
//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
//        databaseAccess.open();
//        List<String> words = databaseAccess.getWords();
//        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,word);
        wordIndexList.setAdapter(adapter);

        wordIndexList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int word_id = words.get(position).getId();
                Log.d("TAG, ", "onClick: " + word_id);
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("id", word_id);
                startActivity(intent);
            }
        });

        return view;
    }
}
