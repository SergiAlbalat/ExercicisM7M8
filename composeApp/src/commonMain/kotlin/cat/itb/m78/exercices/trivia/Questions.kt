package cat.itb.m78.exercices.trivia

val questions = listOf(
    "When was Dead By Daylight released?",
    "Who is the main villain?",
    "How can the survivors escape from the trials?",
    "What uses the killer to make sacrifices?",
    "What's the meaning of NOED?",
    "From which videogame was Dead By Daylight inspired?",
    "How many diferent types of items are in the game?",
    "What are the four main categories of point for survivors?",
    "What are the four main categories of point for killers?",
    "What's the name of the killer called The Shape?",
    "What's the name of the killer and survivor perks for totems?",
    "Who is the horse on the Father Campbell's Chapel in the Crotus Prenn Asylum Map?",
    "What killers are asociated with Racoon City Realm?",
    "From who is the perk autodidact",
    "Who is the killer with the lowest base speed?"
)
val answers = listOf(
    listOf("2018", "2016", "2015", "2017"),
    listOf("The Entity", "The Trapper", "The Killer", "The Ace"),
    listOf("Powering the doors", "Powering the doors or powering the hatch", "Powering The hatch", "Powering the doors or using the hatch"),
    listOf("Altars", "Oferings", "Generators", "Hooks"),
    listOf("No One Escapes Death", "New Ongoing Endgame Duty", "Neptune Offshore Engineering Development", "No Observed Effect Dose"),
    listOf("Alan Wake", "Friday the 13th: The Videogame", "The Resident Evil Games", "None"),
    listOf("2", "5", "6", "4"),
    listOf("Reparing, Looping, Healing and Crafting", "Objectives, Healing, Breaking and Reparing", "Objectives, Boldness, Altruism and Survival", "Brutallity, Survival, Crafting and Toteming"),
    listOf("Deviousness, Brutallity, Hunter and Sacrifice", "Evilness, Killing, Hunter and Ofering", "Speed, Strenght, Perception and Madness", "Reparing, Healing, Looping, Toteming"),
    listOf("Jason Voorhees", "Danny Johnson", "Michael Myers", "Charles Lee Ray"),
    listOf("Curses and Perks", "Hexes and Boons", "Hexes and Perks", "Curses and Boons"),
    listOf("Jeffrey", "Jeff", "Maurice", "Jay"),
    listOf("The Necromancer and The Plague", "The Nemesis and The Plague", "The Nemesis and The Necromancer", "The Nemesis and The Mastermind"),
    listOf("Meg Thomas", "Claudette Morel", "Jonah Vazquez", "Adam Francis"),
    listOf("The Pig", "The Nurse", "The Nemesis", "The Knight")
)
val keys = listOf(2, 1, 4, 4, 1, 1, 3, 3, 1, 3, 2, 3, 4, 4, 2)

data class Question(val question: String, val answer: List<String>, val valid: Int){
    fun withAnswers(count: Int) : Question {
        if(valid > count){
            val newKey = (1..count).random()
            return Question(question, List(count){
                if(newKey-1==it){
                    answer[valid-1]
                }else{
                    answer[it]
                }
            }, newKey)
        }
        return Question(question, List(count){answer[it]}, valid)
    }
}