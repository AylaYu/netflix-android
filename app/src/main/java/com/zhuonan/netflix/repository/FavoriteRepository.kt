package com.zhuonan.netflix.repository

import com.zhuonan.netflix.database.FavoriteDao
import com.zhuonan.netflix.database.VideoEntity
import com.zhuonan.netflix.datamodel.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
){
    // dispatch.io is not necessary here since room handles the thread while creating suspend,
    // mostly for reminding performance
    suspend fun addFavorite(video: Video) = withContext(Dispatchers.IO) {
        favoriteDao.insert(
            VideoEntity(
                id = video.id,
                title = video.title,
                posterUrl = video.posterUrl
            )
        )
    }

    suspend fun removeFavorite(videoId: String) {
        favoriteDao.deleteByVideoId(videoId)
    }

    fun isFavoriteFlow(videoId: String): Flow<Boolean> {
        return favoriteDao.isFavorite(videoId).flowOn(Dispatchers.IO)
    }

    fun getAllFavorite() : Flow<List<VideoEntity>> = favoriteDao.getAll().flowOn(Dispatchers.IO)
}