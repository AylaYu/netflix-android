package com.zhuonan.netflix.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zhuonan.netflix.player.VideoPlayerScreen
import com.zhuonan.netflix.ui.home.HomeScreen
import com.zhuonan.netflix.ui.profile.ProfileScreen
import com.zhuonan.netflix.ui.videodetail.VideoDetailScreen

@Composable
fun NetflixNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.BottomBarScreen.Home.route
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        // =========================
        // Home
        // =========================
        composable(
            route = Screen.BottomBarScreen.Home.route
        ) {

            HomeScreen(
                onVideoClick = { videoId ->

                    navController.navigate(
                        Screen.VideoDetail.createRoute(videoId)
                    )
                }
            )
        }


        // =========================
        // Video Detail
        // =========================
        composable(
            route = Screen.VideoDetail.route,
            arguments = listOf(
                navArgument(Screen.ARG_VIDEO_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val videoId =
                backStackEntry.arguments
                    ?.getString(Screen.ARG_VIDEO_ID)
                    ?: return@composable


            VideoDetailScreen(
                videoId = videoId,

                onBackClick = {
                    navController.popBackStack()
                },

                onPlayClick = { videoUrl ->

                    Log.d(
                        "Playback",
                        "Play Click $videoUrl"
                    )

                    navController.navigate(
                        Screen.Player.createRoute(videoUrl)
                    )
                }
            )
        }


        // =========================
        // Profile
        // =========================
        composable(
            route = Screen.BottomBarScreen.Profile.route
        ) {

            ProfileScreen(
                onVideoClick = { videoId ->

                    navController.navigate(
                        Screen.VideoDetail.createRoute(videoId)
                    )
                }
            )
        }


        // =========================
        // Player
        // =========================
        composable(
            route = Screen.Player.route,

            arguments = listOf(
                navArgument(Screen.ARG_VIDEO_URL) {
                    type = NavType.StringType
                }
            )

        ) { backStackEntry ->

            val videoUrl =
                backStackEntry.arguments
                    ?.getString(Screen.ARG_VIDEO_URL)
                    ?.let { Uri.decode(it) }


            if (videoUrl.isNullOrBlank()) {

                LaunchedEffect(Unit) {
                    navController.popBackStack()
                }

                return@composable
            }


            VideoPlayerScreen(
                videoUrl = videoUrl
            )
        }
    }
}