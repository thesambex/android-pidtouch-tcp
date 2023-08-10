package br.com.samuelives.printidtcp.di

import android.content.Context
import br.com.samuelives.printidtcp.domain.repository.SettingsRepository
import br.com.samuelives.printidtcp.infra.repository.SettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Repository {

    @Provides
    @Singleton
    fun providesSettingsRepository(@ApplicationContext context: Context): SettingsRepository =
        SettingsRepositoryImpl(context)

}