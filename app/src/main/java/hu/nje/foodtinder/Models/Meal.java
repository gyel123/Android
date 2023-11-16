package hu.nje.foodtinder.Models;

import androidx.room.Entity;

@Entity(tableName = "meals")
public class Meal {
    public int id;

    public String title;
    public String imageUrl;
}
