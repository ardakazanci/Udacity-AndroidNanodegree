package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {

        String mainName = "";
        ArrayList<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin = "";
        String description = "";
        String image = "";
        ArrayList<String> ingredients = new ArrayList<>();

        try {

            JSONObject rootObject = new JSONObject(json);
            JSONObject nameObject = rootObject.getJSONObject("name");
            mainName = nameObject.get("mainName").toString();
            alsoKnownAs.add(nameObject.getJSONArray("alsoKnownAs").toString());
            placeOfOrigin = rootObject.getString("placeOfOrigin").toString();
            description = rootObject.getString("description");
            image = rootObject.getString("image");
            ingredients.add(rootObject.getJSONArray("ingredients").toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);


    }
}
