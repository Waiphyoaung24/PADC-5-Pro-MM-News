package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;

import java.util.List;

/**
 * Created by WaiPhyoAg on 6/9/18.
 */

@Dao
public interface ActedUserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertActedUser(ActedUserVO actedUserVO);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertPublication(ActedUserVO... actedUserVOS);

    @Query("SELECT * FROM acteduser")
    List<ActedUserVO> getActedUser();

    @Query("DELETE From acteduser")
    void deleteAll();

}
