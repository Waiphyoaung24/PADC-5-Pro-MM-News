package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.PublicationVO;

import java.util.List;

/**
 * Created by WaiPhyoAg on 6/9/18.
 */
@Dao
public interface PublicationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPublication (PublicationVO publicationVO);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long [] insertPublication (PublicationVO ... publicationVOS);

    @Query("SELECT * FROM publication")
    List<PublicationVO>getPublication();

    @Query("Delete FROM publication")
    void deleteAll();
}
