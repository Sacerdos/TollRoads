package ru.indychkov.tollroads;

import java.util.concurrent.Executors;

public class Executor {
    private static final Object LOCK = new Object();
    private static Executor sInstance;
    private final java.util.concurrent.Executor threadDB;
    private final java.util.concurrent.Executor network;

    private Executor(java.util.concurrent.Executor threadDB, java.util.concurrent.Executor network) {
        this.threadDB = threadDB;
        this.network = network;
    }

    public static Executor getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Executor(Executors.newSingleThreadExecutor(),
                        Executors.newSingleThreadExecutor());
            }
        }
        return sInstance;
    }

    public java.util.concurrent.Executor threadDB() {
        return threadDB;
    }
    public java.util.concurrent.Executor network() {
        return network;
    }
}
