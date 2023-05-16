package ml.vladmikh.projects.fitness_kit.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ml.vladmikh.projects.fitness_kit.ui.modelui.LessonUI
import ml.vladmikh.projects.shopapp.databinding.LessonItemBinding
import java.text.SimpleDateFormat

class FitnessKitRecyclerViewAdapter : RecyclerView.Adapter<FitnessKitRecyclerViewAdapter.LessonViewHolder>(){

    private var lessons = listOf<LessonUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: LessonItemBinding = LessonItemBinding.inflate(layoutInflater, parent, false)
        return LessonViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {

        holder.bind(lessons[position])
    }

    override fun getItemCount(): Int = lessons.size


    @SuppressLint("NotifyDataSetChanged")
    fun refreshLessons(lessons: List<LessonUI>) {
        this.lessons = lessons
        notifyDataSetChanged()
    }

    class LessonViewHolder(private val binding: LessonItemBinding) : RecyclerView.ViewHolder(binding.root) {



        fun bind(lessonUI: LessonUI) {

            if (lessonUI.isDateVisible) {
                binding.textViewDateLesson.visibility = View.VISIBLE
                binding.textViewDateLesson.text = convertDate(lessonUI.date)
            } else {
                binding.textViewDateLesson.visibility = View.GONE
            }
            binding.textViewLessonStartTime.text = lessonUI.startTime
            binding.textViewLessonName.text = lessonUI.name
            binding.textViewLessonEndTime.text = lessonUI.endTime
            binding.textViewCoachName.text = lessonUI.coachName
            binding.textViewPlaceLesson.text = lessonUI.place
            binding.textViewColor.setBackgroundColor(Color.parseColor(lessonUI.color))



        }

       private fun convertDate(date: String) :String {

            val parser =  SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("EEEE, dd MMMM")
            val formattedDate = formatter.format(parser.parse(date))
            return formattedDate
        }


    }
}