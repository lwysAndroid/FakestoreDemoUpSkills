package com.chemasmas.fakestoreapi.core.data.di

import com.chemasmas.fakestoreapi.core.data.repository.CountriesRepository
import com.chemasmas.fakestoreapi.core.data.repository.CountryRepository
import com.chemasmas.fakestoreapi.core.data.repository.MakeLoginRepository
import com.chemasmas.fakestoreapi.core.data.repository.implementations.CountriesRepositoryImp
import com.chemasmas.fakestoreapi.core.data.repository.implementations.CountryRepositoryImp
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

    @Binds
    fun bindsCountriesRepository(
        CountriesRepository: CountriesRepositoryImp
    ): CountriesRepository

    @Binds
    fun bindsCountryRepository(
        CountryRepository: CountryRepositoryImp
    ): CountryRepository

}