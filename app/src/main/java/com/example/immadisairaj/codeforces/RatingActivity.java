package com.example.immadisairaj.codeforces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.immadisairaj.codeforces.Api.Api;
import com.example.immadisairaj.codeforces.Api.Rating.Rating;
import com.example.immadisairaj.codeforces.Api.Rating.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RatingActivity extends AppCompatActivity {
    public String handle;

    private int countOfCalls;

    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        countOfCalls = 0;

        View loadingIndicator = findViewById(R.id.loading_indicator_info);
        loadingIndicator.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        handle = bundle.getString("handle");

        fetchApi();

        swipeContainer = findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {

                fetchApi();
            }

        });

    }

    public void fetchApi() {                          //This is to get rating.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Rating> call = api.getRating(handle);

        call.enqueue(new Callback<Rating>() {
            @Override
            public void onResponse(Call<Rating> call, Response<Rating> response) {

                Log.v("url", call.request().url().toString());

                Rating rating = response.body();
                TextView textview;
                String status;
                if (rating != null) {
                    status = rating.getStatus();
                    if (status.equals("OK")) {
                        countOfCalls++;
                        swipeContainer.setRefreshing(false);
                        View loadingIndicator = findViewById(R.id.loading_indicator_info);
                        loadingIndicator.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), handle, Toast.LENGTH_SHORT).show();
                        showRating(rating);
                    } else {
                        textview= findViewById(R.id.tv_review);
                        textview.setText("How can I give a review if there's no information na? :p");
                        Toast.makeText(getApplicationContext(), "Wrong handle, Try Again", Toast.LENGTH_SHORT).show();
                        RatingActivity.super.onBackPressed();
                    }
                } else {
                    textview= findViewById(R.id.tv_review);
                    textview.setText("How can I give a review if there's no information na? :p");
                    Toast.makeText(getApplicationContext(), "Wrong handle, Try Again", Toast.LENGTH_SHORT).show();
                    RatingActivity.super.onBackPressed();
                }

            }

            @Override
            public void onFailure(Call<Rating> call, Throwable t) {
                swipeContainer.setRefreshing(false);
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                if (countOfCalls == 0) {
                    //InfoActivity.super.onBackPressed();
                }
            }
        });
    }

    public void showRating(Rating rating) {

        List<Result> results = rating.getResult();

        Result result = results.get(0);

        TextView textView = findViewById(R.id.tv_contestName_rating);
        if (result.getContestName() != null)
            textView.setText("Last attempted contest.. \n" + result.getContestName());
        else
            textView.setText("No attempted contests...");

        textView = findViewById(R.id.tv_contestId_rating);
        if (result.getContestId() != null)
            textView.setText("Contest ID : " + result.getContestId());
        else
            textView.setText("No contests = no ID");

        textView = findViewById(R.id.tv_handle_rating);
        if (result.getHandle() != null)
            textView.setText("Handle : " + result.getHandle());
        else
            textView.setText("Handle : Not Provided by user");

        textView = findViewById(R.id.tv_rank_rating);
        if (result.getRank() != null)
            textView.setText("Rank : " + result.getRank().toString());
        else
            textView.setText("No ranking");

        textView = findViewById(R.id.tv_your_ratings_are_rating);
        textView.setText("Your ratings are....");

        textView = findViewById(R.id.tv_old_rating_rating);
        textView.setText("Old Ratings : " + result.getOldRating());

        textView = findViewById(R.id.tv_new_rating_rating);
        textView.setText("New Ratings : " + result.getNewRating());
        textView = findViewById(R.id.tv_review_heading);
        textView.setVisibility(View.VISIBLE);
        textView = findViewById(R.id.tv_review);
        textView.setVisibility(View.VISIBLE);
        String textContainer="";
        if(result.getOldRating()>result.getNewRating()){
            if(result.getNewRating()>1825){
                textContainer=" Keep up the good work. It's just a competition, don't get dejected.";
            }else{
                textContainer="You can improve. Don't stop trying.";
            }
        }else{
            if(result.getNewRating()>1825){
                textContainer="Keep up the good work. You are improving.";
            }else{
                textContainer="Keep trying na, you are getting better at this.";
            }
        }
        textView.setText(textContainer);
        Button button = findViewById(R.id.button_submission);
        button.setVisibility(View.VISIBLE);
    }
    public void onClickSubmission(View view) {
        Intent intent = new Intent(this, SubmissionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("handle", handle);
        intent.putExtras(bundle);
        TextView textView;
        textView = findViewById(R.id.tv_review);
        textView.setVisibility(View.GONE);
        startActivity(intent);
    }
}
