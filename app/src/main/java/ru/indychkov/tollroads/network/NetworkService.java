package ru.indychkov.tollroads.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService sInstance;
    private static final String BASE_URL = "https://dychkov.mockit.io";
    private Retrofit retrofit;
    public static NetworkService getInstance() {
        if (sInstance == null) {
            sInstance = new NetworkService();
        }
        return sInstance;
    }
    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public JSONApi getJSONApi() {
        return retrofit.create(JSONApi.class);
    }
}
