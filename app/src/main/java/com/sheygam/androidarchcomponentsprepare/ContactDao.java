package com.sheygam.androidarchcomponentsprepare;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY name")
    LiveData<List<ContactEntity>> getAllContacts();
    @Insert
    void addContact(ContactEntity entity);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateContact(ContactEntity entity);
    @Delete
    void deleteTask(ContactEntity entity);

    @Query("SELECT * FROM contacts WHERE id = :id")
    LiveData<ContactEntity> getContactById(int id);
}
