package hu.nje.foodtinder.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import hu.nje.foodtinder.Models.Meal;
import hu.nje.foodtinder.Models.MealDao;

@Database(entities = {Meal.class}, version = 1)

public abstract class FoodTinderDatabase extends RoomDatabase {
    public abstract MealDao mealDao();
}
