package com.alexzolotov.nlp

import java.lang.Math.log
import java.util.HashMap
import java.util.Map
import java.util.Set

/**
 * Classifier model. It is data holder for classification information.
 *
 * @param lengths Words count grouped by classes
 * @param docCount Documents count grouped by classes
 * @param wordCount Word frequencies grouped by classes {class1 -> {word1 -> freq1, word2 -> freq2}}
 * @param dictionarySize Count of unique words in learning set
 */
class NaiveBayesModel(val lengths: Map<String, Int> = HashMap<String, Int>(),
                      val docCount: Map<String, Int> = HashMap<String, Int>(),
                      val wordCount: Map<String, Map<String, Int>> = HashMap<String, Map<String, Int>>(),
                      val dictionarySize: Int = 0) {

    /**
     * Calculation logarithmic word probability {code}(log(P(w|c)){code} with add-one smoothing
     * in given class
     */
    fun wordLogProbability(c: String, word: String) =
        log((wordCount[c].orEmpty().getOrElse(word, {0}).toDouble() + 1.0) /
        (lengths.getOrElse(c, {0}) + dictionarySize))

    /**
     * Calculation logarithmic class probability {code}(log(P(c))){code}
     */
    fun classLogProbability(c: String) = log(docCount.getOrElse(c, {0}).toDouble() / overallDocuments())

    fun overallDocuments() = docCount.values().reduce { x, y -> x + y }

    val classes : Set<String>
        get() {
            return docCount.keySet();
        }

}

