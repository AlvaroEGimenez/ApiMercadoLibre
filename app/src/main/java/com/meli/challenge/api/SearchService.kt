package com.meli.challenge.api

import com.meli.challenge.models.Results
import com.meli.challenge.utils.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("sites/MLA/search")
    suspend fun search (@Query("q") query : String): Response<Results>

}