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
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pixelhubllc.dictionary.adapter.CustomAdapter;
import com.pixelhubllc.dictionary.database.DatabaseAccess;
import com.pixelhubllc.dictionary.model.Model;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    DatabaseAccess databaseAccess;
    private ListView wordIndexList;
    private EditText searchViewEt;
    private static final String TAG = "SearchFragment";
    ArrayList<Model> data, nullvalue;

    Context context;

    public SearchFragment(Context context){
        this.context = context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // nullvalue = new List

        View view = inflater.inflate(R.layout.activity_search,container, false);

        databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        searchViewEt = view.findViewById(R.id.searchboxEt);

        ImageView clearEt = view.findViewById(R.id.clear_et);

        wordIndexList = view.findViewById(R.id.listview);

        searchViewEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String word = s.toString();
                if (!word.isEmpty()){
                    data = databaseAccess.fetchdatabyfilter(word);
                    CustomAdapter customAdapter = new CustomAdapter(getActivity(), data);
                    wordIndexList.setAdapter(customAdapter);
                    Log.d("TAG",data.toString());
                } else {
                    CustomAdapter customAdapter = new CustomAdapter(getActivity(), new ArrayList<Model>());
                    wordIndexList.setAdapter(customAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clearEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserVisibleHint(true);
                searchViewEt.setText("");
            }
        });



        return view;

    }

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


