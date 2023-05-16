package ml.vladmikh.projects.fitness_kit.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ml.vladmikh.projects.fitness_kit.component
import ml.vladmikh.projects.fitness_kit.ui.adapters.FitnessKitRecyclerViewAdapter
import ml.vladmikh.projects.fitness_kit.ui.viewmodel.FitnessKitViewModel
import ml.vladmikh.projects.fitness_kit.ui.viewmodel.FitnessKitViewModelFactory
import ml.vladmikh.projects.shopapp.databinding.FragmentScheduleBinding
import javax.inject.Inject


class ScheduleFragment : Fragment() {

    @Inject
    lateinit var factory : FitnessKitViewModelFactory
    private val viewModel: FitnessKitViewModel by lazy {
        ViewModelProvider(this, factory).get(FitnessKitViewModel::class.java)
    }

    private var _binding : FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val recyclerViewLessons = binding.recyclerViewLessons
        val adapter = FitnessKitRecyclerViewAdapter()
        recyclerViewLessons.adapter = adapter

        viewModel.getLessonsUIList().observe(viewLifecycleOwner) {
            adapter.refreshLessons(it)
        }

        viewModel.getScheduleData()
        return binding.root
    }
}