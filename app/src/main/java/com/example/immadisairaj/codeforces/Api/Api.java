package com.example.immadisairaj.codeforces.Api;

import com.example.immadisairaj.codeforces.Api.Info.Info;
import com.example.immadisairaj.codeforces.Api.Submission.Submission;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://codeforces.com/api/";

    @GET("user.status")
    Call<Submission> getSubmission(
            @Query("handle") String handle,
            @Query("from") String from,
            @Query("count") String count);

    @GET("user.info")
    Call<Info> getInfo(
            @Query("handles") String handle);

}