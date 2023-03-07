package com.chemasmas.fakestoreapi.core.data.di

import com.chemasmas.fakestoreapi.core.data.repository.MakeLoginRepository
import com.chemasmas.fakestoreapi.core.data.repository.implementations.MakeLoginRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsMakeLoginRepository(
        MakeLoginRepository: MakeLoginRepositoryImp
    ): MakeLoginRepository

}