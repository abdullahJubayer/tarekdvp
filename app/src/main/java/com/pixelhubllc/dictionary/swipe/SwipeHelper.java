package com.pixelhubllc.dictionary.swipe;

import com.pixelhubllc.dictionary.adapter.SearchSuggestionAdapter;


import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Oclemmy on 5/1/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class SwipeHelper extends ItemTouchHelper.SimpleCallback {

   SearchSuggestionAdapter adapter;

    public SwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public SwipeHelper(SearchSuggestionAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.deleteWordFromHistory(viewHolder.getAdapterPosition());
    }
}
