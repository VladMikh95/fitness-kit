package ml.vladmikh.projects.fitness_kit

import android.app.Application
import ml.vladmikh.projects.fitness_kit.di.AppComponent
import ml.vladmikh.projects.fitness_kit.di.DaggerAppComponent

class App : Application() {

    //lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder().build()
    }
}
lateinit var component: AppComponent