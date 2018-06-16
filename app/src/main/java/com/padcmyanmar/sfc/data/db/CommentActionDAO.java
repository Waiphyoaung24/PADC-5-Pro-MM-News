package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.CommentActionVO;

import java.util.List;

/**
 * Created by WaiPhyoAg on 6/9/18.
 */

@Dao
public interface CommentActionDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE )
    long insertCommentAction (CommentActionVO commentActionVO);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long [] insertCommentAction (CommentActionVO ... commentActionVOS);

    @Query("SELECT * FROM commentaction")
    List<CommentActionVO>getCommentAction();

    @Query("DELETE FROM commentaction")
    void deleteAll();
}
