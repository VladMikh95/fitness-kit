package ml.vladmikh.projects.fitness_kit.di

import dagger.Module
import dagger.Provides
import ml.vladmikh.projects.fitness_kit.data.repository.Repository
import ml.vladmikh.projects.fitness_kit.ui.viewmodel.FitnessKitViewModelFactory

@Module
class ViewModelModule {

    @Provides
    fun providesFitnessKitViewModelFactory(repository: Repository) : FitnessKitViewModelFactory {

        return FitnessKitViewModelFactory(repository)
    }
}