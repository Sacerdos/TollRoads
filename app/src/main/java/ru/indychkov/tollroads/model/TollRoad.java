package ru.indychkov.tollroads.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "road")
public class TollRoad {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "km_start")
    private int km_start;

    @ColumnInfo(name = "km_end")
    private int km_end;

    @ColumnInfo(name = "section_order")
    private int section_order;

    @ColumnInfo(name = "name_part")
    private String name_part;

    @ColumnInfo(name = "length")
    private double length;

    @ColumnInfo(name = "average_time")
    private double average_time;



/*    @ColumnInfo(name = "start_coordinate_x")
    private double start_coordinate_x;
    @ColumnInfo(name = "start_coordinate_y")
    private double start_coordinate_y;*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKm_start() {
        return km_start;
    }

    public void setKm_start(int km_start) {
        this.km_start = km_start;
    }

    public int getKm_end() {
        return km_end;
    }

    public void setKm_end(int km_end) {
        this.km_end = km_end;
    }

    public int getSection_order() {
        return section_order;
    }

    public void setSection_order(int section_order) {
        this.section_order = section_order;
    }

    public String getName_part() {
        return name_part;
    }

    public void setName_part(String name_part) {
        this.name_part = name_part;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getAverage_time() {
        return average_time;
    }

    public void setAverage_time(double average_time) {
        this.average_time = average_time;
    }

/*    public double getStart_coordinate_x() {
        return start_coordinate_x;
    }

    public void setStart_coordinate_x(double start_coordinate_x) {
        this.start_coordinate_x = start_coordinate_x;
    }

    public double getStart_coordinate_y() {
        return start_coordinate_y;
    }

    public void setStart_coordinate_y(double start_coordinate_y) {
        this.start_coordinate_y = start_coordinate_y;
    }*/
}
