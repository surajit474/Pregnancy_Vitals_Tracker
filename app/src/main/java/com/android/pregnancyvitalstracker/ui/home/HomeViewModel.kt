package com.android.pregnancyvitalstracker.ui.home

import android.content.Context
import android.util.Log
import android.util.Log.e
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pregnancyvitalstracker.data.local.entities.LocalItem
import com.android.pregnancyvitalstracker.data.local.repository.LocalItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localItemRepository: LocalItemRepository,
    ) : ViewModel() {


    val allItems: StateFlow<List<LocalItem>> =
        localItemRepository.getAllItemsStream()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )


    fun insertItem(item: LocalItem){
        viewModelScope.launch {
            localItemRepository.insertItem(item = item)
        }
    }


  }

