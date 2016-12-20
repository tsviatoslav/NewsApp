package com.example.sviatoslav.newsapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements GetNewsAsyncTask.AsyncResponse {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //Get news using newsapi
        //Result will be returned in processFinish method
        new GetNewsAsyncTask(this).execute();
    }

    @Override
    public void processFinish(String output) {
        JSONHelper helper = new JSONHelper(output);
        try {
            NewsSuite newsSuite = helper.parse();
            mAdapter = new NewsAdapter(newsSuite);
            mRecyclerView.setAdapter(mAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
