package ru.indychkov.tollroads.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.indychkov.tollroads.model.TollRoad;
import ru.indychkov.tollroads.model.TollRoadPrice;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TollRoadDao {
    @Insert(onConflict = REPLACE)
    void addTollRoad(TollRoad tollRoad);

    @Delete
    void deleteTollRoad(TollRoad tollRoad);

    @Update
    void updateTollRoad(TollRoad tollRoad);

    @Insert
    void addPrice(TollRoadPrice tollRoadPrice);

    @Query("SELECT * from road")
    List<TollRoad> getAllTollRoads();

    @Query("SELECT * from price WHERE road_id=:road_id")
    LiveData<List<TollRoadPrice>> getAllPricesForRoad(long road_id);
}
