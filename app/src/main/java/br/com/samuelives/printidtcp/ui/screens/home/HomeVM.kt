package br.com.samuelives.printidtcp.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuelives.printidtcp.common.Resource
import br.com.samuelives.printidtcp.domain.usecase.PrintTestPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(private val testPage: PrintTestPageUseCase) : ViewModel() {

    var showDialog by mutableStateOf(false)
    var message by mutableStateOf("")

    fun printTestPage() {

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = testPage()) {

                is Resource.Error -> {
                    message = result.message!!
                    showDialog = true
                }

                is Resource.Loading -> {}

                is Resource.Success -> {}
            }
        }

    }

}