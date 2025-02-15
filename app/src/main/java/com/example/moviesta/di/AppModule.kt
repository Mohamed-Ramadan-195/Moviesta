package com.example.moviesta.di

import android.app.Application
import androidx.room.Room
import com.example.moviesta.data.local.MoviestaDao
import com.example.moviesta.data.local.MoviestaDatabase
import com.example.moviesta.data.manager.LocalUserManagerImpl
import com.example.moviesta.data.remote.api.MoviestaApi
import com.example.moviesta.data.repository.MoviestaRepositoryImpl
import com.example.moviesta.domain.manager.LocalUserManager
import com.example.moviesta.domain.repository.MoviestaRepository
import com.example.moviesta.domain.usecase.app_entry.AppEntryUseCases
import com.example.moviesta.domain.usecase.app_entry.ReadAppEntryUseCase
import com.example.moviesta.domain.usecase.app_entry.SaveAppEntryUseCase
import com.example.moviesta.domain.usecase.movie.base.MovieUseCasesLocal
import com.example.moviesta.domain.usecase.movie.remote.GetGenresListUseCase
import com.example.moviesta.domain.usecase.movie.remote.GetMovieDetailsUseCase
import com.example.moviesta.domain.usecase.movie.remote.GetMovieListsUseCase
import com.example.moviesta.domain.usecase.movie.remote.GetMoviesByGenreUseCase
import com.example.moviesta.domain.usecase.movie.base.MovieUseCasesRemote
import com.example.moviesta.domain.usecase.movie.local.DeleteMovieUseCase
import com.example.moviesta.domain.usecase.movie.local.GetMovieBookmarkedDetailsUseCase
import com.example.moviesta.domain.usecase.movie.local.GetMoviesBookmarkedUseCase
import com.example.moviesta.domain.usecase.movie.local.InsertMovieUseCase
import com.example.moviesta.domain.usecase.movie.remote.SearchMovieUseCase
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

    // Remote Data & Local Data
    @Provides
    @Singleton
    fun provideMoviestaRepository (
        moviestaDao: MoviestaDao,
        moviestaApi: MoviestaApi
    ) : MoviestaRepository {
        return MoviestaRepositoryImpl (
            moviestaDao = moviestaDao,
            moviestaApi = moviestaApi
        )
    }

    // Remote Data
    @Provides
    @Singleton
    fun provideMoviestaUseCases (
        moviestaRepository: MoviestaRepository
    ) : MovieUseCasesRemote {
        return MovieUseCasesRemote (
            getMovieListsUseCase = GetMovieListsUseCase(moviestaRepository),
            getMovieDetailsUseCase = GetMovieDetailsUseCase(moviestaRepository),
            searchMovieUseCase = SearchMovieUseCase(moviestaRepository),
            getGenresListUseCase = GetGenresListUseCase(moviestaRepository),
            getMoviesByGenreUseCase = GetMoviesByGenreUseCase(moviestaRepository)
        )
    }

    // Local Data
    @Provides
    @Singleton
    fun provideMoviestaLocalUseCase (
        moviestaRepository: MoviestaRepository
    ): MovieUseCasesLocal {
        return MovieUseCasesLocal (
            insertMovieUseCase = InsertMovieUseCase(moviestaRepository),
            deleteMovieUseCase = DeleteMovieUseCase(moviestaRepository),
            getMoviesBookmarkedUseCase = GetMoviesBookmarkedUseCase(moviestaRepository),
            getMovieBookmarkedDetailsUseCase = GetMovieBookmarkedDetailsUseCase(moviestaRepository)
        )
    }

    // Local Data
    @Provides
    @Singleton
    fun provideMoviestaDatabase (
        application: Application
    ): MoviestaDatabase {
        return Room.databaseBuilder (
            context = application,
            klass = MoviestaDatabase::class.java,
            name = Constant.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // Local Data
    @Provides
    @Singleton
    fun provideMoviestaDao (
        moviestaDatabase: MoviestaDatabase
    ) = moviestaDatabase.moviestaDao
}