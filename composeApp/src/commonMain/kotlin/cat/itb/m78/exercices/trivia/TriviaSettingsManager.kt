package cat.itb.m78.exercices.trivia

data class TrivialSettings(
    val rounds: Int = 10,
    val difficulty: Int = 3
)

data object TrivialSettingsManager{
    private var settings = TrivialSettings()
    fun update(newSettings: TrivialSettings){
        settings = newSettings
    }
    fun get() = settings
}