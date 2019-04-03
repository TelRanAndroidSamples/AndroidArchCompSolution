package com.sheygam.androidarchcomponentsprepare;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {ContactEntity.class},version = 1,exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class DatabaseProvider extends RoomDatabase {
    private static final String DATABASE_NAME = "contacts_db";
    private static DatabaseProvider instance;

    public static DatabaseProvider getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,DatabaseProvider.class,DATABASE_NAME)
                    .build();
        }
        return instance;
    }

    public abstract ContactDao contactDao();
}
