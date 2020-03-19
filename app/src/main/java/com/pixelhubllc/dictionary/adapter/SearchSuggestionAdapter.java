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
import com.pixelhubllc.dictionary.MainActivity;
import com.pixelhubllc.dictionary.R;
import com.pixelhubllc.dictionary.database.DatabaseAccess;
import com.pixelhubllc.dictionary.model.Model;
import java.util.ArrayList;


public class SearchSuggestionAdapter extends RecyclerView.Adapter<SearchSuggestionAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Model> words;
    DatabaseAccess databaseAccess;

    public SearchSuggestionAdapter(Context mContext, ArrayList<Model> words) {
        this.words = words;
        this.context = mContext;
    }

    @NonNull
    @Override
    public SearchSuggestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.search_suggestions_list_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSuggestionAdapter.ViewHolder holder, int position) {
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
            Model model=words.get(pos);
            int id=model.getId();
            databaseAccess = DatabaseAccess.getInstance(context);
            databaseAccess.open();
            if(databaseAccess.delete(id))
            {
                words.remove(pos);
            }else
            {
                Toast.makeText(context,"Unable To Delete",Toast.LENGTH_SHORT).show();
            }

            databaseAccess.close();

            this.notifyItemRemoved(pos);
        }

}
