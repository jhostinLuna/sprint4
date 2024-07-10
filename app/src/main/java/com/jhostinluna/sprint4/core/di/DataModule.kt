package com.jhostinluna.sprint4.core.di

import android.content.Context
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jhostinluna.sprint4.R
import com.jhostinluna.sprint4.data.repository.DataProviderImp
import com.jhostinluna.sprint4.data.repository.LocalDataSource
import com.jhostinluna.sprint4.data.repository.local.AppDataBase
import com.jhostinluna.sprint4.data.repository.local.LocalDataSourceImp
import com.jhostinluna.sprint4.data.repository.local.PersonDAO
import com.jhostinluna.sprint4.domain.interfaces.DataProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun getDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            name = context.getString(R.string.app_name)
        ).build()
    }
    @Provides
    fun getPersonDao(dataBase: AppDataBase): PersonDAO = dataBase.personDAO()

    @Provides
    fun provideFusedLocation(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindDataLayer {
    @Binds
    abstract fun bindDataProvider(dataProviderImp: DataProviderImp): DataProvider

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImp: LocalDataSourceImp): LocalDataSource
}