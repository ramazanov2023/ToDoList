package com.example.todolist;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static final Object LOCK = new Object();
    private static AppExecutors instance;
    private Executor discIO;

    public static  AppExecutors getInstance(){
        if(instance==null){
            synchronized (LOCK) {
                instance = new AppExecutors(Executors.newSingleThreadExecutor());
            }
        }
        return instance;
    }

    public AppExecutors(Executor discIO) {
        this.discIO = discIO;
    }

    public Executor discIO(){
        return discIO;
    }
}
