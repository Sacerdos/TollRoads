package ru.indychkov.tollroads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.indychkov.tollroads.database.TollRoadsDatabase;
import ru.indychkov.tollroads.model.TollRoad;
import ru.indychkov.tollroads.network.NetworkService;

public class MainActivity extends AppCompatActivity {
    private TollRoadsDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textview);
        db = TollRoadsDatabase.getInstance(getApplicationContext());

        NetworkService.getInstance()
                .getJSONApi()
                .getRoads()
                .enqueue(new Callback<List<TollRoad>>() {
                    @Override
                    public void onResponse(Call<List<TollRoad>> call, Response<List<TollRoad>> response) {
                        for (TollRoad road :
                                response.body()) {
                            textView.append(road.toString());
                            Executor.getInstance().threadDB().execute(() -> db.tollRoadDao().addTollRoad(road));

                        }
                    }

                    @Override
                    public void onFailure(Call<List<TollRoad>> call, Throwable t) {

                    }
                });
        /*Executor.getInstance().threadDB().execute(new Runnable() {
            @Override
            public void run() {

                List<TollRoad> data = db.tollRoadDao().getAllTollRoads();
                System.out.println("sfassafasfasfasfa");
                for (TollRoad a :
                        data) {
                    a.toString();
                }
                finish();
            }
        });*/
    }
}
