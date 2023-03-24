package com.chemasmas.fakestoreapi.core.data.di

import com.chemasmas.fakestoreapi.core.data.repository.*
import com.chemasmas.fakestoreapi.core.data.repository.implementations.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsPerformLoginRepository(
        performLoginRepository: PerformLoginRepositoryImp
    ): PerformLoginRepository

    @Binds
    fun bindsValidateSessionRepository(
        validateSessionRepository: ValidateSessionRepositoryImp
    ): ValidateSessionRepository

    @Binds
    fun bindsCloseSessionRepository(
        closeSessionRepository: CloseSessionRepositoryImp
    ): CloseSessionRepository

    @Binds
    fun bindsCreateUserRepository(
        createUserRepository: CreateUserRepositoryImp
    ): CreateUserRepository

    @Binds
    fun bindsTokensRepository(
        tokensRepository: TokensRepositoryImp
    ): TokensRepository

    @Binds
    fun bindsUserListRepository(
        userListRepository: UserListRepositoryImp
    ): UserListRepository

    @Binds
    fun bindsUserDetailRepository(
        userDetailRepository: UserDetailRepositoryImp
    ): UserDetailRepository

    @Binds
    fun bindsRefreshTokenRepository(
        refreshTokenRepository: RefreshTokenRepositoryImp
    ): RefreshTokenRepository

    @Binds
    fun bindsCountriesRepository(
        countriesRepository: CountriesRepositoryImp
    ): CountriesRepository

    @Binds
    fun bindsCountryRepository(
        countryRepository: CountryRepositoryImp
    ): CountryRepository

}