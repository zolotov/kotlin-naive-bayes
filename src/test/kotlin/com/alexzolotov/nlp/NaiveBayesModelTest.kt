package com.alexzolotov.nlp

import java.lang.Math.log
import java.util.Map
import kotlin.test.expect
import org.testng.annotations.BeforeClass as before
import org.testng.annotations.Test as test

class NaiveBayesModelTest {

    var model: NaiveBayesModel? = null

    before fun setUp() {
        /*
            Examples:
            "предоставляю услуги бухгалтера" -> "SPAM"
            "спешите купить виагру" -> "SPAM"
            "надо купить молоко" -> "HAM"
        */
        val lengths = hashMap(
                "SPAM" to 6,
                "HAM" to 6)

        val words = hashMap<String, Map<String, Int>>(
                "SPAM" to hashMap(
                        "предоставляю" to 1,
                        "услуги" to 1,
                        "бухгалтера" to 1,
                        "спешите" to 1,
                        "купить" to 1,
                        "виагру" to 1),
                "HAM" to hashMap(
                        "надо" to 2,
                        "купить" to 2,
                        "сигареты" to 1,
                        "молоко" to 1))

        val docsCount = hashMap(
                "SPAM" to 2,
                "HAM" to 2)

        model = NaiveBayesModel(lengths, docsCount, words, 9)
    }

    test fun wordProbabilityCalculation() {
        expect(4) { model?.overallDocuments() }
        expect(log((1.0 + 1) / (6 + 9))) { model?.wordLogProbability("SPAM", "купить") }
        expect(log((2.0 + 1) / (6 + 9))) { model?.wordLogProbability("HAM", "купить") }

    }

    test fun testUnknownWordProbabilityCalculation() {
        expect(4) { model?.overallDocuments() }
        expect(log((0.0 + 1) / (6 + 9))) { model?.wordLogProbability("SPAM", "виагра") }
        expect(log((0.0 + 1) / (6 + 9))) { model?.wordLogProbability("HAM", "виагра") }
    }

    test fun testClassProbabilityCalculation() {
        expect(4) { model?.overallDocuments() }
        expect(log((1.0 + 1) / (6 + 9))) { model?.wordLogProbability("SPAM", "купить") }
        expect(log((2.0 + 1) / (6 + 9))) { model?.wordLogProbability("HAM", "купить") }
    }

}