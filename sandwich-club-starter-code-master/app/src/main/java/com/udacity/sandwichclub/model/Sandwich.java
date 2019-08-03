package com.udacity.sandwichclub.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Sandwich implements Parcelable {


    public Sandwich(Parcel in) {
        this.mainName = in.readString();
        this.alsoKnownAs = in.readArrayList(null);
        this.description = in.readString();
        this.image = in.readString();
        this.ingredients = in.readArrayList(null);
        this.placeOfOrigin = in.readString();
    }


    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(mainName);
        parcel.writeString(image);
        parcel.writeString(description);
        parcel.writeString(placeOfOrigin);
        parcel.writeList(ingredients);
        parcel.writeList(alsoKnownAs);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Sandwich createFromParcel(Parcel in) {
            return new Sandwich(in);
        }

        public Sandwich[] newArray(int size) {
            return new Sandwich[size];
        }
    };

}
