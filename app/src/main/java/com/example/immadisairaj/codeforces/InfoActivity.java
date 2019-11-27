package com.example.immadisairaj.codeforces;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.immadisairaj.codeforces.Api.Api;
import com.example.immadisairaj.codeforces.Api.Info.Info;
import com.example.immadisairaj.codeforces.Api.Info.Result;

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
    private CoordinatorLayout coordinatorLayout;
    private static final Handler handler = new Handler();
    public static final int BACKPRESS_DELAY_SECONDS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        countOfCalls = 0;

        View loadingIndicator = findViewById(R.id.loading_indicator_info);
        loadingIndicator.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        handle = bundle.getString(getString(R.string.label_handle));

        fetchApi();

        swipeContainer = findViewById(R.id.swipeContainer);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
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
                    if (status.equals(getString(R.string.label_ok))) {
                        countOfCalls++;
                        swipeContainer.setRefreshing(false);
                        View loadingIndicator = findViewById(R.id.loading_indicator_info);
                        loadingIndicator.setVisibility(View.INVISIBLE);
                        Snackbar.make(coordinatorLayout, handle, Snackbar.LENGTH_SHORT).show();
                        showInfo(info);
                    } else {
                        Snackbar.make(coordinatorLayout, getString(R.string.msg_wrong_handle), Snackbar.LENGTH_SHORT).show();
                        delayedBackpress();
                        //InfoActivity.super.onBackPressed();
                    }
                } else {
                    Snackbar.make(coordinatorLayout, getString(R.string.msg_wrong_handle), Snackbar.LENGTH_SHORT).show();
                    delayedBackpress();
                    //InfoActivity.super.onBackPressed();
                }

            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                swipeContainer.setRefreshing(false);
                Snackbar.make(coordinatorLayout, "No Internet Connection", Snackbar.LENGTH_SHORT)
                        .setAction("Settings", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                startActivity(intent);
                            }
                        })
                        .show();
                if (countOfCalls == 0)
                    delayedBackpress();
                // InfoActivity.super.onBackPressed();
            }
        });
    }

    // Navigates to previous activity after specified delay
    private void delayedBackpress() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InfoActivity.super.onBackPressed();
            }
        }, BACKPRESS_DELAY_SECONDS * 1000);
    }

    public void showInfo(Info info) {

        List<Result> results = info.getResult();

        Result result = handleNulls(results.get(0));

        ImageView imageView = findViewById(R.id.iv_image);
        Glide.with(this).load("https:" + result.getTitlePhoto()).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TextView textView = findViewById(R.id.tv_name_info);
        textView.setText(getString(R.string.msg_info_template,
                getString(R.string.label_name),
                getString(R.string.msg_name_template, result.getFirstName(), result.getLastName())));

        textView = findViewById(R.id.tv_country_info);
        textView.setText(getString(R.string.msg_info_template, getString(R.string.label_location),
                result.getAddress()));

        textView = findViewById(R.id.tv_organization_info);
        textView.setText(getString(R.string.msg_info_template, getString(R.string.label_organization),
                result.getOrganization()));

        textView = findViewById(R.id.tv_rating_info);
        textView.setText(getString(R.string.msg_info_template, getString(R.string.label_rating),
                String.valueOf(result.getRating())));

        textView = findViewById(R.id.tv_max_rating_info);
        textView.setText(getString(R.string.msg_info_template, getString(R.string.label_max_rating),
                String.valueOf(result.getMaxRating())));

        textView = findViewById(R.id.tv_rank_info);
        textView.setText(getString(R.string.msg_info_template, getString(R.string.label_rank), result.getRank()));

        textView = findViewById(R.id.tv_max_rank_info);
        textView.setText(getString(R.string.msg_info_template, getString(R.string.label_max_rank),
                result.getMaxRank()));

        Button button = findViewById(R.id.button_submission);
        button.setVisibility(View.VISIBLE);

    }

    private Result handleNulls(Result result) {
        final String na = getResources().getString(R.string.msg_na);
        if (result.getFirstName() == null)
            result.setFirstName(na);
        if (result.getLastName() == null)
            result.setLastName(na);
        if (result.getCity() == null) {
            result.setCity(na);
            if (result.getCountry() == null) {
                result.setAddress(na);
                result.setCountry(na);
            } else {
                result.setAddress(result.getCountry());
            }
        } else {
            result.setAddress(getString(R.string.msg_address_template,
                    result.getCity(), result.getCountry()));
        }
        if (result.getCountry() == null)
            result.setCountry(na);
        if (result.getMaxRank() == null)
            result.setMaxRank(na);
        if (result.getOrganization() == null)
            result.setOrganization(na);
        if (result.getRank() == null) {
            result.setRank(na);
            result.setRating(0);
            result.setMaxRating(0);
        }
        return result;
    }

    public void onClickSubmission(View view) {
        Intent intent = new Intent(this, SubmissionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.label_handle), handle);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}