package com.android.pregnancyvitalstracker.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.pregnancyvitalstracker.data.local.entities.LocalItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(localItem: LocalItem)

    @Query("SELECT * from items ")
    fun getAllItems(): Flow<List<LocalItem>>


}