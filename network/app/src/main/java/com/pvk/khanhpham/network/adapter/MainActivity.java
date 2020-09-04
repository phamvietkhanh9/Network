package com.pvk.khanhpham.network.adapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.pvk.khanhpham.network.R;
import com.pvk.khanhpham.network.model.Item;
import com.pvk.khanhpham.network.network.APIManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tvDate, tvTitle, tvContent;
    ImageView ivCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView) findViewById(R.id.tvTile);
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvDate = (TextView) findViewById(R.id.tvDate);
        ivCover = (ImageView) findViewById(R.id.ivCover);

        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);
        service.getItemData().enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.body() == null){
                    return;
                }
                Item model = response.body();
                tvTitle.setText(model.getTitle());
                tvDate.setText(model.getDate());
                tvContent.setText(model.getContent().getDescription());
                Glide.with(MainActivity.this).load(model.getImage()).into(ivCover);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("MainActivity","onFailure: " + t);
            }
        });
    }
}