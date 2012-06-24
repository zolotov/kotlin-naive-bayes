package com.alexzolotov.nlp

import kotlin.test.expect
import org.testng.annotations.Test as test

class NaiveBayesLearningToolTest {

    test fun createDictionary() {
        val learningTool = com.alexzolotov.nlp.NaiveBayesLearningTool()
        learningTool.addExample("hello world", "test1")
        learningTool.addExample("hello man", "test2")
        expect(hashSet("hello", "world", "man")) {
            learningTool.dictionary
        }
    }

    test fun createModel() {
        val learningTool = com.alexzolotov.nlp.NaiveBayesLearningTool()
        learningTool.addExample("hello world", "test1")
        learningTool.addExample("hello hello", "test1")
        learningTool.addExample("hello man", "test2")
        val model = learningTool.model

        expect(hashMap("test1" to 4, "test2" to 2)) { model.lengths }
        expect(hashMap("test1" to 2, "test2" to 1)) { model.docCount }
        expect(hashMap(
                "test1" to hashMap("hello" to 3, "world" to 1),
                "test2" to hashMap("hello" to 1, "man" to 1))
        ) { model.wordCount }
        expect(3) { model.dictionarySize }
    }
}