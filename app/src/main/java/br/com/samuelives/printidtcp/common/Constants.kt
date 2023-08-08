package br.com.samuelives.printidtcp.common

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {

    const val PREFERENCES_NAME = "settings"

    val PRINTER_HOST_PREFERENCE = stringPreferencesKey("printer_host")
    val PRINTER_PORT_PREFERENCE = intPreferencesKey("printer_port")

}