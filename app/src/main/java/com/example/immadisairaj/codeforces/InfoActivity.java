package com.example.immadisairaj.codeforces;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.immadisairaj.codeforces.Api.Info.Info;
import com.example.immadisairaj.codeforces.Api.Info.Result;
import com.example.immadisairaj.codeforces.Api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoActivity extends AppCompatActivity {

    public String handle;

    private int countOfCalls;

    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        countOfCalls = 0;

        View loadingIndicator = findViewById(R.id.loading_indicator_info);
        loadingIndicator.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        handle = bundle.getString(getString(R.string.handle));

        fetchApi();

        swipeContainer = findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {

                fetchApi();
            }

        });
    }

    public void fetchApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Info> call = api.getInfo(handle);

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {

                Log.v("url", call.request().url().toString());

                Info info = response.body();

                String status;
                if (info != null) {
                    status = info.getStatus();
                    if (status.equals(getString(R.string.OK))) {
                        countOfCalls++;
                        swipeContainer.setRefreshing(false);
                        View loadingIndicator = findViewById(R.id.loading_indicator_info);
                        loadingIndicator.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), handle, Toast.LENGTH_SHORT).show();
                        showInfo(info);
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.WrongHandle), Toast.LENGTH_SHORT).show();
                        InfoActivity.super.onBackPressed();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.WrongHandle), Toast.LENGTH_SHORT).show();
                    InfoActivity.super.onBackPressed();
                }

            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                swipeContainer.setRefreshing(false);
                Toast.makeText(getApplicationContext(), getString(R.string.NoInternetConnection), Toast.LENGTH_SHORT).show();
                if (countOfCalls == 0)
                    InfoActivity.super.onBackPressed();
            }
        });
    }

    public void showInfo(Info info) {

        List<Result> results = info.getResult();

        Result result = results.get(0);

        ImageView imageView = findViewById(R.id.iv_image);
        Glide.with(this).load("https:" + result.getTitlePhoto()).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TextView textView = findViewById(R.id.tv_name_info);
        if (result.getFirstName() != null)
            textView.setText(getString(R.string.Name) + result.getFirstName() + " " + result.getLastName());
        else
            textView.setText(getString(R.string.Name) + getString(R.string.Not_Provided));

        textView = findViewById(R.id.tv_country_info);
        if (result.getCity() != null)
            textView.setText(getString(R.string.City) + result.getCity() + ", " + result.getCountry());
        else
            textView.setText(getString(R.string.City) + getString(R.string.City_Not_Provided));

        textView = findViewById(R.id.tv_organization_info);
        if (result.getOrganization() != null)
            textView.setText(getString(R.string.Organization) + result.getOrganization());
        else
            textView.setText(getString(R.string.Organization) + getString(R.string.Organization_Not_Provided));

        textView = findViewById(R.id.tv_rating_info);
        textView.setText(getString(R.string.Rating) + result.getRating().toString());

        textView = findViewById(R.id.tv_max_rating_info);
        textView.setText(getString(R.string.Max_Rating) + result.getMaxRating().toString());

        textView = findViewById(R.id.tv_rank_info);
        textView.setText(getString(R.string.Rank) + result.getRank());

        textView = findViewById(R.id.tv_max_rank_info);
        textView.setText(getString(R.string.Max_Rank) + result.getMaxRank());

        Button button = findViewById(R.id.button_submission);
        button.setVisibility(View.VISIBLE);

    }

    public void onClickSubmission(View view) {
        Intent intent = new Intent(this, SubmissionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.handle), handle);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}