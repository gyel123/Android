package hu.nje.foodtinder.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealDao {
    @Insert
    void insert (Meal meal);
    @Delete
    void delete (Meal meal);
    @Query("Select * from meals")
    List<Meal>getAllMeals();
}
