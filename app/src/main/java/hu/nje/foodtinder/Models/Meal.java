package hu.nje.foodtinder.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meals")
public class Meal {
    @PrimaryKey
    public int id;

    public String title;
    public String imageUrl;
}
