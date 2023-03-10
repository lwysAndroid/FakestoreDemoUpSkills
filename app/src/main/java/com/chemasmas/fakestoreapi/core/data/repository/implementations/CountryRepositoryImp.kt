package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.apollographql.apollo3.ApolloClient
import com.chemasmas.CountryQuery
import com.chemasmas.fakestoreapi.core.data.mappers.toDetailedCountry
import com.chemasmas.fakestoreapi.core.data.repository.CountryRepository
import com.chemasmas.fakestoreapi.core.data.repository.models.DetailedCountry
import javax.inject.Inject

class CountryRepositoryImp @Inject constructor(
    private val apolloClient: ApolloClient
) : CountryRepository {

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}