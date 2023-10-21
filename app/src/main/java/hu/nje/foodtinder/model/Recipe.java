package hu.nje.foodtinder.model;

import com.google.gson.annotations.SerializedName;

public class Recipe {
    @SerializedName("id")
    private int  id;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String imageUrl;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
