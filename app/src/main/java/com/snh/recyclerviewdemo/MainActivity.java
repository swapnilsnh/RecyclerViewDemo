package com.snh.recyclerviewdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.snh.recyclerviewdemo.databinding.ActivityMainBinding;
import com.snh.recyclerviewdemo.util.Logger;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private final LinkedList<String> mWordList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();

    }

    private void init() {
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(view -> {
            int wordListSize = mWordList.size();
            //Add a new word to the wordlist
            mWordList.addLast(" + word" + wordListSize);
            //notify the adapter, that the data has changed.
            mRecyclerView.getAdapter().notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(wordListSize);
            Logger.logger("MainActivity FAB onClick");
        });

        initSampleList();

        /**
         * Initializing RECYCLER VIEW
         */
//        Get handle to recycler view
        mRecyclerView = findViewById(R.id.rv);
//        create adapter and supply data to be displayed
        mAdapter = new WordListAdapter(this, mWordList);
//        connect adapter with recycler view
        mRecyclerView.setAdapter(mAdapter);
//        give recycler view a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Logger.logger("MainActivity Adapter and Recycler view initialized and assigned");
    }

    private void initSampleList() {
        mWordList.clear();
        for (int i = 1; i <= 20; i++) {
            mWordList.addLast("Word " + i);
            Logger.logger("MainActivity initialized sample list with 20 items");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_reset) {
            initSampleList();
            mAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}