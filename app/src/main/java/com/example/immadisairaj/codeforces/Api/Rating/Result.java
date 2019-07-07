package com.example.immadisairaj.codeforces.Api.Rating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("contestId")
    @Expose
    private Integer contestId;
    @SerializedName("contestName")
    @Expose
    private String contestName;
    @SerializedName("handle")
    @Expose
    private String handle;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("ratingUpdateTimeSeconds")
    @Expose
    private Integer ratingUpdateTimeSeconds;
    @SerializedName("oldRating")
    @Expose
    private Integer oldRating;
    @SerializedName("newRating")
    @Expose
    private Integer newRating;

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getRatingUpdateTimeSeconds() {
        return ratingUpdateTimeSeconds;
    }

    public void setRatingUpdateTimeSeconds(Integer ratingUpdateTimeSeconds) {
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
    }

    public Integer getOldRating() {
        return oldRating;
    }

    public void setOldRating(Integer oldRating) {
        this.oldRating = oldRating;
    }

    public Integer getNewRating() {
        return newRating;
    }

    public void setNewRating(Integer newRating) {
        this.newRating = newRating;
    }

}