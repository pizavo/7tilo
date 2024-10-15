package hw3

import hw3.extensions.bubbleSort
import hw3.extensions.mergeSort
import hw3.extensions.quickSort

fun main() {
	val studentNum = "P24026"
	
	studentNum.run {
		val bubbleSortList = this.toMutableList()
		val quickSort = this.toMutableList()
		val mergeSort = this.toMutableList()
		
		val bubbleSortComplexity = bubbleSortList.bubbleSort()
		val quickSortComplexity = quickSort.quickSort()
		val mergeSortComplexity = mergeSort.mergeSort()
		
		println("Bubble Sort: $bubbleSortList, Complexity: $bubbleSortComplexity")
		println("Quick Sort: $quickSort, Complexity: $quickSortComplexity")
		println("Merge Sort: $mergeSort, Complexity: $mergeSortComplexity")
	}
}