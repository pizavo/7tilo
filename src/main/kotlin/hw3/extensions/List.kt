package hw3.extensions

import hw3.sorting.BubbleSort
import hw3.sorting.MergeSort
import hw3.sorting.QuickSort
import hw3.sorting.Sort

fun <T: Comparable<T>> List<T>.bubbleSort(): Sort<T> =
	BubbleSort<T>().sort(this)

fun <T: Comparable<T>> List<T>.quickSort(): Sort<T> =
	QuickSort<T>().sort(this)

fun <T: Comparable<T>> List<T>.mergeSort(): Sort<T> =
	MergeSort<T>().sort(this)

fun <T: Comparable<T>> List<T>.sortAndPrintComplexity(name: String) {
	println(name)
	
	val bubbleSort = this.bubbleSort()
	val quickSort = this.quickSort()
	val mergeSort = this.mergeSort()
	
	println("Bubble Sort Complexity: ${bubbleSort.complexity}")
	println("Quick Sort Complexity: ${quickSort.complexity}")
	println("Merge Sort Complexity: ${mergeSort.complexity}")
}

