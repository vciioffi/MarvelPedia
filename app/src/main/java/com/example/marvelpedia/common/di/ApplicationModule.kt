package com.example.marvelpedia.common.di


import android.content.Context
import androidx.room.Room
import com.example.marvelpedia.common.db.AppDatabase
import com.example.marvelpedia.common.utils.getRetrofit
import com.example.marvelpedia.heroes.data.HeroesComicsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideQuoteApiClient(retrofit: Retrofit):HeroesComicsApiService{
        return retrofit.create(HeroesComicsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "marvel_pedia_db").fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideHeroesDao(db: AppDatabase) = db.getHeroesDao()

    @Singleton
    @Provides
    fun provideComicsDao(db: AppDatabase) = db.getComicsDao()
}