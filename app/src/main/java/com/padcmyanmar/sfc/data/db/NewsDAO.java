package com.padcmyanmar.sfc.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.NewsVO;

import java.util.List;

/**
 * Created by WaiPhyoAg on 6/8/18.
 */

@Dao
public interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertNews(NewsVO newsVO);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertNews(NewsVO... newsVOS);

    @Query("Select * From news")
    LiveData<List<NewsVO>>getAllNews();

    @Query("DELETE FROM news")
    void deleteAll();




}
