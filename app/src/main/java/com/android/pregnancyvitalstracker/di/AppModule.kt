package com.android.pregnancyvitalstracker.di

import android.content.Context
import androidx.room.Room
import com.android.pregnancyvitalstracker.data.local.dao.ItemDao
import com.android.pregnancyvitalstracker.data.local.database.ItemDatabase
import com.android.pregnancyvitalstracker.data.local.repository.LocalItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): ItemDatabase {  // Changed: Return ItemDatabase, not RoomDatabase
        return Room.databaseBuilder(
            context,
            ItemDatabase::class.java,  // Changed: Use ItemDatabase::class.java
            "vital_db"
        ).build()
    }

    @Provides
    @Singleton  // Added: This should also be Singleton
    fun provideItemDao(itemDatabase: ItemDatabase): ItemDao {
        return itemDatabase.itemDao()
    }

    @Provides
    @Singleton  // Added: This should also be Singleton
    fun provideItemRepository(itemDao: ItemDao): LocalItemRepository {
        return LocalItemRepository(itemDao = itemDao)
    }
}