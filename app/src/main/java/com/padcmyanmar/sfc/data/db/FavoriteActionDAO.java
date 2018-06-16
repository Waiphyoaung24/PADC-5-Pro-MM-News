package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;

import java.util.List;

/**
 * Created by WaiPhyoAg on 6/9/18.
 */

@Dao
public interface FavoriteActionDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertFavoriteAction(FavoriteActionVO favoriteActionVO);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertFavoriteAction(FavoriteActionVO... favoriteActionVOS);

    @Query("SELECT * From FavoriteAction")
    List<FavoriteActionVO> getFavoriteAction();

    @Query("DELETE FROM FavoriteAction")
    void deleteAll();

}
