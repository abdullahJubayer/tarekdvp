package com.pixelhubllc.dictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pixelhubllc.dictionary.R;
import com.pixelhubllc.dictionary.model.Model;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Model> {

    private Context context;
    private List<Model> words;


    public CustomAdapter(@NonNull Context context, List<Model> words ) {
        super(context, R.layout.liststyle, words);

        this.context = context;
        this.words = words;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.liststyle, null, true);
        TextView wordsTv =  view.findViewById(R.id.wordTv);
        wordsTv.setText(words.get(position).getEn_words());
        return view;
    }
}
