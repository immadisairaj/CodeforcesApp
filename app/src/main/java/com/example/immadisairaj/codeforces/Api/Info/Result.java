package com.example.immadisairaj.codeforces.Api.Info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("lastOnlineTimeSeconds")
    @Expose
    private Integer lastOnlineTimeSeconds;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("friendOfCount")
    @Expose
    private Integer friendOfCount;
    @SerializedName("titlePhoto")
    @Expose
    private String titlePhoto;
    @SerializedName("handle")
    @Expose
    private String handle;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("contribution")
    @Expose
    private Integer contribution;
    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("maxRating")
    @Expose
    private Integer maxRating;
    @SerializedName("registrationTimeSeconds")
    @Expose
    private Integer registrationTimeSeconds;
    @SerializedName("maxRank")
    @Expose
    private String maxRank;
    // For profile view
    private String address;

    public Result() {
    }

    public Result(String lastName, String country, Integer lastOnlineTimeSeconds, String city, Integer rating, Integer friendOfCount, String titlePhoto, String handle, String avatar, String firstName, Integer contribution, String organization, String rank, Integer maxRating, Integer registrationTimeSeconds, String maxRank) {
        super();
        this.lastName = lastName;
        this.country = country;
        this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
        this.city = city;
        this.rating = rating;
        this.friendOfCount = friendOfCount;
        this.titlePhoto = titlePhoto;
        this.handle = handle;
        this.avatar = avatar;
        this.firstName = firstName;
        this.contribution = contribution;
        this.organization = organization;
        this.rank = rank;
        this.maxRating = maxRating;
        this.registrationTimeSeconds = registrationTimeSeconds;
        this.maxRank = maxRank;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public Integer getLastOnlineTimeSeconds() {
        return lastOnlineTimeSeconds;
    }

    public String getCity() {
        return city;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getFriendOfCount() {
        return friendOfCount;
    }

    public String getTitlePhoto() {
        return titlePhoto;
    }

    public String getHandle() {
        return handle;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getContribution() {
        return contribution;
    }

    public String getOrganization() {
        return organization;
    }

    public String getRank() {
        return rank;
    }

    public Integer getMaxRating() {
        return maxRating;
    }

    public Integer getRegistrationTimeSeconds() {
        return registrationTimeSeconds;
    }

    public String getMaxRank() {
        return maxRank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLastOnlineTimeSeconds(Integer lastOnlineTimeSeconds) {
        this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setFriendOfCount(Integer friendOfCount) {
        this.friendOfCount = friendOfCount;
    }

    public void setTitlePhoto(String titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setMaxRating(Integer maxRating) {
        this.maxRating = maxRating;
    }

    public void setRegistrationTimeSeconds(Integer registrationTimeSeconds) {
        this.registrationTimeSeconds = registrationTimeSeconds;
    }

    public void setMaxRank(String maxRank) {
        this.maxRank = maxRank;
    }
}