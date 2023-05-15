package ml.vladmikh.projects.fitness_kit.data.repository

import ml.vladmikh.projects.fitness_kit.data.model.Schedule
import ml.vladmikh.projects.fitness_kit.data.retrofit.RetrofitService
import retrofit2.Response

class Repository(private val retrofit: RetrofitService) {

    suspend fun getScheduleData(): Response<Schedule> {

        return retrofit.getScheduleData()
    }
}