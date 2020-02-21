package ru.indychkov.tollroads.database;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TollRoadExecutor {
    private static final Object LOCK = new Object();
    private static TollRoadExecutor sInstance;
    private final Executor threadDB;

    private TollRoadExecutor(Executor threadDB) {
        this.threadDB = threadDB;

    }

    public static TollRoadExecutor getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new TollRoadExecutor(Executors.newSingleThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor threadDB() {
        return threadDB;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
