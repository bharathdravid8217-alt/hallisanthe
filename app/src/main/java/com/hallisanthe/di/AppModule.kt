package com.hallisanthe.di

import android.content.Context
import androidx.room.Room
import com.hallisanthe.data.local.AppDatabase
import com.hallisanthe.data.local.ProductDao
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "halli_santhe.db").build()

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao = database.productDao()
}
