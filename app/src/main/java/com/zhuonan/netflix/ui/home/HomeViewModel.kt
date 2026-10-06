package com.zhuonan.netflix.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhuonan.netflix.datamodel.Section
import com.zhuonan.netflix.datamodel.Video
import com.zhuonan.netflix.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax. inject.Inject

private const val TAG = "HomeViewModel"

// Holds Home screen state and asks the repository for data.
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    val uiState: StateFlow<HomeUiState> =
        _uiState.asStateFlow()

    fun fetchHomeFeed() {
        Log.d("Tim", Thread.currentThread().name + " 1")

        viewModelScope.launch {
            try {
                Log.d("Tim", Thread.currentThread().name + " 2")

                _uiState.value = HomeUiState.Loading

                val response = repository.getHomeFeed()

                Log.d("Tim", Thread.currentThread().name + " 4")

                Log.d(
                    TAG,
                    "Loaded home feed: ${response.sections.size} sections"
                )

                _uiState.value = HomeUiState.Success(
                    topRecommended = response.topRecommended,
                    sections = response.sections
                )

            } catch (e: Exception) {
                Log.e(TAG, "Failed to load home feed", e)

                _uiState.value = HomeUiState.Error(
                    e.message ?: "Unable to load home feed"
                )
            }
        }

        viewModelScope.launch {
            Log.d("Tim", Thread.currentThread().name + " 5")

            delay(1000)

            Log.d("Tim", Thread.currentThread().name + " 6")
        }
    }
}

sealed class HomeUiState {
    data object Loading: HomeUiState()

    data class Error(
        val message: String
    ) : HomeUiState()

    data class Success(
        val topRecommended: Video?,
        val sections: List<Section>
    ) : HomeUiState()
}