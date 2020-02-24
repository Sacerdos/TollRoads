package ru.indychkov.tollroads.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.indychkov.tollroads.Executor;
import ru.indychkov.tollroads.model.TollRoadName;
import ru.indychkov.tollroads.model.TollRoadPart;
import ru.indychkov.tollroads.model.TollRoadPrice;
import ru.indychkov.tollroads.network.NetworkService;

@Database(entities = {TollRoadName.class, TollRoadPart.class, TollRoadPrice.class}, version = 1, exportSchema = false)
public abstract class TollRoadsDatabase extends RoomDatabase {
    private static final String TAG = "TollRoadsDatabase";

    public abstract TollRoadDao tollRoadDao();

    private static final Object LOCK = new Object();
    private static volatile TollRoadsDatabase sInstance;
    private static final String DATABASE_NAME = "toll_road.db";

    public static TollRoadsDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                System.out.println("asdsadasdsadsad");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        TollRoadsDatabase.class, DATABASE_NAME)
                        .build();
                Executor.getInstance().network().execute(new Runnable() {
                    @Override
                    public void run() {

                        NetworkService.getInstance()
                                .getJSONApi()
                                .getRoads()
                                .enqueue(new retrofit2.Callback<List<TollRoadName>>() {
                                    @Override
                                    public void onResponse(Call<List<TollRoadName>> call, Response<List<TollRoadName>> response) {
                                        for (TollRoadName roadName :
                                                response.body()) {

                                            Executor.getInstance().threadDB().execute(() -> {
                                                sInstance.tollRoadDao().addTollRoadName(roadName);
                                            });

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<TollRoadName>> call, Throwable t) {

                                    }
                                });
                        NetworkService.getInstance()
                                .getJSONApi()
                                .getParts()
                                .enqueue(new retrofit2.Callback<List<TollRoadPart>>() {
                                    @Override
                                    public void onResponse(Call<List<TollRoadPart>> call, Response<List<TollRoadPart>> response) {
                                        for (TollRoadPart roadPart :
                                                response.body()) {
                                            Executor.getInstance().threadDB().execute(() -> sInstance.tollRoadDao().addTollRoadPart(roadPart));


                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<TollRoadPart>> call, Throwable t) {

                                    }
                                });


                    }

                });
                /*Executor.getInstance().network().execute(() -> NetworkService.getInstance()
                        .getJSONApi()
                        .getPrices()
                        .enqueue(new retrofit2.Callback<List<TollRoadPrice>>() {
                            @Override
                            public void onResponse(Call<List<TollRoadPrice>> call, Response<List<TollRoadPrice>> response) {
                                for (TollRoadPrice roadPrice :
                                        response.body()) {
                                    Executor.getInstance().threadDB().execute(() -> sInstance.tollRoadDao().addTollRoadPrice(roadPrice));

                                }
                            }

                            @Override
                            public void onFailure(Call<List<TollRoadPrice>> call, Throwable t) {

                            }
                        }));*/

            }
        }
        return sInstance;

    }
}