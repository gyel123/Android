package hu.nje.foodtinder.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealDao {
    void insert (Meal meal);

    @Query("Select * from meals")
    LiveData<List<Meal>>getAllMeals();
}
