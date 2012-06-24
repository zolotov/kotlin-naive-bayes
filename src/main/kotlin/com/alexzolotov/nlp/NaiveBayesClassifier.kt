package com.alexzolotov.nlp

import java.util.Collection

/**
* Алгоритм непосредственно классификации
* @param m статистическая модель классификатора
*/
class NaiveBayesClassifier(val model: NaiveBayesModel, val tokenize: (String) -> Collection<String>) {
    public fun classify(text: String): String {
        return model.classes.map { (it: String) ->
            #(it, calculateProbability(it, text))
        }.sortBy{it._2}.first()._1
    }

    /**
     * Рассчитывает оценку вероятности документа в пределах класса
     * @param c класс
     * @param s текст документа
     * @return оценка <code>P(c|d)</code>
     */
    fun calculateProbability(cl: String, text: String) = tokenize(text).map {
        model.wordLogProbability(cl, it)
    }.reduce {(x, y) -> x + y} + model.classLogProbability(cl)
}

