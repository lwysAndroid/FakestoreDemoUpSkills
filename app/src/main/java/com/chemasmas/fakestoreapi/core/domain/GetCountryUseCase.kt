package com.chemasmas.fakestoreapi.core.domain

import com.chemasmas.fakestoreapi.core.data.repository.CountryRepository
import com.chemasmas.fakestoreapi.core.data.repository.models.DetailedCountry
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {
    suspend fun execute(code: String): DetailedCountry? {
        return countryRepository.getCountry(code = code)
    }
}