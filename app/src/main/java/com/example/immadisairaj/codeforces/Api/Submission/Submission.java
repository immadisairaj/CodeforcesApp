package com.example.immadisairaj.codeforces.Api.Submission;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Submission {

    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Result> result;

    public Submission(String status, List<Result> result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public List<Result> getResult() {
        return result;
    }
}