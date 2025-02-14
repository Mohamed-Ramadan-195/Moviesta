package com.example.moviesta.di

import android.app.Application
import com.example.moviesta.data.manager.LocalUserManagerImpl
import com.example.moviesta.data.remote.api.MoviestaApi
import com.example.moviesta.data.repository.MoviestaRepositoryImpl
import com.example.moviesta.domain.manager.LocalUserManager
import com.example.moviesta.domain.repository.MoviestaRepository
import com.example.moviesta.domain.usecase.app_entry.AppEntryUseCases
import com.example.moviesta.domain.usecase.app_entry.ReadAppEntryUseCase
import com.example.moviesta.domain.usecase.app_entry.SaveAppEntryUseCase
import com.example.moviesta.domain.usecase.movie.GetMovieDetailsUseCase
import com.example.moviesta.domain.usecase.movie.GetMovieListsUseCase
import com.example.moviesta.domain.usecase.movie.MovieUseCases
import com.example.moviesta.domain.usecase.movie.SearchMovieUseCase
import com.example.moviesta.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // App Entry (On-Boarding)
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    // App Entry (On-Boarding)
    @Provides
    @Singleton
    fun provideAppEntryUseCases (
        localUserManager: LocalUserManager
    ) = AppEntryUseCases (
            readAppEntryUseCase = ReadAppEntryUseCase(localUserManager),
            saveAppEntryUseCase = SaveAppEntryUseCase(localUserManager)
    )

    // Remote Data
    @Provides
    @Singleton
    fun provideMoviestaApi(): MoviestaApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MoviestaApi::class.java)
    }

    // Remote Data
    @Provides
    @Singleton
    fun provideMoviestaRepository (
        // moviestaDao: MoviestaDao,
        moviestaApi: MoviestaApi
    ) : MoviestaRepository {
        return MoviestaRepositoryImpl (
            // moviestaDao = moviestaDao,
            moviestaApi = moviestaApi
        )
    }

    // Remote Data
    @Provides
    @Singleton
    fun provideMoviestaUseCases (
        moviestaRepository: MoviestaRepository
    ) : MovieUseCases {
        return MovieUseCases (
            getMovieListsUseCase = GetMovieListsUseCase(moviestaRepository),
            getMovieDetailsUseCase = GetMovieDetailsUseCase(moviestaRepository),
            searchMovieUseCase = SearchMovieUseCase(moviestaRepository)
        )
    }
}