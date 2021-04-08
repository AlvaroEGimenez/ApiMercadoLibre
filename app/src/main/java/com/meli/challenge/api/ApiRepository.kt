package com.meli.challenge.api

import com.meli.challenge.data.remote.SearchRemoteDataSource
import com.meli.challenge.utils.performGetOperation
import javax.inject.Inject

/** Class to create an implementation of the API endpoint "search"
 * @see com.meli.challenge.viewmodels.MainViewModel
 */
class ApiRepository @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource
) {
     fun getSearch(query: String) = performGetOperation(networkCall = {remoteDataSource.search(query)})
}