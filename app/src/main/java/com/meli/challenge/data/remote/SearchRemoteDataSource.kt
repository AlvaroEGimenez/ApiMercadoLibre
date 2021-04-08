package com.meli.challenge.data.remote

import com.meli.challenge.api.SearchService
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(private val searchService: SearchService)
    :BaseDataSource(){
        suspend fun search(query: String)= getResult{searchService.search(query)}
}