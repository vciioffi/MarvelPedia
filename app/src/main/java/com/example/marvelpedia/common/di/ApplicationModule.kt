package com.example.marvelpedia.common.di


import com.example.marvelpedia.common.utils.getRetrofit
import com.example.marvelpedia.heroes.data.HeroesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return getRetrofit()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit):HeroesApiService{
        return retrofit.create(HeroesApiService::class.java)
    }
}