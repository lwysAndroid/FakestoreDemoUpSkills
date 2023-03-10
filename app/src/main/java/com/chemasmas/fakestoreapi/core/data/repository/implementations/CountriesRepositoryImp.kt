package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.apollographql.apollo3.ApolloClient
import com.chemasmas.CountriesQuery
import com.chemasmas.fakestoreapi.core.data.mappers.toSimpleCountry
import com.chemasmas.fakestoreapi.core.data.repository.CountriesRepository
import com.chemasmas.fakestoreapi.core.data.repository.models.SimpleCountry
import javax.inject.Inject

class CountriesRepositoryImp @Inject constructor(
    private val apolloClient: ApolloClient
) : CountriesRepository {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }
}