package ru.indychkov.tollroads.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "road_part", foreignKeys =
@ForeignKey(entity = TollRoadName.class,
        parentColumns = "road_id",
        childColumns = "main_road_id",
        onDelete = CASCADE), indices = {@Index(value = {"main_road_id", "part_id"}, unique = true)})
public class TollRoadPart {
    @PrimaryKey()
    @ColumnInfo(name = "part_id")
    @SerializedName("part_id")
    @Expose
    private long part_id;

    @ColumnInfo(name = "main_road_id")
    @SerializedName("main_road_id")
    @Expose
    private long main_road_id;

    @ColumnInfo(name = "km_start")
    @SerializedName("km_start")
    @Expose
    private int km_start;

    @ColumnInfo(name = "km_end")
    @SerializedName("km_end")
    @Expose
    private int km_end;

    @ColumnInfo(name = "section_order")
    @SerializedName("section_order")
    @Expose
    private double section_order;

    @ColumnInfo(name = "isFromMoscow")
    @SerializedName("isFromMoscow")
    @Expose
    private boolean isFromMoscow;

    @ColumnInfo(name = "isToMoscow")
    @SerializedName("isToMoscow")
    @Expose
    private boolean isToMoscow;


}
