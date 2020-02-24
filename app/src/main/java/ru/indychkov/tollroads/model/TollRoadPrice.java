package ru.indychkov.tollroads.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "road_part_price", foreignKeys =
@ForeignKey(entity = TollRoadPart.class,
        parentColumns = "part_id",
        childColumns = "part_id",
        onDelete = CASCADE),
        indices = {@Index(value = "part_id", unique = true)})
public class TollRoadPrice {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "price_id")
    @SerializedName("price_id")
    @Expose
    private long price_id;

    @ColumnInfo(name = "part_id")
    @SerializedName("part_id")
    @Expose
    private String part_id;

}
