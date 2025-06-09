package com.dicoding.mycafe.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.mycafe.core.data.source.local.room.CartDao
import com.dicoding.mycafe.core.data.source.local.room.CartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CartDatabase = Room.databaseBuilder(
        context,
        CartDatabase::class.java, "Cart.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideCartDao(database: CartDatabase): CartDao = database.cartDao()
}