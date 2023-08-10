package br.com.samuelives.printidtcp.infra.repository

import android.content.Context
import br.com.samuelives.printidtcp.common.Constants
import br.com.samuelives.printidtcp.domain.data.PrinterSettings
import br.com.samuelives.printidtcp.domain.repository.SettingsRepository
import br.com.samuelives.printidtcp.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val context: Context) :
    SettingsRepository {

    override suspend fun printerSettings(): PrinterSettings =
        context.preferencesDataStore.data.map { preferences ->
            val host = preferences[Constants.PRINTER_HOST_PREFERENCE]
            val port = preferences[Constants.PRINTER_PORT_PREFERENCE] ?: 9100
            PrinterSettings(host, port)
        }.first()

}