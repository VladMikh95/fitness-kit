package ml.vladmikh.projects.fitness_kit.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ml.vladmikh.projects.fitness_kit.data.repository.Repository

@Suppress("UNCHECKED_CAST")
class FitnessKitViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(FitnessKitViewModel::class.java)) {
            return FitnessKitViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}