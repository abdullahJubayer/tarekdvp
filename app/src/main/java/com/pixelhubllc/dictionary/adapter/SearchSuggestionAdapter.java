package com.pixelhubllc.dictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pixelhubllc.dictionary.R;
import com.pixelhubllc.dictionary.model.Model;
import java.util.ArrayList;


public class SearchSuggestionAdapter extends RecyclerView.Adapter<SearchSuggestionAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Model> words;


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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView wordsTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wordsTv = itemView.findViewById(R.id.wordTv);
        }

        public void bindTo(Model currentWord) {
            wordsTv.setText(currentWord.getEn_words());
        }

        @Override
        public void onClick(View v) {
            itemView.setOnClickListener(this);
            Model word = words.get(getAdapterPosition());
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

//            Intent detailIntent = new Intent(mContext, DetailsActivity.class);
//            detailIntent.putExtra("title", currentSport.getTitle());
//            detailIntent.putExtra("image_resource", currentSport.getImageResource());
//
//            mContext.startActivity(detailIntent);

        }
    }

//
//    public SearchSuggestionAdapter(@NonNull Context context, List<Model> words ) {
//        super(context, R.layout.search_suggestions_list_style, words);
//
//        this.context = context;
//        this.words = words;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.search_suggestions_list_style, null, true);
//        TextView wordsTv =  view.findViewById(R.id.wordTv);
//        wordsTv.setText(words.get(position).getEn_words());
//        return view;
//    }
}
