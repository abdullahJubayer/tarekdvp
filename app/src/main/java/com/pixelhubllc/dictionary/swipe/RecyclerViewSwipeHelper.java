package com.pixelhubllc.dictionary.swipe;

import com.pixelhubllc.dictionary.adapter.BookmarkAdapter;
import com.pixelhubllc.dictionary.adapter.HistoryAdapter;
import com.pixelhubllc.dictionary.adapter.SearchSuggestionAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewSwipeHelper extends ItemTouchHelper.SimpleCallback {

    public boolean isSwipeable;

    private HistoryAdapter adapter;
    private BookmarkAdapter bookmarkAdapter;

    public RecyclerViewSwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public RecyclerViewSwipeHelper(HistoryAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

//    public RecyclerViewSwipeHelper(BookmarkAdapter bookmarkAdapter) {
//        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
//        this.bookmarkAdapter = bookmarkAdapter;
//    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (adapter != null){
            adapter.deleteWordFromHistory( viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }
//        bookmarkAdapter.deleteWordFromBookmark(viewHolder.getAdapterPosition());
//        bookmarkAdapter.notifyDataSetChanged();
    }

    @Override
    public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof SearchSuggestionAdapter.ViewHolder) return 0;
        return super.getSwipeDirs(recyclerView, viewHolder);
    }


//    @Override
//    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        // Set movement flags based on the layout manager
//        recyclerView.getLayoutManager();
//        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
//        return makeMovementFlags(dragFlags, swipeFlags);
//    }

//    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        if (viewHolder instanceof SearchSuggestionAdapter.ViewHolder
//                && ((SearchSuggestionAdapter.ViewHolder) viewHolder).isSwipeable) {
//            return super.getSwipeDirs(recyclerView, viewHolder);
//        } else {
//            return 0;
//        }
//    }
}
