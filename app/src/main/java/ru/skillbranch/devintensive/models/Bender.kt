package ru.skillbranch.devintensive.models

import android.util.Log

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    var answerCount = 0

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val ret = question.validateAnswer(answer)
        return if (ret.isNotEmpty()) {
            "${ret}\n${question.question}" to status.color
        } else if (question.answers.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            if (status != Status.NORMAL) {
                "Это неправильный ответ\n${question.question}" to status.color
            } else {
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        DANGER(Triple(255, 120, 0)),
        WARNING(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "Bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun validateAnswer(answer: String): String {
                Log.d("M_Bender", "$answer : ${answer.matches("[A-Z].*".toRegex())}")
                return if (!answer.matches("[A-Z].*".toRegex())) {
                    "Имя должно начинаться с заглавной буквы"
                } else {
                    ""
                }
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun validateAnswer(answer: String): String {
                return if (answer.matches("[A-Z].*".toRegex())) {
                    "Профессия должна начинаться со строчной буквы"
                } else {
                    ""
                }
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun validateAnswer(answer: String): String {
                return if (!answer.none { it.isDigit() }) {
                    "Материал не должен содержать цифр"
                } else {
                    ""
                }
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun validateAnswer(answer: String): String {
                return if (!answer.all { it.isDigit() }) {
                    "Год моего рождения должен содержать только цифры"
                } else {
                    ""
                }
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun validateAnswer(answer: String): String {
                return if (!answer.all { it.isDigit() } || answer.length != 7) {
                    "Серийный номер содержит только цифры, и их 7"
                } else {
                    ""
                }
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun validateAnswer(answer: String): String = ""
        };

        abstract fun nextQuestion(): Question
        abstract fun validateAnswer(answer: String): String
    }
}