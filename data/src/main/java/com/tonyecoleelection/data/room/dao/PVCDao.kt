package com.tonyecoleelection.data.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.tonyecoleelection.data.model.PVCDataEntity
import io.reactivex.Flowable

/**
 * Created by aliumujib on 20/01/2018.
 */

@Dao
interface PVCDao {

    @Query("SELECT * FROM PVC")
    fun getPVCDataList(): Flowable<List<PVCDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePVCData(pvcDataEntity: PVCDataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePVCData(pvcDataEntity: List<PVCDataEntity>)

    @Query("SELECT COUNT(*) FROM PVC")
    fun getNumberOfRows(): Int

    @Query("DELETE FROM PVC")
    fun clear()

}