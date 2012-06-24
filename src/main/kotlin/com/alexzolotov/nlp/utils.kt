package com.alexzolotov.nlp

import java.util.Map

public inline fun <T> java.lang.Iterable<java.util.List<T>>.flatten(): java.util.Collection<T> {
    return this.fold(arrayList<T>()) { (x, y) ->
        x.addAll(y)
        x
    }
}

// arrayList(2 to 2, 1 to 3).sortBy { it._2 }
public inline fun <T, R: Comparable<in R>> java.util.Collection<T>.sortBy(f: (T) -> R): java.util.List<T> {
    val sortedList = arrayList<T>()
    sortedList.addAll(this)
    java.util.Collections.sort(sortedList, comparator {(x, y) -> f(x).compareTo(f(y))})
    return sortedList
}

public inline fun <K, V> Map<K, V>.plus(pair: #(K, V)): Map<K, V> {
    val result = hashMap<K, V>()
    result.putAll(this)
    result.put(pair._1, pair._2)
    return result
}