package com.pixelhubllc.dictionary;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pixelhubllc.dictionary.adapter.SearchSuggestionAdapter;
import com.pixelhubllc.dictionary.database.DatabaseAccess;
import com.pixelhubllc.dictionary.model.Model;
import com.pixelhubllc.dictionary.swipe.SwipeHelper;

import java.util.ArrayList;
import java.util.Collections;

public class SearchFragment extends Fragment {

    DatabaseAccess databaseAccess;
    private RecyclerView wordIndexList;
    private EditText searchViewEt;
    private static final String TAG = "SearchFragment";
    private ArrayList<Model> data, history;
    SearchSuggestionAdapter searchSuggestionAdapter;

    Context context;

    public  SearchFragment(Context context){
        this.context = context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // nullvalue = new List

        View view = inflater.inflate(R.layout.activity_search,container, false);


        databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        history = databaseAccess.getSearchHistory();



        searchViewEt = view.findViewById(R.id.searchboxEt);

        ImageView clearEt = view.findViewById(R.id.clear_et);

        wordIndexList = view.findViewById(R.id.listview);

        wordIndexList.setLayoutManager(new LinearLayoutManager(context));

//        searchSuggestionAdapter = new SearchSuggestionAdapter(getActivity(),)
//        wordIndexList.setAdapter(wordHistory);

  /*      searchSuggestionAdapter = new SearchSuggestionAdapter(getActivity(), history);
        wordIndexList.setAdapter(searchSuggestionAdapter);
        */
        searchSuggestionAdapter = new SearchSuggestionAdapter(getActivity(), history);
        wordIndexList.setAdapter(searchSuggestionAdapter);

        searchViewEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String word = s.toString();
                if (!word.isEmpty()){
                    data = databaseAccess.fetchdatabyfilter(word);
                    searchSuggestionAdapter = new SearchSuggestionAdapter(getActivity(), data);
                    wordIndexList.setAdapter(searchSuggestionAdapter);
                    Log.d("TAG",data.toString());

                } else {
                    history = databaseAccess.getSearchHistory();
                    searchSuggestionAdapter = new SearchSuggestionAdapter(getActivity(), history);
                    wordIndexList.setAdapter(searchSuggestionAdapter);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchSuggestionAdapter.notifyItemChanged(history.size()-1);
            }
        });

        clearEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserVisibleHint(true);
                searchViewEt.setText("");
            }
        });

        ItemTouchHelper.Callback callback=new SwipeHelper(searchSuggestionAdapter);
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(wordIndexList);

        //this is for remove search element by swipe
//        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
//                .SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
////                              data.remove(viewHolder.getAdapterPosition());
//                                history.remove(viewHolder.getAdapterPosition());
//                searchSuggestionAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//            }
//        });
//                helper.attachToRecyclerView(wordIndexList);

        return view;

    }

    //keyboard of on
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {
                InputMethodManager mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                mImm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                mImm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                Log.e("TAG", "setUserVisibleHint: ", e);
            }
        }
    }

}


