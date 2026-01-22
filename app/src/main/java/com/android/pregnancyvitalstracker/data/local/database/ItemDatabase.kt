package com.android.pregnancyvitalstracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.pregnancyvitalstracker.data.local.dao.ItemDao
import com.android.pregnancyvitalstracker.data.local.entities.LocalItem
import com.android.pregnancyvitalstracker.util.DateTimeConverter


@Database(entities = [LocalItem::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

}