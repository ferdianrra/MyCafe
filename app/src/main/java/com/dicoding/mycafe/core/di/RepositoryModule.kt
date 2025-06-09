package com.dicoding.mycafe.core.di

import com.dicoding.mycafe.core.data.CartRepository
import com.dicoding.mycafe.core.data.MenuRepository
import com.dicoding.mycafe.core.domain.repository.ICartRepository
import com.dicoding.mycafe.core.domain.repository.IMenuRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(cafeRepository: MenuRepository): IMenuRepository

    @Binds
    abstract fun provideCartRepository(cartRepository: CartRepository): ICartRepository

}