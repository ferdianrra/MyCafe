package com.dicoding.mycafe.di

import com.dicoding.mycafe.core.domain.usecase.CartInteractor
import com.dicoding.mycafe.core.domain.usecase.CartUseCase
import com.dicoding.mycafe.core.domain.usecase.MenuInteractor
import com.dicoding.mycafe.core.domain.usecase.MenuUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideMenuUseCase(menuInteractor: MenuInteractor): MenuUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideCartUseCase(cartInteractor: CartInteractor): CartUseCase
}