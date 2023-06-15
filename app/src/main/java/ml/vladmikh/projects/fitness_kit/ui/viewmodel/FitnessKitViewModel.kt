package ml.vladmikh.projects.fitness_kit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ml.vladmikh.projects.fitness_kit.data.model.Lesson
import ml.vladmikh.projects.fitness_kit.data.model.Schedule
import ml.vladmikh.projects.fitness_kit.data.model.Trainer
import ml.vladmikh.projects.fitness_kit.data.repository.Repository
import ml.vladmikh.projects.fitness_kit.ui.modelui.LessonUI
import retrofit2.Response
import kotlin.collections.ArrayList

class FitnessKitViewModel(private val repository: Repository) : ViewModel() {

    private var _lessonsUIList = MutableLiveData<List<LessonUI>>()
    val lessonsUIList: LiveData<List<LessonUI>> get() = _lessonsUIList

    private val _status = MutableLiveData<ScheduleApiStatus>()

    val status: LiveData<ScheduleApiStatus> = _status


    init {
        getScheduleData()
    }

    fun getScheduleData()  {
        viewModelScope.launch {
            _status.value = ScheduleApiStatus.LOADING
            try {
                _status.value = ScheduleApiStatus.DONE
                val scheduleData = repository.getScheduleData()
                getListOfLessons(scheduleData)
            } catch (e: Exception) {
                _status.value = ScheduleApiStatus.ERROR
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
        val lessons = ArrayList<LessonUI>()

        for (lesson in lessonsList) {
            val nameofTrainer = getTrainerNameById(lesson.coach_id, trainersList)
            val lessonUI = LessonUI(lesson.appointment_id,
                nameofTrainer,
                lesson.color,
                lesson.date,
                lesson.endTime,
                lesson.name,
                lesson.place, lesson.startTime,
                " :  ",
                true
            )
            lessons.add(lessonUI)
        }
        lessons.sortBy { it.date }
        _lessonsUIList.value = setDateVisibleForLessonUI(lessons)
    }

    private fun setDateVisibleForLessonUI(lessons : ArrayList<LessonUI>) : ArrayList<LessonUI> {
        for ( i in 1 until lessons.size) {
            if (lessons.get(i).date == lessons.get(i-1).date) {
                lessons.get(i).isDateVisible = false
            }
        }
        return lessons
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