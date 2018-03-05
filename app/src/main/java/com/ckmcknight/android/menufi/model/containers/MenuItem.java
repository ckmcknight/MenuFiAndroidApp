package com.ckmcknight.android.menufi.model.containers;

import android.util.Log;

import com.ckmcknight.android.menufi.model.datastores.DietaryPreferenceStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.ckmcknight.android.menufi.model.datafetchers.NetworkController.TAG;

public class MenuItem {
    private int itemId;
    private int restaurantId;
    private String name;
    private String description;
    private float price;
    private float ratings;
    private int calories;
    private String pictureUri;
    private List<DietaryPreference> dietaryPreferences;


    public MenuItem(String name, String description, float price, float ratings, int calories, List<DietaryPreference> dietaryPreferences) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.ratings = ratings;
        this.calories = calories;
        this.dietaryPreferences = dietaryPreferences;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public float getRatings() {
        return ratings;
    }

    public int getCalories() { return calories; }

    public void setCalories(int cal) { calories = cal; }

    public List<DietaryPreference> getDietaryPreferences(DietaryPreference.Type type) {
        List<DietaryPreference> typedPreferences = new ArrayList<>();
        for (DietaryPreference d: dietaryPreferences) {
            if (d.getType().equals(type)) {
                typedPreferences.add(d);
            }
        }
        return typedPreferences;
    }

    public static MenuItem from(JSONObject jsonObject, DietaryPreferenceStore store) {
        try {
            String name = jsonObject.getString("name");
            String description =jsonObject.getString("description");
            float price = (float)(jsonObject.getDouble("price"));
            float rating = (float)(jsonObject.getDouble("rating"));
            int calories = (jsonObject.getInt("calories"));
            List<DietaryPreference> dietaryPreferences = store.getDietaryPreferencesList(
                    jsonObject.getJSONArray("dietaryPreferences"));
            return new MenuItem(name, description,price,rating, calories, dietaryPreferences);
        } catch(JSONException e) {
            Log.e(TAG, "error while parsing restaurant from jsonObject: " + e.getMessage());
        }
        return null;
    }

    public static List<MenuItem> menuListFrom(JSONArray jsonArray, DietaryPreferenceStore store) {
        try {
            List<MenuItem> menuItems = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                menuItems.add(MenuItem.from(jsonArray.getJSONObject(i), store));
            }
            return menuItems;
        } catch (JSONException e) {
            Log.e(TAG, "error while parsing restaurants from jsonArray: " + e.getMessage());
        }
        return null;
    }

    public static JsonCreator<MenuItem> getCreator(final DietaryPreferenceStore store) {
        return new JsonCreator<MenuItem>() {
            @Override
            public MenuItem createFromJsonObject(JSONObject object) {
                return from(object, store);
            }
        };
    }
}

