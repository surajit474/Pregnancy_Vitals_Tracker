package com.android.pregnancyvitalstracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pregnancyvitalstracker.data.local.entities.LocalItem
import com.android.pregnancyvitalstracker.data.local.repository.LocalItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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


    fun insertItem(item: LocalItem) {
        viewModelScope.launch {
            localItemRepository.insertItem(item = item)
        }
    }


}

