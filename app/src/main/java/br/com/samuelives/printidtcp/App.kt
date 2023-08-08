package br.com.samuelives.printidtcp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import br.com.samuelives.printidtcp.common.Constants
import dagger.hilt.android.HiltAndroidApp

val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(Constants.PREFERENCES_NAME)

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

    }

}