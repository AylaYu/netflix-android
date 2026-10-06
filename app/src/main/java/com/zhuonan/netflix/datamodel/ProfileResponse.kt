package com.zhuonan.netflix.datamodel

// Response returned by GET /profile.
data class ProfileResponse(
    val profile: Profile?,
    val recentViewed: List<Video>?
)