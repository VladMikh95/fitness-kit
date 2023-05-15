package ml.vladmikh.projects.fitness_kit.data.model

data class Schedule(
    val lessons: List<Lesson>,
    val option: Option,
    val tabs: List<Tab>,
    val trainers: List<Trainer>
)