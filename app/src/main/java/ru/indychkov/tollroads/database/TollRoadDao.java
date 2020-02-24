package ru.indychkov.tollroads.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.indychkov.tollroads.model.TollRoad;
import ru.indychkov.tollroads.model.TollRoadName;
import ru.indychkov.tollroads.model.TollRoadPart;
import ru.indychkov.tollroads.model.TollRoadPrice;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TollRoadDao {
    @Insert(onConflict = REPLACE)
    void addTollRoadName(TollRoadName tollRoadName);

    @Delete
    void deleteTollRoadName(TollRoadName tollRoadName);

    @Update
    void updateTollRoadName(TollRoadName tollRoadName);

    @Insert(onConflict = REPLACE)
    void addTollRoadPart(TollRoadPart tollRoadPart);

    @Delete
    void deleteTollRoadPart(TollRoadPart tollRoadPart);

    @Update
    void updateTollRoadPart(TollRoadPart tollRoadPart);

    @Insert(onConflict = REPLACE)
    void addTollRoadPrice(TollRoadPrice tollRoadPrice);

    @Delete
    void deleteTollRoadName(TollRoadPrice tollRoadPrice);

    @Update
    void updateTollRoadName(TollRoadPrice tollRoadPrice);

    @Query("SELECT * from road_name")
    List<TollRoadName> getAllTollRoadName();
}
