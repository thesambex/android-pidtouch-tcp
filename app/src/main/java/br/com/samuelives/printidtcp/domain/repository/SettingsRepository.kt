package br.com.samuelives.printidtcp.domain.repository

import br.com.samuelives.printidtcp.domain.data.PrinterSettings

interface SettingsRepository {
    suspend fun printerSettings(): PrinterSettings
}