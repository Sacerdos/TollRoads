package ru.indychkov.tollroads.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.indychkov.tollroads.model.TollRoad;

public interface JSONPlaceHolderApi {
    @GET("/roads")
    public Call<List<TollRoad>> getRoads();
    @GET("/prices")
    public Call<List<TollRoad>> getPrices();
}
