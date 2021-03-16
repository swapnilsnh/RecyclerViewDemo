package com.snh.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snh.recyclerviewdemo.util.Logger;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LinkedList<String> mWordList;
    private final LayoutInflater mInflater;
    private final WordListAdapter.WordListItemClickListener itemClickListener;

    public WordListAdapter(Context context, LinkedList<String> mWordList, WordListItemClickListener listener) {
        this.mWordList = mWordList;
        mInflater = LayoutInflater.from(context);
        this.itemClickListener = listener;
        Logger.logger("WordListAdapter Class constructor initialized\n" +
                "with context and Linkedlist:worlist");
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Logger.logger("WordListAdapter > onCreateViewHolder");
        View view = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(view, this, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        Logger.logger("WordListAdapter > onBindViewHolder");
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        Logger.logger("WordListAdapter > getItemCount");
        return mWordList.size();
    }

    interface WordListItemClickListener {
        void onWordClick(int pos);
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        final WordListAdapter mAdapter;
        private final WordListItemClickListener listener;

        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter, WordListItemClickListener listener) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.tvWord);
            this.mAdapter = adapter;
            this.listener = listener;
            itemView.setOnClickListener(this);
            Logger.logger("WordListAdapter.WordViewHolder Constructor called");
        }

        @Override
        public void onClick(View v) {
            listener.onWordClick(getAdapterPosition());
        }
    }
}
