package com.example.manikyapavan.myudacitybaking.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Recipe {
    @SerializedName(value = "id")
    private int mRecipeID;
    @SerializedName(value = "name")
    private String mRecipeName;
    @SerializedName(value = "ingredients")
    private ArrayList<Ingredient> mRecipeIngredient;
    @SerializedName(value = "steps")
    private ArrayList<Step> mRecipeStep;
    @SerializedName(value = "servings")
    private int mRecipeServings;
    @SerializedName(value = "image")
    private String mRecipeImage;

    public Recipe(int id, String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, int servings, String image) {
        mRecipeID = id;
        mRecipeName = name;
        mRecipeIngredient = ingredients;
        mRecipeStep = steps;
        mRecipeServings = servings;
        mRecipeImage = image;
    }

    public void setRecipeID(int id) {
        mRecipeID = id;
    }

    public void setRecipeName(String name) {
        mRecipeName = name;
    }

    public void setRecipeIngredient(ArrayList<Ingredient> ingredients) {
        mRecipeIngredient = ingredients;
    }

    public void setRecipeStep(ArrayList<Step> steps) {
        mRecipeStep = steps;
    }

    public void setRecipeServings(int servings) {
        mRecipeServings = servings;
    }

    public void setRecipeImage(String image) {
        mRecipeImage = image;
    }

    public int getRecipeID() {
        return mRecipeID;
    }

    public String getRecipeName() {
        return mRecipeName;
    }

    public ArrayList<Ingredient> getRecipeIngredient() {
        return mRecipeIngredient;
    }

    public ArrayList<Step> getRecipeStep() {
        return mRecipeStep;
    }

    public int getRecipeServings() {
        return mRecipeServings;
    }

    public String getRecipeImage() {
        return mRecipeImage;
    }

}
