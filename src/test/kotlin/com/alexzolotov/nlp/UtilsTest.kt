package com.alexzolotov.nlp

import java.util.List
import kotlin.test.expect
import org.testng.annotations.Test as test

class UtilsTest {

    test fun flatten() {
        expect(arrayList(1, 2, 3, 4)) {
            arrayList<List<Int>>(arrayList(1, 2), arrayList(3, 4)).flatten()
        }
    }

    test fun sortBy() {
        expect(arrayList(2 to 2, 1 to 3)) {
            arrayList(2 to 2, 1 to 3).sortBy { it._2 }
        }
        expect(arrayList(1 to 3, 2 to 2)) {
            arrayList(2 to 2, 1 to 3).sortBy { it._1 }
        }
    }

    test fun appendToMap() {
        val map = hashMap(1 to 2)
        expect(hashMap(1 to 2, 3 to 4)) {
            map + #(3, 4)
        }
        expect(hashMap(1 to 3)) {
            map + (1 to 3)
        }
    }
}

