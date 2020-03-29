package com.pixelhubllc.dictionary.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pixelhubllc.dictionary.DetailsActivity;
import com.pixelhubllc.dictionary.R;
import com.pixelhubllc.dictionary.database.DatabaseAccess;
import com.pixelhubllc.dictionary.model.Model;

import java.util.ArrayList;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private static ArrayList<Model> words;
    DatabaseAccess databaseAccess;
    private static final String TAG = "HistoryAdapter";

    public HistoryAdapter(Context mContext, ArrayList<Model> words) {
        this.words = words;
        this.context = mContext;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.search_suggestions_list_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
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
                    intent.putExtra("class", "SearchSuggestionAdapter");
                    context.startActivity(intent);
                }
            });
        }

        public void bindTo(Model currentWord) {
            wordsTv.setText(currentWord.getEn_words());
        }

    }

    public void deleteWordFromHistory(int pos)
    {
        //GET ID
        Log.e("Position",pos+"");
        if (pos<words.size()-1){
            Model model=words.get(pos);
            int id=model.getId();
            Log.e(TAG, "deleteWordFromHistory: " + id );
            databaseAccess = DatabaseAccess.getInstance(context);
            databaseAccess.open();
            if(databaseAccess.delete(id))
            {
                words.remove(pos);

            }else
            {
                Toast.makeText(context,"Unable To Delete",Toast.LENGTH_SHORT).show();
            }

//            databaseAccess.close();

            this.notifyItemRemoved(pos);
        }else {
            if (words.size()==1){
                Model model=words.get(0);
                int id=model.getId();
                Log.e(TAG, "deleteWordFromHistory: " + id );
                databaseAccess = DatabaseAccess.getInstance(context);
                databaseAccess.open();
                if(databaseAccess.delete(id))
                {
                    words.remove(0);

                }else
                {
                    Toast.makeText(context,"Unable To Delete",Toast.LENGTH_SHORT).show();
                }

//            databaseAccess.close();

                this.notifyItemRemoved(0);
            }
        }
        if (pos == 0 && words.size()==0){
            words.clear();
        }
    }

}
