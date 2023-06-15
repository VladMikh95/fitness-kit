package ml.vladmikh.projects.fitness_kit.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ml.vladmikh.projects.fitness_kit.ui.adapters.FitnessKitRecyclerViewAdapter.Companion.DiffCallback
import ml.vladmikh.projects.fitness_kit.ui.modelui.LessonUI
import ml.vladmikh.projects.shopapp.databinding.LessonItemBinding
import java.text.SimpleDateFormat

class FitnessKitRecyclerViewAdapter : ListAdapter<LessonUI, FitnessKitRecyclerViewAdapter.LessonViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: LessonItemBinding = LessonItemBinding.inflate(layoutInflater, parent, false)
        return LessonViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {

        holder.bind(getItem(position))
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


       @SuppressLint("SimpleDateFormat")
       private fun convertDate(date: String) : String? {

            val parser =  SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("EEEE, dd MMMM")
            val formattedDate = parser.parse(date)?.let { formatter.format(it) }
            return formattedDate
        }


    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<LessonUI>() {
            override fun areItemsTheSame(oldLessonUI: LessonUI, newLessonUI: LessonUI): Boolean {
                return oldLessonUI === newLessonUI
            }

            override fun areContentsTheSame(oldLessonUI: LessonUI, newLessonUI: LessonUI): Boolean {
                return (oldLessonUI.appointmentId == newLessonUI.appointmentId
                        || oldLessonUI.coachName == newLessonUI.coachName
                        || oldLessonUI.color == newLessonUI.color
                        || oldLessonUI.date == newLessonUI.date
                        || oldLessonUI.endTime == newLessonUI.endTime
                        || oldLessonUI.name == newLessonUI.name
                        || oldLessonUI.place == newLessonUI.place
                        || oldLessonUI.startTime == newLessonUI.startTime
                        || oldLessonUI.durationOfLesson == newLessonUI.durationOfLesson
                        || oldLessonUI.isDateVisible == newLessonUI.isDateVisible)
            }
        }
    }
}
