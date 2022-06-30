package com.example.quizapp

object Constants{

    const val USER_NAME: String = "user name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answer"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,
            "Czym jest GIT?",
            R.drawable.git,
            "Językiem programowania",
            "Środowiskiem programistycznym",
            "Systemem kontroli wersji",
            "Kompilatorem",
            3
        )
        questionsList.add(que1)

        val que2 = Question(
            2,
            "Którego państwa to flaga?",
            R.drawable.nigeria,
            "RPA",
            "Czad",
            "Nigeria",
            "Niger",
            3
        )
        questionsList.add(que2)

        val que3 = Question(
            3,
            "Co to za znak",
            R.drawable.b12,
            "zakaz wjazdu wózków ręcznych",
            "zakaz wjazdu wózków rowerowych",
            "zakaz wjazdu pojazdów z materiałami wybuchowymi lub łatwo zapalnymi",
            "akaz używania sygnałów dźwiękowych",
            1
        )
        questionsList.add(que3)

        val que4 = Question(
            4,
            "Jakim poleceniem w konsoli cmd utworzymy folder",
            R.drawable.folder,
            "ndir",
            "makedirectory",
            "newdirectory",
            "mkdir",
            4
        )
        questionsList.add(que4)



        return questionsList
    }
}