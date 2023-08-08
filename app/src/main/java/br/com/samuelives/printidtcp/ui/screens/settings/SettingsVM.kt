package br.com.samuelives.printidtcp.ui.screens.settings

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuelives.printidtcp.App
import br.com.samuelives.printidtcp.common.Constants
import br.com.samuelives.printidtcp.preferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(application: Application) : AndroidViewModel(application) {

    var host by mutableStateOf("")
    var port by mutableStateOf(9100)

    private val preferencesDataStore: DataStore<Preferences> =
        getApplication<App>().preferencesDataStore

    init {
        loadPreferences()
    }

    private fun loadPreferences() {

        viewModelScope.launch {
            host = preferencesDataStore.data.first()[Constants.PRINTER_HOST_PREFERENCE] ?: ""
            port = preferencesDataStore.data.first()[Constants.PRINTER_PORT_PREFERENCE] ?: 9100
        }

    }

    fun save() {

        viewModelScope.launch {
            preferencesDataStore.edit { settings ->
                settings[Constants.PRINTER_HOST_PREFERENCE] = host
                settings[Constants.PRINTER_PORT_PREFERENCE] = port
            }
        }

    }

}