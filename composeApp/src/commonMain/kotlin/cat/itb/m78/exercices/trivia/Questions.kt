package cat.itb.m78.exercices.trivia



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