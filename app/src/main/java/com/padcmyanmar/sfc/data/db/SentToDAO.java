package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.SentToVO;

import java.util.List;

/**
 * Created by WaiPhyoAg on 6/9/18.
 */

@Dao
public interface SentToDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertSentTo (SentToVO sentToVO);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertSentTo (SentToVO ... sentToVOS);

    @Query("SELECT * FROM senttoaction")
    List<SentToVO>getSentTo();

    @Query("Delete from senttoaction")
    void deleteAll();
}
