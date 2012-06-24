package com.alexzolotov.nlp

import kotlin.test.expect
import org.testng.annotations.Test as test

class NaiveBayesClassifierTest {

    test fun guessClasses() {
        val tool = NaiveBayesLearningTool()
        tool.addExample("предоставляю услуги бухгалтера", "SPAM")
        tool.addExample("спешите купить виагру", "SPAM")
        tool.addExample("надо купить молоко", "HAM")

        expect("HAM") { tool.classifier.classify("надо купить сигареты") }
        expect("SPAM") { tool.classifier.classify("услуги программиста") }
    }
}
