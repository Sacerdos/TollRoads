package ru.indychkov.tollroads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.lifecycle.LiveData;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ru.indychkov.tollroads.database.TollRoadsDatabase;
import ru.indychkov.tollroads.model.TollRoadName;
import ru.indychkov.tollroads.model.TollRoadPart;

public class MainActivity extends Activity {
    private TollRoadsDatabase db;
    private Spinner roadNameSpinner;
    private Spinner roadFromSpinner;
    private Spinner roadToSpinner;
    private Button calculateButton;
    private Switch isFromMoscow;
    public static boolean isFinish=false;
    private int positionRoad = 0;
    private int positionPartFrom = 0;
    private int positionPartTo = 0;
    private TextView answerTextView;
    Timer timer;
    MyTimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = new Timer();
        timerTask = new MyTimerTask();
        db = TollRoadsDatabase.getInstance(getApplicationContext());
        timer.schedule(timerTask, 1000, 1000);
        roadNameSpinner = findViewById(R.id.spinner_road_name);
        roadFromSpinner = findViewById(R.id.spinner_road_from);
        roadToSpinner = findViewById(R.id.spinner_road_to);
        isFromMoscow = findViewById(R.id.switch_to_Moscow);
        calculateButton = findViewById(R.id.button_calculate);
        answerTextView = findViewById(R.id.answer_view);
        new InitRoadName(this).execute();
        //new InitRoadFrom(this, isFromMoscow.isChecked(), positionRoad).execute();
        //new InitRoadTo(this, isFromMoscow.isChecked(), positionRoad, positionPartFrom).execute();

        roadNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionRoad = position;
                initFromListener();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        roadFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionPartFrom = position;
                initToListener();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
        roadToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionPartTo = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
        isFromMoscow.setOnCheckedChangeListener((buttonView, isChecked) -> {
            new InitRoadFrom(this, isFromMoscow.isChecked(), positionRoad).execute();
        });
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalculateRoad(isFromMoscow.isChecked()).execute();
            }
        });
    }

    public void initFromListener() {
        new InitRoadFrom(this, isFromMoscow.isChecked(), positionRoad).execute();
    }

    private void initToListener() {
        new InitRoadTo(this, isFromMoscow.isChecked(), positionRoad, positionPartFrom).execute();
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

            new InitRoadFrom(applicationContextTask, isFromMoscow.isChecked(), positionRoad);
        }
    }


    public class InitRoadFrom extends AsyncTask<Void, Void, SpinnerAdapter> {
        private Context applicationContextTask;
        private boolean isFromMoscow;
        private long roadId;


        public InitRoadFrom(Context applicationContextTask, boolean isFromMoscow, long roadId) {
            this.applicationContextTask = applicationContextTask;
            this.isFromMoscow = isFromMoscow;
            this.roadId = roadId;

        }

        @Override
        protected SpinnerAdapter doInBackground(Void... voids) {

            List<TollRoadPart> allTollRoadPart = db.tollRoadDao().getAllTollRoadPartsFromMoscow(roadId);
            ArrayList initData = new ArrayList<String>();

            for (TollRoadPart element :
                    allTollRoadPart) {
                initData.add((isFromMoscow ? element.getKm_start() : element.getKm_end()) + " km");
            }
            ArrayAdapter adapter = new ArrayAdapter<TollRoadPart>(applicationContextTask,
                    android.R.layout.simple_list_item_1,
                    initData);
            if(adapter!=null){

                isFinish=true;
            }
            return adapter;
        }

        @Override
        protected void onPostExecute(SpinnerAdapter spinnerAdapter) {
            super.onPostExecute(spinnerAdapter);

            roadFromSpinner.setAdapter(spinnerAdapter);
            new InitRoadTo(applicationContextTask, isFromMoscow, roadId, positionPartFrom).execute();
        }
    }

    public class InitRoadTo extends AsyncTask<Void, Void, SpinnerAdapter> {
        private Context applicationContextTask;
        private boolean isFromMoscow;
        private long roadId;
        private long fromPosition;

        public InitRoadTo(Context applicationContextTask, boolean isFromMoscow, long roadId, long fromPosition) {
            this.applicationContextTask = applicationContextTask;
            this.isFromMoscow = isFromMoscow;
            this.roadId = roadId;
            this.fromPosition = fromPosition;
        }

        @Override
        protected SpinnerAdapter doInBackground(Void... voids) {
            List<TollRoadPart> allTollRoadPart;
            if (isFromMoscow) {
                allTollRoadPart = db.tollRoadDao().getToTollRoadPartsFromMoscow(roadId, fromPosition);
            } else {
                allTollRoadPart = db.tollRoadDao().getToTollRoadPartsToMoscow(roadId, fromPosition);
            }
            ArrayList initData = new ArrayList();
            for (TollRoadPart element :
                    allTollRoadPart) {
                initData.add((isFromMoscow ? element.getKm_end() : element.getKm_start()) + " km");
            }
            ArrayAdapter adapter = new ArrayAdapter<TollRoadPart>(applicationContextTask,
                    android.R.layout.simple_list_item_1,
                    initData);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            return adapter;
        }

        @Override
        protected void onPostExecute(SpinnerAdapter spinnerAdapter) {
            super.onPostExecute(spinnerAdapter);
            roadToSpinner.setAdapter(spinnerAdapter);
        }
    }

    public class CalculateRoad extends AsyncTask<Void, Void, Integer> {
        private boolean isFromMoscow;

        public CalculateRoad(boolean isFromMoscow) {
            this.isFromMoscow = isFromMoscow;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            double positionStart;
            double positionEnd;
            if (isFromMoscow) {
                positionStart = positionPartFrom;
                positionEnd = positionPartFrom + positionPartTo;
            } else {
                positionStart = positionPartTo;
                positionEnd = positionPartFrom;
            }
            List<TollRoadPart> allTollRoadPart = db.tollRoadDao().getFinalPath(positionRoad, positionStart, positionEnd);
            Integer finalPath = 0;
            for (TollRoadPart element :
                    allTollRoadPart) {
                finalPath += (element.getKm_end() - element.getKm_start());
            }
            return finalPath;
        }

        @Override
        protected void onPostExecute(Integer finalPath) {
            super.onPostExecute(finalPath);
            answerTextView.setText(finalPath + "km");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }


    private void retrieveTasks() {
        Executor.getInstance().threadDB().execute(new Runnable() {
            @Override
            public void run() {
                new InitRoadName(getApplicationContext()).execute();
            }
        });
    }
    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            if(isFinish){
                timer.cancel();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    retrieveTasks();
                }
            });
        }
    }
}
