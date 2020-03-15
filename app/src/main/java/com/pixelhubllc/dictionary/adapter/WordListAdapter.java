package com.pixelhubllc.dictionary.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pixelhubllc.dictionary.DetailsActivity;
import com.pixelhubllc.dictionary.R;
import com.pixelhubllc.dictionary.model.Model;

import java.util.ArrayList;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Model> words;


    public WordListAdapter(Context mContext, ArrayList<Model> words) {
        this.words = words;
        this.context = mContext;
    }

    @NonNull
    @Override
    public WordListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.word_list_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.ViewHolder holder, int position) {
        Model currentWords = words.get(position);
        holder.bindTo(currentWords);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView wordsTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wordsTv = itemView.findViewById(R.id.wordTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    int word_id = words.get(position).getId();
                    Log.d("TAG, ", "onClick: " + word_id);
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("id", word_id);
                    context.startActivity(intent);
                }
            });
        }

        public void bindTo(Model currentWord) {
            wordsTv.setText(currentWord.getEn_words());
        }

    }

}
