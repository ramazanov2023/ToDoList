package com.example.todolist.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {TaskEntry.class},version = 1,exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private final static String LOG = AppDatabase.class.getSimpleName();
    private final static Object LOCK = new Object();
    private final static String DATABASE_NAME = "todolist";
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance==null){
            synchronized (LOCK){
                instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DATABASE_NAME)
                        //Временное включение разрешения на запрос базы данных из основного потока
                        .allowMainThreadQueries()
                        .build();
                Log.e(LOG,"GRANTED");
            }
        }
        return instance;
    }

    public abstract TaskDao taskDao();

}
