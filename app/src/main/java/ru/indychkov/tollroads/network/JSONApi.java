package ru.indychkov.tollroads.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.indychkov.tollroads.model.TollRoadName;
import ru.indychkov.tollroads.model.TollRoadPart;
import ru.indychkov.tollroads.model.TollRoadPrice;

public interface JSONApi {
    @GET("/roads")
    public Call<List<TollRoadName>> getRoads();
    @GET("/parts")
    public Call<List<TollRoadPart>> getParts();
    @GET("/prices")
    public Call<List<TollRoadPrice>> getPrices();
}
