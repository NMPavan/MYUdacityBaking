package com.example.manikyapavan.myudacitybaking.utils;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.manikyapavan.myudacitybaking.R;
import com.example.manikyapavan.myudacitybaking.RecipeWidget;
import com.example.manikyapavan.myudacitybaking.models.Ingredient;

import java.util.ArrayList;

public class Preferences {
    public static final String PREFERENCE_FILE_KEY = "com.example.manikyapavan.myudacitybaking";

    public static void saveIngredientList(Context context, String recipe, ArrayList<Ingredient> ingredientArrayList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < ingredientArrayList.size(); i++) {
            String ingredient = ingredientArrayList.get(i).getIngredient() + " - " +
                    ingredientArrayList.get(i).getIngredientQuantity() + " " +
                    ingredientArrayList.get(i).getIngredientMeasure();
            editor.putString(String.valueOf(i), ingredient);
        }
        editor.apply();
        updateWidget(context, recipe);
    }

    public static void deleteAllPreferences(Context context) {
        context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE).edit().clear().apply();
    }
    private static void updateWidget(Context context, String recipe) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecipeWidget.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_recipe_list);
        RecipeWidget.updateRecipeWidget(context, appWidgetManager, appWidgetIds, recipe);
    }
}

