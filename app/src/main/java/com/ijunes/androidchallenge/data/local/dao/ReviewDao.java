package com.ijunes.androidchallenge.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ijunes.androidchallenge.data.local.dao.entity.ReviewEntity;

import java.util.List;

@Dao
public interface ReviewDao {

    @Query("SELECT * FROM reviews")
    LiveData<List<ReviewEntity>> loadReviews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveReviews(List<ReviewEntity> reviewEntities);

    @Query("SELECT * FROM reviews WHERE dateUpdated=:dateUpdated")
    LiveData<ReviewEntity> getReview(String dateUpdated);

}