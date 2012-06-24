package com.alexzolotov.nlp

import java.util.Collection
import java.util.List
import java.util.Map
import java.util.Set

/**
 * Обучающий алгоритм классификации
 */
class NaiveBayesLearningTool {

    private val examples: Collection<#(String, String)> = arrayList<#(String, String)>()
    private val tokenize = {(v: String) -> v.split(' ').toList()}
    private val tokenizeTuple = { (v: #(String, String)) -> tokenize(v._1) }
    private val calculateWordsCount = {(l: List<#(String, String)>) -> l map{tokenizeTuple(it).size} reduce{(x, y) -> x + y} }

    fun addExample(example: String, cl: String) {
        examples.add(example to cl)
    }

    val dictionary: Set<String>
        get() {
            return examples.map(tokenizeTuple).flatten().toSet()
        }


    val model: NaiveBayesModel
        get() {
            // very complicated. TODO fix after http://youtrack.jetbrains.net/issue/KT-1145
            val docsByClass: Map<String, List<#(String, String)>> = examples.groupBy{ it._2 }
            val lengths = docsByClass mapValues{ (it: Map.Entry<String, List<#(String, String)>>) ->
                calculateWordsCount(it.getValue())
            }
            val docCounts = docsByClass mapValues { (it: Map.Entry<String, List<#(String, String)>>) ->
                it.getValue().size()
            }
            val wordsCount = docsByClass mapValues { (it: Map.Entry<String, List<#(String, String)>>) ->
                it.getValue().map(tokenizeTuple).flatten().groupBy{it : String} mapValues{ (it: Map.Entry<String, List<String>>) ->
                    it.getValue().size
                }
            }

            return NaiveBayesModel(lengths, docCounts, wordsCount, dictionary.size())
        }

    val classifier: NaiveBayesClassifier
        get() {
            return NaiveBayesClassifier(model, tokenize)
        }
}

