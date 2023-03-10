package com.chemasmas.fakestoreapi.presentation.config.di

import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import com.chemasmas.fakestoreapi.presentation.config.DispatchersSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DispatchersSourceModule {

    @Binds
    fun bindsDispatchersSource(
        charactersSWRepository: DispatchersSourceImp
    ): DispatchersSource

}