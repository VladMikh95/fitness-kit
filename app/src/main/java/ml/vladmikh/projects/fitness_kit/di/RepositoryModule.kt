package ml.vladmikh.projects.fitness_kit.di

import dagger.Module
import dagger.Provides
import ml.vladmikh.projects.fitness_kit.data.repository.Repository
import ml.vladmikh.projects.fitness_kit.data.retrofit.RetrofitService
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(retrofitService: RetrofitService): Repository {

        return Repository(retrofitService)
    }
}