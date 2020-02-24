package ru.indychkov.tollroads.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.indychkov.tollroads.model.TollRoad;
import ru.indychkov.tollroads.model.TollRoadPrice;


@Database(entities = {TollRoad.class, TollRoadPrice.class}, version = 1, exportSchema = false)
public abstract class TollRoadsDatabase extends RoomDatabase {
    private static final String TAG = "TollRoadsDatabase";
    public abstract TollRoadDao tollRoadDao();
    private static final Object LOCK = new Object();
    private static volatile TollRoadsDatabase sInstance;
    private static final String DATABASE_NAME = "tollroad.db";

    public static TollRoadsDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    Log.d(TAG, "getInstance: creating new database Instance");
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            TollRoadsDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return sInstance;
    }

}