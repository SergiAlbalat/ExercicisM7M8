package cat.itb.m78.exercices.trivia


val questions = listOf(
    Question("When was Dead By Daylight released?",listOf("2018", "2016", "2015", "2017"),2),
    Question("Who is the main villain?",listOf("The Entity", "The Trapper", "The Killer", "The Ace"),1),
    Question("How can the survivors escape from the trials?",listOf("Powering the doors", "Powering the doors or powering the hatch", "Powering The hatch", "Powering the doors or using the hatch"),4),
    Question("What uses the killer to make sacrifices?",listOf("Altars", "Oferings", "Generators", "Hooks"),4),
    Question("What's the meaning of NOED?",listOf("No One Escapes Death", "New Ongoing Endgame Duty", "Neptune Offshore Engineering Development", "No Observed Effect Dose"),1),
    Question("From which videogame was Dead By Daylight inspired?",listOf("Alan Wake", "Friday the 13th: The Videogame", "The Resident Evil Games", "None"),1),
    Question("How many diferent types of items are in the game?",listOf("2", "5", "6", "4"),3),
    Question("What are the four main categories of point for survivors?",listOf("Reparing, Looping, Healing and Crafting", "Objectives, Healing, Breaking and Reparing", "Objectives, Boldness, Altruism and Survival", "Brutallity, Survival, Crafting and Toteming"),3),
    Question("What are the four main categories of point for killers?",listOf("Deviousness, Brutallity, Hunter and Sacrifice", "Evilness, Killing, Hunter and Ofering", "Speed, Strenght, Perception and Madness", "Reparing, Healing, Looping, Toteming"),1),
    Question("What's the name of the killer called The Shape?",listOf("Jason Voorhees", "Danny Johnson", "Michael Myers", "Charles Lee Ray"),3),
    Question("What's the name of the killer and survivor perks for totems?",listOf("Curses and Perks", "Hexes and Boons", "Hexes and Perks", "Curses and Boons"),2),
    Question("Who is the horse on the Father Campbell's Chapel in the Crotus Prenn Asylum Map?",listOf("Jeffrey", "Jeff", "Maurice", "Jay"),3),
    Question("What killers are asociated with Racoon City Realm?",listOf("The Necromancer and The Plague", "The Nemesis and The Plague", "The Nemesis and The Necromancer", "The Nemesis and The Mastermind"),4),
    Question("From who is the perk autodidact",listOf("Meg Thomas", "Claudette Morel", "Jonah Vazquez", "Adam Francis"),4),
    Question("Who is the killer with the lowest base speed?",listOf("The Pig", "The Nurse", "The Nemesis", "The Knight"),2),
)

data class Question(val question: String, var answer: List<String>, var valid: Int){
    fun shuffleAnswers(){
        val correctAnswer = answer[valid-1]
        answer = answer.shuffled()
        for(i in answer.indices){
            if(answer[i] == correctAnswer){
                valid = i+1
            }
        }
    }
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