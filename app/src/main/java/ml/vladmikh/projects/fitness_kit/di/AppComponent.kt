package ml.vladmikh.projects.fitness_kit.di

import dagger.Component
import ml.vladmikh.projects.fitness_kit.ui.view.ScheduleFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(fragment: ScheduleFragment)
}