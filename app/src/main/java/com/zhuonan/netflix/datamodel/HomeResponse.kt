package com.zhuonan.netflix.datamodel
// Matches the JSON returned by GET /home.
data class HomeResponse(
    val topRecommended: Video?,
    val sections: List<Section>
)