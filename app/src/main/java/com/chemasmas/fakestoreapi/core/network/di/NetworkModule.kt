package com.chemasmas.fakestoreapi.core.network.di

import com.chemasmas.fakestoreapi.core.network.UpskillsNetworkDataSource
import com.chemasmas.fakestoreapi.core.network.retrofit.RetrofitUpskills
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun RetrofitUpskills.binds(): UpskillsNetworkDataSource
}