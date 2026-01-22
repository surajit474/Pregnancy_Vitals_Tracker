package com.android.pregnancyvitalstracker.data.local.repository


import com.android.pregnancyvitalstracker.data.local.dao.ItemDao
import com.android.pregnancyvitalstracker.data.local.entities.LocalItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalItemRepository @Inject
constructor(val itemDao: ItemDao) {


    fun getAllItemsStream(): Flow<List<LocalItem>> = itemDao.getAllItems()

    suspend fun insertItem(item: LocalItem) = itemDao.insertItem(item)

}





