package ru.indychkov.tollroads.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "price", foreignKeys = @ForeignKey(entity = TollRoad.class, parentColumns = "id", childColumns = "road_id", onDelete = CASCADE))
public class TollRoadPrice {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "road_id")
    private String road_id;

    @ColumnInfo(name = "week_day")
    private String week_day;

    @ColumnInfo(name = "start_time")
    private int start_time;

    @ColumnInfo(name = "end_time")
    private int end_time;

    @ColumnInfo(name = "base_price")
    private double base_price;

    @ColumnInfo(name = "avtodor_price")
    private double avtodor_price;

    @ColumnInfo(name = "nwcc_price")
    private double nwcc_price;

    @ColumnInfo(name = "other_price")
    private double other_price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoad_id() {
        return road_id;
    }

    public void setRoad_id(String road_id) {
        this.road_id = road_id;
    }

    public String getWeek_day() {
        return week_day;
    }

    public void setWeek_day(String week_day) {
        this.week_day = week_day;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public double getAvtodor_price() {
        return avtodor_price;
    }

    public void setAvtodor_price(double avtodor_price) {
        this.avtodor_price = avtodor_price;
    }

    public double getNwcc_price() {
        return nwcc_price;
    }

    public void setNwcc_price(double nwcc_price) {
        this.nwcc_price = nwcc_price;
    }

    public double getOther_price() {
        return other_price;
    }

    public void setOther_price(double other_price) {
        this.other_price = other_price;
    }
}
