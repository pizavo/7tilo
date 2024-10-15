package hw3.extensions

import hw3.sorting.BubbleSort
import hw3.sorting.MergeSort
import hw3.sorting.QuickSort

fun <T: Comparable<T>> MutableList<T>.bubbleSort(): Long =
	BubbleSort<T>().sort(this)

fun <T: Comparable<T>> MutableList<T>.quickSort(): Long =
	QuickSort<T>().sort(this)

fun <T: Comparable<T>> MutableList<T>.mergeSort(): Long =
	MergeSort<T>().sort(this)

