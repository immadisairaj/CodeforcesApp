package com.example.immadisairaj.codeforces.Api.Submission;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("verdict")
    private String verdict;
    @SerializedName("problem")
    private Problem problem;
    @SerializedName("creationTimeSeconds")
    private Integer creationTimeSeconds;
    @SerializedName("programmingLanguage")
    private String programmingLanguage;
    @SerializedName("timeConsumedMillis")
    private Integer timeConsumedMillis;
    @SerializedName("memoryConsumedBytes")
    private Integer memoryConsumedBytes;

    public Result(String verdict, Problem problem, Integer creationTimeSeconds, String programmingLanguage, int timeConsumedMillis, int memoryConsumedBytes) {
        this.verdict = verdict;
        this.problem = problem;
        this.creationTimeSeconds = creationTimeSeconds;
        this.programmingLanguage = programmingLanguage;
        this.timeConsumedMillis = timeConsumedMillis;
        this.memoryConsumedBytes = memoryConsumedBytes;
    }

    public String getVerdict() {
        return verdict;
    }

    public Problem getProblem() {
        return problem;
    }

    public Integer getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Integer getTimeConsumedMillis() {
        return timeConsumedMillis;
    }

    public Integer getMemoryConsumedBytes() {
        return memoryConsumedBytes;
    }
}