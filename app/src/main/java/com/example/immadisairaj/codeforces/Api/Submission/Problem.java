package com.example.immadisairaj.codeforces.Api.Submission;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Problem {

    @SerializedName("contestId")
    private Integer contestId;
    @SerializedName("index")
    private String index;
    @SerializedName("name")
    private String name;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("problemsetName")
    private String problemsetName;

    public Problem(Integer contestId, String index, String name, List<String> tags, String problemsetName) {
        this.contestId = contestId;
        this.index = index;
        this.name = name;
        this.tags = tags;
        this.problemsetName = problemsetName;
    }

    public Integer getContestId() {
        return contestId;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getProblemsetName() {
        return problemsetName;
    }
}