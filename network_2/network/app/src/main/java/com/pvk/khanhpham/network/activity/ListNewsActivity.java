package com.pvk.khanhpham.network.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.pvk.khanhpham.network.R;
import com.pvk.khanhpham.network.adapter.NewsAdapter;
import com.pvk.khanhpham.network.interfaces.NewsOnClick;
import com.pvk.khanhpham.network.model.Item;

import java.util.List;

public class ListNewsActivity extends AppCompatActivity {

    private List<Item> listDatas;
    private RecyclerView rvNews;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        getData();

        adapter = new NewsAdapter(this, listDatas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

        RecyclerView rvNews = findViewById(R.id.rvNews);
        rvNews.setLayoutManager(layoutManager);
        rvNews.setAdapter(adapter);

        adapter.setiOnClick(new NewsOnClick() {
            @Override
            public void onClickItem(int position) {
                Item model = listDatas.get(position);
                Intent intent = new Intent(ListNewsActivity.this,DetailActivity.class);
                intent.putExtra("URL",model.getContent().getUrl());
                startActivity(intent);
            }
        });
    }
    private void getData() {
    }
}
