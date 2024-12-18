package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.datasourceimpl.UserDataSourceImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsUserDataSource(
        userDataSourceImpl: UserDataSourceImpl
    ): UserDataSource

}