package com.zhuonan.netflix.repository

import com.zhuonan.netflix.datamodel.HomeResponse
import com.zhuonan.netflix.network.NetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax. inject.Inject
import javax. inject.Singleton

// Data layer object that fetches the Home response.
@Singleton
class HomeRepository @Inject constructor(
    private val networkApi: NetworkApi
) {
    suspend fun getHomeFeed(): HomeResponse = withContext(Dispatchers.IO) {
        networkApi.getHome().execute().body()
            ?: error("Error Home Response")
    }
}
