package ml.vladmikh.projects.fitness_kit.data.retrofit

import ml.vladmikh.projects.fitness_kit.data.model.Schedule
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("schedule/get_v3/?club_id=2\n")
    suspend fun getScheduleData(): Response<Schedule>
}