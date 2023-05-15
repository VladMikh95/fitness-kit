package ml.vladmikh.projects.fitness_kit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ml.vladmikh.projects.fitness_kit.data.model.Lesson
import ml.vladmikh.projects.fitness_kit.data.model.Schedule
import ml.vladmikh.projects.fitness_kit.data.model.Trainer
import ml.vladmikh.projects.fitness_kit.data.repository.Repository
import ml.vladmikh.projects.fitness_kit.ui.modelui.LessonUI
import retrofit2.Response

class FitnessKitViewModel(private val repository: Repository) : ViewModel() {

    val lessonsUIList = ArrayList<LessonUI>()

    fun getScheduleData() {
        viewModelScope.launch {
            try {
                 val scheduleData = repository.getScheduleData()
                getListOfLessons(scheduleData)
                // list of users from the network
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }
    }

    fun getListOfLessons(scheduleData: Response<Schedule>) {

        val scheduleBody = scheduleData.body()

        if (scheduleData.isSuccessful && scheduleBody != null) {
            val lessonsList = scheduleBody.lessons
            val trainersList = scheduleBody.trainers

            transformLessonsToLessonsUI(lessonsList, trainersList)
        }


    }

    private fun transformLessonsToLessonsUI(lessonsList: List<Lesson>, trainersList: List<Trainer>){
        for (lesson in lessonsList) {
            val nameofTrainer = getTrainerNameById(lesson.coach_id, trainersList)
            val lessonUI = LessonUI(lesson.appointment_id,
                nameofTrainer,
                lesson.color,
                lesson.date,
                lesson.endTime,
                lesson.name,
                lesson.place, lesson.startTime, " :  "
            )
            lessonsUIList.add(lessonUI)
        }
        lessonsUIList.sortBy { it.date }
        lessonsUIList.forEach { println(it) }
    }

    private fun getTrainerNameById (id : String, trainersList: List<Trainer>) : String {
        var nameOfTrainer  = ""
        for (trainer in trainersList) {
           if (trainer.id == id) {
               nameOfTrainer = trainer.full_name
           }
        }
        return nameOfTrainer
    }

}