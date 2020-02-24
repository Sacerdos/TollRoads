package ru.indychkov.tollroads.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "road_name", indices = {@Index(value = "road_id", unique = true)})
public class TollRoadName {
    @PrimaryKey()
    @ColumnInfo(name = "road_id")
    @SerializedName("road_id")
    @Expose
    private long road_id;

    @ColumnInfo(name = "main_name")
    @SerializedName("main_name")
    @Expose
    private String main_name;
}