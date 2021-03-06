package com.example.manikyapavan.myudacitybaking.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Step implements Parcelable {
    @SerializedName(value = "id")
    private int mStepID;
    @SerializedName(value = "shortDescription")
    private String mStepShortDescription;
    @SerializedName(value = "description")
    private String mStepDescription;
    @SerializedName(value = "videoURL")
    private String mStepVideoURL;
    @SerializedName(value = "thumbnailURL")
    private String mStepThumbnailURL;

    public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        mStepID = id;
        mStepShortDescription = shortDescription;
        mStepDescription = description;
        mStepVideoURL = videoURL;
        mStepThumbnailURL = thumbnailURL;
    }

    protected Step(Parcel in) {
        mStepID = in.readInt();
        mStepShortDescription = in.readString();
        mStepDescription = in.readString();
        mStepVideoURL = in.readString();
        mStepThumbnailURL = in.readString();
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public void setStepID(int id) {
        mStepID = id;
    }

    public void setStepShortDescription(String shortDescription) {
        mStepShortDescription = shortDescription;
    }

    public void setStepDescription(String description) {
        mStepDescription = description;
    }

    public void setStepVideoURL(String videoURL) {
        mStepVideoURL = videoURL;
    }

    public void setmStepThumbnailURL(String thumbnailURL) {
        mStepThumbnailURL = thumbnailURL;
    }

    public int getStepID() {
        return mStepID;
    }

    public String getStepShortDescription() {
        return mStepShortDescription;
    }

    public String getStepDescription() {
        return mStepDescription;
    }

    public String getStepVideoURL() {
        return mStepVideoURL;
    }

    public String getStepThumbnailURL() {
        return mStepThumbnailURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mStepID);
        parcel.writeString(mStepShortDescription);
        parcel.writeString(mStepDescription);
        parcel.writeString(mStepVideoURL);
        parcel.writeString(mStepThumbnailURL);
    }
}
