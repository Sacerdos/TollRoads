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

    public long getPrice_id() {
        return price_id;
    }

    public void setPrice_id(long price_id) {
        this.price_id = price_id;
    }

    public String getPart_id() {
        return part_id;
    }

    public void setPart_id(String part_id) {
        this.part_id = part_id;
    }

    @Ignore
    @Override
    public String toString() {
        return "TollRoadPrice{" +
                "price_id=" + price_id +
                ", part_id='" + part_id + '\'' +
                '}';
    }
}
