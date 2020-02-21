package ru.indychkov.tollroads.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ru.indychkov.tollroads.database.TollRoadDao;
import ru.indychkov.tollroads.database.TollRoadExecutor;
import ru.indychkov.tollroads.database.TollRoadsDatabase;

public class TollRoadRepository {
    private TollRoadDao tollRoadDao;
    private LiveData<List<TollRoad>> data;
    TollRoadRepository(Application application){
        TollRoadsDatabase db = TollRoadsDatabase.getInstance(application);
        tollRoadDao=db.tollRoadDao();
        data = tollRoadDao.getAllTollRoads();
    }
    LiveData<List<TollRoad>> getAllRoads(){
        return  data;
    }
    void insert(TollRoad road){
        TollRoadExecutor.getInstance().threadDB().execute(()->{
            tollRoadDao.addTollRoad(road);
        });
    }

}
