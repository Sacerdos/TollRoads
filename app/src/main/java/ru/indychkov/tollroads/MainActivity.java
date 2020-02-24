package ru.indychkov.tollroads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import ru.indychkov.tollroads.database.TollRoadsDatabase;
import ru.indychkov.tollroads.model.TollRoadName;
import ru.indychkov.tollroads.model.TollRoadPart;

public class MainActivity extends Activity {
    private TollRoadsDatabase db;
    private Spinner roadNameSpinner;
    private Spinner roadFromSpinner;
    private Spinner roadToSpinner;
    private Switch isToMoscow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = TollRoadsDatabase.getInstance(getApplicationContext());
        roadNameSpinner= findViewById(R.id.spinner_road_name);
        roadFromSpinner = findViewById(R.id.spinner_road_from);
        roadToSpinner = findViewById(R.id.spinner_road_from);
        //isToMoscow = findViewById(R.id.switch_to_Moscow);
        new InitRoadName(this).execute();
        //new InitRoadFrom(this, isToMoscow.)
        roadNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public class InitRoadName extends AsyncTask<Void, Void, SpinnerAdapter> {
        private Context applicationContextTask;
        public InitRoadName(Context applicationContext) {
            this.applicationContextTask = applicationContext;
        }

        @Override
        protected SpinnerAdapter doInBackground(Void... voids) {
            List<TollRoadName> allTollRoadName = db.tollRoadDao().getAllTollRoadName();
            ArrayList initData = new ArrayList();
            for (TollRoadName element :
                    allTollRoadName) {
                initData.add(element.getMain_name());
            }
            SpinnerAdapter roadNameSpinnerAdapter = new ArrayAdapter<TollRoadName>(applicationContextTask,
                    android.R.layout.simple_list_item_1,
                    initData);

            return roadNameSpinnerAdapter;
        }

        @Override
        protected void onPostExecute(SpinnerAdapter spinnerAdapter) {
            super.onPostExecute(spinnerAdapter);
            roadNameSpinner.setAdapter(spinnerAdapter);
        }
    }
    /*public class InitRoadFrom extends AsyncTask<Void, Void, SpinnerAdapter> {
        private Context applicationContextTask;
        public InitRoadFrom(Context applicationContext) {
            this.applicationContextTask = applicationContext;
        }

        @Override
        protected SpinnerAdapter doInBackground(Void... voids) {
            List<TollRoadPart> allTollRoadPart = db.tollRoadDao().getAllTollRoadParts();
            ArrayList initData = new ArrayList();
            for (TollRoadPart element :
                    allTollRoadPart) {
                initData.add(element.getKm_start());
            }
            SpinnerAdapter roadPartSpinnerAdapter = new ArrayAdapter<TollRoadName>(applicationContextTask,
                    android.R.layout.simple_list_item_1,
                    initData);
            return roadPartSpinnerAdapter;
        }

        @Override
        protected void onPostExecute(SpinnerAdapter spinnerAdapter) {
            super.onPostExecute(spinnerAdapter);
            roadNameSpinner.setAdapter(spinnerAdapter);
        }
    }*/

}
