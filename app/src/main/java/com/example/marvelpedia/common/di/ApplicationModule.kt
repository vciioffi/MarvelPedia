package com.example.marvelpedia.common.di


import com.example.marvelpedia.common.utils.getRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return getRetrofit()
    }
}